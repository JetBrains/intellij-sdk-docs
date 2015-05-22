require 'kramdown'
require 'rexml/document'

module Docs
  class Utils
    def self.md2xml(path, kramdown_config = {})
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

      return xml
    end
  end
end