require 'rexml/xpath'
require_relative './util/markdown'

module Docs
  class SummaryChecker
    def self.check(path)
      xml = Docs::Utils.md2xml(path)
      dir = File.dirname(path)
      is_ok = true
      missing_files = []

      xml.elements.each("//a") do |link|
        href = link.attribute('href').to_s
        is_external = href.start_with?('http://', 'https://', 'ftp://', '//')
        path = "#{dir}/#{href.gsub(/\.html$/, '.md')}"

        if !is_external and !File.file?(path)
          is_ok = false unless is_ok
          missing_files.push href
          break
        end
      end

      missing_files
    end
  end
end