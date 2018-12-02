#!/bin/bash

cat ../TD.csv | awk -F ',' '{print $4" "$3}' | sort -n | grep -v 0 > train