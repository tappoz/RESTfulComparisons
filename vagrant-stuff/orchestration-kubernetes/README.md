
vagrant@debian-jessie:~/tmp/kubernetes$ ./platforms/linux/amd64/kubectl get pods
error: couldn't read version from server: Get http://localhost:8080/api: dial tcp 127.0.0.1:8080: connection refused

vagrant@debian-jessie:~/tmp/kubernetes$

vagrant@debian-jessie:~/tmp/kubernetes$ ./platforms/linux/amd64/kubectl config view
apiVersion: v1
clusters: []
contexts: []
current-context: ""
kind: Config
preferences: {}
users: []
vagrant@debian-jessie:~/tmp/kubernetes$


vagrant@debian-jessie:~/tmp/kubernetes$ ./kubectl help config

https://devops.profitbricks.com/tutorials/getting-started-with-a-multi-node-kubernetes-cluster-on-ubuntu/
http://www.severalnines.com/blog/installing-kubernetes-cluster-minions-centos7-manage-pods-services
http://www.livewyer.com/blog/2015/05/20/deploying-kubernetes-digitalocean






