require 'kramdown'
require 'pygments'

module Kramdown
  module Converter
    class Upsrc < Html

      def convert_header(el, indent)
        attr = el.attr.dup
        el_id = generate_id(el.options[:raw_text])

        if @options[:auto_ids] && !attr['id']
          attr['id'] = el_id
        end
        @toc << [el.options[:level], attr['id'], el.children] if attr['id'] && in_toc?(el)
        level = output_header_level(el.options[:level])

        if level <= 3
          anchor = Element.new(:a, nil, {'href' => '#' + el_id, 'class' => 'anchor-link'})
          el.children.push(anchor)
        end

        anchor = format_as_block_html("a", {'name' => el_id, 'class' => 'elem-anchor'}, inner(Element.new(:a, nil), indent), indent)
        header = format_as_block_html("h#{level}", attr, inner(el, indent), indent)
        anchor + header
      end

      def convert_codeblock(el, indent)
        attr = el.attr.dup
        lang = self.extract_code_language(attr) || 'text'
        highlight_lines = ''
        
        if attr['class'] and attr['class'].scan(/\{[\d\-\,]+\}/).length > 0
          lang_parts = attr['class'].split('{')
          highlight_lines = "{#{lang_parts[1]}"
        end

        code = pygmentize(el.value, lang, highlight_lines)
        code_attr = {}
        code_attr['class'] = "code-block__wrapper"
        code_attr['class'] += " code-block _highlighted lang_#{lang}" if lang

        "<pre><code#{html_attributes(code_attr)}>#{code}</code></pre>\n"
      end

      # Extract the code block/span language from the attributes.
      def extract_code_language(attr)
        if attr['class']
          class_attr = attr['class']

          if class_attr.scan(/\{|\}/).length > 0
            class_attr = class_attr.split('{')[0]
          end

          class_attr.scan(/\blanguage-(\w+)\b/).first.first
        end
      end

      def convert_codespan(el, indent)
        attr = el.attr.dup
        lang = extract_code_language!(attr) || 'text'
        code = pygmentize(el.value, lang)
        attr['class'] = 'code'
        attr['class'] += " highlight language-#{lang}" if lang
        "<code#{html_attributes(attr)}>#{code}</code>"
      end

      def convert_blockquote(el, indent)
        format_as_indented_block_html(el.type, el.attr, inner(el, indent), indent)
      end

      def convert_a(el, indent)
        res = inner(el, indent)
        attr = el.attr.dup
        attr['href'] = '' if attr['href'].nil?
        is_external = attr['href'].start_with?('http://', 'https://', 'ftp://', '//')
        attr['data-bypass'] = 'yes' if is_external
        if attr['href'].start_with?('mailto:')
          mail_addr = attr['href'][7..-1]
          attr['href'] = obfuscate('mailto') << ":" << obfuscate(mail_addr)
          res = obfuscate(res) if res == mail_addr
        end
        format_as_span_html(el.type, attr, "<span>#{res}</span>")
      end

      def convert_img(el, indent)
        "<img#{html_attributes(el.attr)} />"
      end

      def pygmentize(code, lang, highlight_lines = nil)
        hl_lines = ''
        
        if highlight_lines
          hl_lines = highlight_lines.gsub(/[{}]/, '').split(',').map do |ln|
            if matches = /(\d+)-(\d+)/.match(ln)
              ln = Range.new(matches[1], matches[2]).to_a.join(' ')
            end
            ln
          end.join(' ')
        end
        
        if lang
          Pygments.highlight(code,
            :lexer => lang,
            :options => {
                :encoding => 'utf-8', 
                :nowrap => true,
                :hl_lines => hl_lines
            }
          )
        else
          escape_html(code)
        end
      end
    end
  end

  module Parser
    class GFM2 < GFM
      def parse
        super
      end

      FENCED_CODEBLOCK_MATCH = /^(([~`]){3,})\s*?(\w+[\{\}\,\d\-]*?)?\s*?\n(.*?)^\1\2*\s*?\n/m
    end
  end

end

module Jekyll
  class KramdownPygments < Jekyll::Converter
    def matches(ext)
      ext =~ /^\.md$/i
    end

    def output_ext(ext)
      ".html"
    end

    def convert(content)
      html = Kramdown::Document.new(content, {
          :auto_ids             => @config['kramdown']['auto_ids'],
          :footnote_nr          => @config['kramdown']['footnote_nr'],
          :hard_wrap          =>   @config['kramdown']['hard_wrap'],
          :entity_output        => @config['kramdown']['entity_output'],
          :toc_levels           => @config['kramdown']['toc_levels'],
          :smart_quotes         => @config['kramdown']['smart_quotes'],
          :coderay_default_lang => @config['kramdown']['default_lang'],
          :input                => @config['kramdown']['input']
      }).to_upsrc
      return html
    end
  end
end