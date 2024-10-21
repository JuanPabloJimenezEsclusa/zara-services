#!/usr/bin/env bash

set -o errexit # Exit on error. Append "|| true" if you expect an error.
set -o errtrace # Exit on error inside any functions or subshells.
set -o nounset # Do not allow use of undefined vars. Use ${VAR:-} to use an undefined VAR
if [[ "${debug:-}" == "true" ]]; then set -o xtrace; fi  # enable debug mode.

cd "$(dirname "$0")"

# aplicar los primitivos
kubectl apply -f ./camila-product-ddbb.yml

# consultar el namespace
kubectl get all,pv,pvc,resourcequotas,ingress -n mongodb -o wide --show-labels

# se requiere permisos de escritura en la ruta del host
sudo chmod a+w /tmp/hostpath-provisioner/data/mongo/ -R || true
