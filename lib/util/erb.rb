require 'ostruct'
require 'erb'

module Docs
  module Util
    class Erb

      def self.render(path, data = {})
        vars = ErbBinding.new(data)

        erb_contents = File.read(path)
        erb = ERB.new(erb_contents)
        vars_binding = vars.send(:get_binding)

        erb.result(vars_binding)
      end

      class ErbBinding < OpenStruct
        def get_binding
          binding
        end
      end
    end
  end
end