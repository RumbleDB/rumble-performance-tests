#!/bin/bash
mkdir -p sbbxml1024 # Create the destination folder if it doesn't exist
for file in sbbxml256/*; do
  for i in {1..4}; do
    cp "$file" "sbbxml1024/$(basename "$file")_$i.xml"
  done
  done
