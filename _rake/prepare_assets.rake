desc 'Preparing assets'
task :prepare_assets do
  dest = ENV["dest"] || CONFIG[:build_destination]
  appsrc = 'webhelp-template/app'
  appdest = 'app'

  # webhelp template
  RakeFileUtils.cp 'webhelp-template/app/templates/page.html', '_includes'

  # assets dir
  RakeFileUtils.mkdir_p %W(#{appdest}/css #{appdest}/fonts #{appdest}/img #{appdest}/js/vendor/requirejs)

  # js
  RakeFileUtils.cp_r "#{appsrc}/js/main.build.js", "#{appdest}/js"
  RakeFileUtils.cp_r "#{appsrc}/js/vendor/requirejs/require.js", "#{appdest}/js/vendor/requirejs"

  # css
  RakeFileUtils.cp_r "#{appsrc}/css/styles.min.css", "#{appdest}/css"

  # images & fonts
  RakeFileUtils.cp_r %W(#{appsrc}/fonts #{appsrc}/img), appdest
end