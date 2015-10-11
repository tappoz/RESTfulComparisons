#!/bin/bash
#
# based on the work done by William at: https://github.com/William-Yeh/docker-enabled-vagrant

HOME_USER=/home/vagrant

# update packages
sudo apt-get update
sudo apt-get -y -q upgrade

# install docker
mkdir -p ${HOME_USER}/tmp
cd ${HOME_USER}/tmp
curl -sL https://get.docker.io/ubuntu/ > docker-installer.sh
sudo sh docker-installer.sh

# running docker as a daemon
sudo service docker start

# install docker-compose
curl -o docker-compose -L https://github.com/docker/compose/releases/download/1.4.2/docker-compose-`uname -s`-`uname -m` 
chmod a+x docker-compose
sudo mv docker-compose /usr/local/bin


# clone the git repository for this current project
# (currently commented out as we can use vagrant synced folders)
# mkdir -p /home/vagrant/repo
# cd /home/vagrant/repo
# git clone https://github.com/tappoz/RESTfulComparisons.git
# sudo chown -R vagrant:vagrant RESTfulComparisons

# add 'vagrant' user to docker group
sudo usermod -aG docker vagrant

# zero out the free space to save space in the final image
sudo dd if=/dev/zero of=/EMPTY bs=1M
sudo rm -f /EMPTY

# configuration of the vagrant user
echo "alias ll='ls -laFh'" >> ${HOME_USER}/.bashrc
