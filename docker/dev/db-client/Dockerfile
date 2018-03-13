FROM debian:stretch-slim

#    ___                        _             _
#   |   \ ___ _ __  ___ _ _  __| |___ _ _  __(_)___ ___
#   | |) / -_) '_ \/ -_) ' \/ _` / -_) ' \/ _| / -_|_-<
#   |___/\___| .__/\___|_||_\__,_\___|_||_\__|_\___/__/
#            |_|
#
RUN apt-get update && apt-get install -yq \
	unzip \
    xz-utils \
    curl \
    rlwrap && \
    apt-get clean && \
    rm -fr /var/lib/apt/lists/* /tmp/* /var/tmp/*

RUN curl -L https://omnidb.org/dist/2.5.0/omnidb-server_2.5.0-debian-amd64.deb -o omni.deb
RUN dpkg -i omni.deb

ENTRYPOINT ["omnidb-server"]
