FROM ubuntu:20.10

RUN apt-get update && apt-get install -y --no-install-recommends \
    # git pull script dependencies
    ca-certificates \
    git \
    # rest-service dependencies
    maven \
    openjdk-11-jdk \
 && rm -rf /var/lib/apt/lists/*
