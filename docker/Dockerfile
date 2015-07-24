FROM ubuntu:14.04

RUN \
  apt-get install --yes software-properties-common && \
  apt-get install --yes python && \
  add-apt-repository --yes ppa:brightbox/ruby-ng && \
  add-apt-repository --yes ppa:chris-lea/node.js && \
  apt-get update && \
  apt-get install --yes vim curl git

RUN \
  apt-get install --yes ruby2.1 ruby2.1-dev bundler && \
  apt-get install --yes libghc-zlib-dev && \
  apt-get install --yes nodejs

RUN \
  gem install bundler

VOLUME /data

WORKDIR /data

EXPOSE 4000

CMD \
  if cd intellij-sdk-docs; \
    then git pull; \
    else git clone --depth 1 https://github.com/jetbrains/intellij-sdk-docs.git && cd intellij-sdk-docs; \
  fi && \
  git submodule update --init && \
  rake bootstrap && \
  rake preview
