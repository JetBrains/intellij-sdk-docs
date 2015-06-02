desc 'Preparing assets'
task :prepare_assets do
  dest = ENV["dest"] || CONFIG[:build_destination]
  appsrc = 'webhelp-template/app'
  appdest = 'app'

  # webhelp template
  RakeFileUtils.cp 'webhelp-template/app/templates/page.html', '_includes'
end