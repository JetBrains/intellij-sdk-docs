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
  gem install bundler && \
# git clone --depth 1 http://github.com/jetbrains/intellij-sdk-docs && \
  git clone --depth 1 http://github.com/breandan/intellij-sdk-docs && \
  cd intellij-sdk-docs && \
  git submodule init && \
  git submodule update

RUN \
  cd intellij-sdk-docs && \
  rake bootstrap

EXPOSE 4000
