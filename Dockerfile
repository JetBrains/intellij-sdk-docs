FROM ruby:2.2

RUN apt-get update

RUN apt-get --yes install locales
RUN dpkg-reconfigure -f noninteractive locales && \
  locale-gen C.UTF-8 && \
  /usr/sbin/update-locale LANG=C.UTF-8
RUN echo 'en_US.UTF-8 UTF-8' >> /etc/locale.gen && \
  locale-gen
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

# Tell anyone who's interested that we're running in docker
ENV DOCKER true

RUN apt-get --yes install nodejs

# Add bundle install to Docker image
ADD Gemfile* /tmp/
ADD Rakefile /tmp/
ADD _SUMMARY.md /tmp/
ADD .git /tmp/.git
ADD sdkdocs-template /tmp/sdkdocs-template
WORKDIR /tmp
RUN rake bootstrap --trace

WORKDIR /usr/src/app

EXPOSE 4000

CMD \
  rake bootstrap --trace && \
  rake preview
