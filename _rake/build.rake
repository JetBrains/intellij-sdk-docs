desc 'Build docs'
task :build do
  dest = ENV['dest'] || CONFIG[:build_destination]

  Rake::Task['build_toc'].invoke
  Rake::Task['prepare_assets'].invoke

  command = "jekyll build --trace --destination=#{dest}"
  sh command
end