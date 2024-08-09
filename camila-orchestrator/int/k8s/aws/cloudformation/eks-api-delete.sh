#!/usr/bin/env bash

# Prerequisites:
# - aws eks cluster created
# - kubectl installed

set -o errexit # Exit on error. Append "|| true" if you expect an error.
set -o errtrace # Exit on error inside any functions or subshells.
set -o nounset # Do not allow use of undefined vars. Use ${VAR:-} to use an undefined VAR
set -o xtrace

cd "$(dirname "$0")"

kubectl delete namespaces camila-product-api-ns --grace-period=0 --force
