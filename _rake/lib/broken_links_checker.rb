require 'rexml/xpath'
require_relative './lic/markdown'

module Docs
  class SummaryChecker
    def self.check(path)
      xml = Docs::Utils.md2xml(path)
      dir = File.dirname(path)
      is_ok = true
      missing_files = []

      xml.elements.each("//a") do |link|
        href = link.attribute('href').to_s
        path = "#{dir}/#{href.gsub(/\.html$/, '.md')}"

        if !File.file?(path)
          is_ok = false unless is_ok
          missing_files.push href
          break
        end
      end

      missing_files
    end
  end
end