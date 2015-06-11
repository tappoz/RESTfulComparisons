#!/bin/bash
#
# based on the work done by William at: https://github.com/William-Yeh/docker-enabled-vagrant

# update packages
sudo apt-get update
sudo apt-get -y -q upgrade

# install docker
mkdir /home/vagrant/tmp
cd /home/vagrant/tmp
curl -sL https://get.docker.io/ubuntu/ > docker-installer.sh
sudo sh docker-installer.sh

# clone the git repository for this current project
mkdir /home/vagrant/repo
cd /home/vagrant/repo
git clone https://github.com/tappoz/RESTfulComparisons.git

# add 'vagrant' user to docker group
sudo usermod -aG docker vagrant

# zero out the free space to save space in the final image
sudo dd if=/dev/zero of=/EMPTY bs=1M
sudo rm -f /EMPTY
