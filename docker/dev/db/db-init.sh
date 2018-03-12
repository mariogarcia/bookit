#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
     CREATE ROLE bookit PASSWORD 'bookit' LOGIN;
     CREATE DATABASE bookit OWNER bookit;
     GRANT ALL ON DATABASE bookit TO bookit;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" bookit <<-EOSQL
     CREATE EXTENSION IF NOT EXISTS "hstore";
EOSQL
