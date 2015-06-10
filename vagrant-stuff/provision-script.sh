#!/bin/bash
#
# based on the work done by William at: https://github.com/William-Yeh/docker-enabled-vagrant


# disable the warning "Your environment specifies an invalid locale"
#sudo touch /var/lib/cloud/instance/locale-check.skip

# update packages
#sudo apt-get update
#sudo apt-get -y -q upgrade

# install docker
mkdir ~/tmp
#cd ~/tmp
#curl -sL https://get.docker.io/ubuntu/ > docker-installer.sh
#sudo su docker-installer.sh

# add 'vagrant' user to docker group
#sudo usermod -aG docker vagrant

# zero out the free space to save space in the final image
#sudo dd if=/dev/zero of=/EMPTY bs=1M
#sudo rm -f /EMPTY


