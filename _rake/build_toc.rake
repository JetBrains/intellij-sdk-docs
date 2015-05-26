desc 'Build docs table of contents index'
task :build_toc do
  src_dir = CONFIG[:source_dir]
  toc_file = ENV['dest'] || "#{src_dir}/HelpTOC.json"

  load "#{src_dir}/_rake/lib/toc_generator.rb"
  load "#{src_dir}/_rake/lib/summary_checker.rb"

  kramdown_config = YAML::load_file("#{src_dir}/_config.yml")['kramdown']
  toc = Docs::TocGenerator.extract("#{src_dir}/_SUMMARY.md", kramdown_config)
  missing_files = Docs::SummaryChecker.check("#{src_dir}/_SUMMARY.md")

  puts "Checking _SUMMARY.md\n"

  if missing_files.size > 0
    msg = ['Error, following files are missing:']
    missing_files.each do |file|
      msg.push "- #{file}"
    end

    raise (msg.join("\n"))
  else
    puts "_SUMMARY.md is OK"
  end

  res = File.write("#{toc_file}", toc.to_json)
  puts "TOC successfully created in #{toc_file}" if res
end