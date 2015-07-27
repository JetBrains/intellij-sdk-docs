FROM ruby:2.2

RUN apt-get update

RUN apt-get --yes install nodejs

WORKDIR /usr/src/app

EXPOSE 4000

CMD \
  rake bootstrap && \
  rake preview