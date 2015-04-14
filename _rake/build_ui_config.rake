desc 'Build config for UI'
task :build_ui_config do
  require 'yaml'
  require 'json'

  src_dir = CONFIG[:source_dir]
  ui_config_file_path = ENV['dest'] || "#{src_dir}/config.json"
  docs_config = YAML::load_file("#{src_dir}/_config.yml")

  ui_config = {
    :productName => docs_config['product_name'],
    :searchScope => docs_config['search_scope'],
    :useSideblocks => docs_config['use_side_blocks'],
    :showDisqus => docs_config['show_disqus'],
  }

  res = File.write("#{ui_config_file_path}", ui_config.to_json)
  puts "UI config successfully created in #{ui_config_file_path}" if res
end