require 'yaml'
require 'json'
require 'kramdown'
require 'rexml/document'
require 'rexml/xpath'

module Docs
  class TocGenerator

    ITEM_TYPE_HEADER = 'header'

    # @param path {String} path to Markdown file
    # @param kramdown_config {Hash} Kramdown config
    # @return {Hash}
    def self.extract(path, kramdown_config = {})
      toc = []
      content = File.read(path)
      kramdown_config = kramdown_config.merge({:html_to_native => true})
      kramdown_doc = Kramdown::Document.new(content, kramdown_config)
      kramdown_doc_content = <<-XML
        <?xml version="1.0" encoding="UTF-8" ?>
        <root>
          #{kramdown_doc.to_html}
        </root>
      XML

      kramdown_doc_content = kramdown_doc_content.gsub(/<!--(.*?)-->/m, '')

      xml = REXML::Document.new kramdown_doc_content
      xml.elements.each("/root/*") do |node|
        items = extract_from_node(node)
        toc.concat(items) if items != nil
      end

      # Removing headers of empty sections
      delete_list = []
      (0).upto(toc.length-1) do |i|
        item = toc[i]
        prev = toc[i-1] != nil ? toc[i-1] : nil
        item_is_header = (item[:type] != nil and item[:type] == self::ITEM_TYPE_HEADER)
        prev_is_header = (prev != nil and prev[:type] != nil and prev[:type] == self::ITEM_TYPE_HEADER)

        if item_is_header and prev_is_header
          delete_list.push(i-1)
        end
      end

      delete_list.each do |del_index|
        toc.delete_at(del_index)
      end

      return toc
    end


    private
    # @param node {REXML::Node}
    # @return {Hash}
    def self.extract_from_node(node)
      items = []

      case node.name
        when 'ul'
          REXML::XPath.each(node, './li') do |node|
            item = extract_items(node)
            items.push(item) if item != nil
          end

        when 'h2'
          items.push(extract_header(node))
      end

      return items.length > 0 ? items : nil
    end

    # @param node {REXML::Node}
    # @return {Hash}
    def self.extract_header(header_node)
      return {
          :title => header_node.text(),
          :type => self::ITEM_TYPE_HEADER
      }
    end

    # @param node {REXML::Node}
    # @return {Hash}
    def self.extract_items(li_node)
      item = nil

      REXML::XPath.each(li_node, 'a') do |link|
        href = link.attribute('href').to_s
        id = File.basename(href, File.extname(href))
        is_external = href.start_with?('http://', 'https://', 'ftp://', '//')
        item = {
            :id => id,
            :title => link.text(),
            :url => href
        }
        item[:is_external] = true if is_external
        pages = []

        REXML::XPath.each(li_node, 'ul/li') do |li|
          pages.push(extract_items(li))
        end

        item['pages'] = pages unless pages.empty?
      end

      return item
    end

  end
end