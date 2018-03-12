FROM openjdk:8-slim

#    ___                        _             _
#   |   \ ___ _ __  ___ _ _  __| |___ _ _  __(_)___ ___
#   | |) / -_) '_ \/ -_) ' \/ _` / -_) ' \/ _| / -_|_-<
#   |___/\___| .__/\___|_||_\__,_\___|_||_\__|_\___/__/
#            |_|
#
RUN apt-get update && apt-get install -yq \
    sudo \
    tmux \
    git \
    vim \
	unzip \
    xz-utils \
    curl \
    rlwrap && \
    apt-get clean && \
    rm -fr /var/lib/apt/lists/* /tmp/* /var/tmp/*

#    _   _
#   | | | |___ ___ _ _
#   | |_| (_-</ -_) '_|
#    \___//__/\___|_|
#

RUN echo "dev ALL=(ALL) PASSWD:ALL" >> /etc/sudoers && \
    useradd dev -d /home/dev -s /bin/bash && \
    cp -r /etc/skel /home/dev && \
    chown dev -R /home/dev

RUN echo 'dev:devpassword123' | chpasswd

#  ___            _
# |   \ _____ __ | |_  ___ _ __  ___
# | |) / -_) V / | ' \/ _ \ '  \/ -_)
# |___/\___|\_/  |_||_\___/_|_|_\___|
#

COPY files /
RUN chown dev:dev /entrypoint.sh
RUN chmod +x /entrypoint.sh
USER dev
WORKDIR /home/dev

ENTRYPOINT ["/entrypoint.sh"]
