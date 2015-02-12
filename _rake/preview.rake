desc "Runs site on a local preview webserver"
task :preview do
  host = ENV["host"] || CONFIG[:preview_host]
  port = ENV["port"] || CONFIG[:preview_port]

  Rake::Task["build_toc"].invoke
  Rake::Task['prepare_assets'].invoke

  command = "jekyll serve --trace  --host=#{host} --port=#{port} --watch --force_polling"
  sh command
end