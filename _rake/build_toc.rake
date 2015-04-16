desc 'Build docs table of contents index'
task :build_toc do
  src_dir = CONFIG[:source_dir]
  toc_file = ENV['dest'] || "#{src_dir}/HelpTOC.json"

  load "#{src_dir}/_lib/toc_generator.rb"

  kramdown_config = YAML::load_file("#{src_dir}/_config.yml")['kramdown']
  toc = Docs::TocGenerator.extract("#{src_dir}/_SUMMARY.md", kramdown_config)

  res = File.write("#{toc_file}", toc.to_json)
  puts "TOC successfully created in #{toc_file}" if res
end