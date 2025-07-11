#!/bin/bash
set -e  # Para em caso de erro

# Instala dependências básicas
apt-get update
apt-get install -y ncurses-bin

# Executa o build
chmod +x mvnw
./mvnw clean install -DskipTests