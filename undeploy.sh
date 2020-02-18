#!/bin/sh

kubectl delete service,deployment,configmap authenteq-greeting-wicket --namespace authenteq-greeting-wicket || exit 1
kubectl delete clusterrolebinding authenteq-greeting-wicket:default || exit 1
kubectl delete namespace authenteq-greeting-wicket || exit 1

minikube cache delete docker.authenteq.com/authenteq-greeting-wicket:0.1.0-SNAPSHOT
