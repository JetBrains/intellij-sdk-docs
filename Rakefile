Rake.add_rakelib 'sdkdocs-template/rakelib'

begin
  FileUtils.mkdir_p %w( _includes/code_samples )
  Dir.glob('code_samples/**/*').reject { |e| e.match(/\/(build|gradlew?)(\/|$)/) }.each do |path|
    if File.directory? path
      FileUtils.mkdir_p "_includes/#{path}"
    else
      FileUtils.mkdir_p "_includes/#{File.dirname path}"
      FileUtils.cp path, "_includes/#{path}"
    end
  end
rescue
  `yes | cp -rf code_samples _includes`
end

CONFIG = {
    :source_dir => __dir__,
    :tmp_dir => "#{__dir__}/_tmp",
    :build_destination => "_site",
    :preview_host => "0.0.0.0",
    :preview_port => 4000,
    :default_env => 'dev'
}
