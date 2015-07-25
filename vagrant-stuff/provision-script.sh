#!/bin/bash
#
# based on the work done by William at: https://github.com/William-Yeh/docker-enabled-vagrant

# useful stuff for bashrc
echo "alias ll='ls -lah'" >> /home/vagrant/.bashrc
echo "alias ll='ls -lah'" >> /root/.bashrc

# update packages
sudo apt-get update
sudo apt-get -y -q upgrade

# install docker
mkdir -p /home/vagrant/tmp
cd /home/vagrant/tmp
curl -sL https://get.docker.io/ubuntu/ > docker-installer.sh
sudo sh docker-installer.sh

# running docker as a daemon
sudo service docker start

# install etcd (distributed cache)
ETCD_VER=2.0.13
ETCD_LABEL=etcd-v$ETCD_VER-linux-amd64
ETCD_TAR_FILE=$ETCD_LABEL.tar.gz
# echo "ETCD_VER=2.0.13" >> ~/.bashrc
# echo "ETCD_LABEL=etcd-v$ETCD_VER-linux-amd64" >> ~/.bashrc
# echo "ETCD_TAR_FILE=$ETCD_LABEL.tar.gz" >> ~/.bashrc
cd /home/vagrant/tmp
curl -L  https://github.com/coreos/etcd/releases/download/v$ETCD_VER/$ETCD_TAR_FILE  -o etcd.tar.gz
tar xzvf etcd.tar.gz
sudo mv $ETCD_LABEL/etcd     /usr/bin
sudo mv $ETCD_LABEL/etcdctl  /usr/bin
# rm -rf  $ETCD_LABEL  etcd.tar.gz

# install kubernetes orchestration
# cfr https://github.com/GoogleCloudPlatform/kubernetes/releases
cd /home/vagrant/tmp
KUBERNETES_VER=1.0.1
KUBERNETES_LABEL=kubernetes
KUBERNETES_TAR_FILE=$KUBERNETES_LABEL.tar.gz
# echo "KUBERNETES_VER=1.0.1" >> ~/.bashrc
curl -sL https://github.com/GoogleCloudPlatform/kubernetes/releases/download/v$KUBERNETES_VER/$KUBERNETES_TAR_FILE -o $KUBERNETES_TAR_FILE
tar xzvf $KUBERNETES_TAR_FILE
# TODO: $KUBERNETES_LABEL/hack/local-up-cluster.sh

# make the 'vagrant' user the owner of the downloaded stuff
chown -R vagrant:vagrant /home/vagrant/tmp

# add 'vagrant' user to docker group
sudo usermod -aG docker vagrant

# zero out the free space to save space in the final image
# sudo dd if=/dev/zero of=/EMPTY bs=1M
# sudo rm -f /EMPTY
