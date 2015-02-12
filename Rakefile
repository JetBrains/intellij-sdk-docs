CONFIG = {
  :source_dir => __dir__,
  :tmp_dir => "#{__dir__}/_tmp",
  :build_destination => "_site",
  :preview_host => "0.0.0.0",
  :preview_port => 4000,
  :default_env => 'dev'
}

Dir['_rake/*.rake'].each { |r| load r }

task :default do
  system('rake -T')
end