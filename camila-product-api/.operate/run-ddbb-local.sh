#!/usr/bin/env bash

set -o errexit # Exit on error. Append "|| true" if you expect an error.
set -o errtrace # Exit on error inside any functions or subshells.
set -o nounset # Do not allow use of undefined vars. Use ${VAR:-} to use an undefined VAR
set -o xtrace

cd "$(dirname "$0")/.."

# preparar el espacio de trabajo
docker stop mongodb7 || true && \
  docker rm mongodb7 || true && \
  docker volume create mongo-data7 || true

# iniciar un servidor standalone
docker run -it --rm \
  --network host \
  --name mongodb7 \
  -p 27017-27019:27017-27019 \
  --expose 27017-27019 \
  --oom-kill-disable \
  --memory="6192m" --memory-reservation="4096m" --memory-swap="6192m" --cpu-shares=4000 \
  -e MONGODB_INITDB_ROOT_USERNAME=camila \
  -e MONGODB_INITDB_ROOT_PASSWORD=camila \
  -v mongo-data7:/data/db \
  mongodb/mongodb-community-server:7.0.4-ubi8
