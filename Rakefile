Rake.add_rakelib 'sdkdocs-template/rakelib'

begin
FileUtils.mkdir_p %w( _includes/code_samples )
FileUtils.cp_r 'code_samples', '_includes', :verbose => true, :remove_destination => true
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
