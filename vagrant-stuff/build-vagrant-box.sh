#!/bin/bash
#
# based on the work done by William at: https://github.com/William-Yeh/docker-enabled-vagrant/
# 
# $ vagrant init debian/jessie64; vagrant up --provider virtualbox

readonly BOX_NAME=vm-debian-docker
readonly BOX_FILE_NAME=vm-debian-docker.box

#
# cfr.: 
#  - http://docs.vagrantup.com/v2/cli/box.html
#  - http://docs.vagrantup.com/v2/boxes/base.html
#

rm -f $BOX_FILE_NAME
vagrant box remove --force $BOX_NAME
vagrant halt
vagrant destroy --force

vagrant up
vagrant package --output $BOX_FILE_NAME
vagrant box add  --name $BOX_NAME $BOX_FILE_NAME
