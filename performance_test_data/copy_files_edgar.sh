#!/bin/bash
mkdir -p edgar1024
mkdir -p edgar256
mkdir -p edgar64
mkdir -p edgar16
for file in edgar16/*; do
  for i in {1..4}; do
    cp "$file" "edgar64/$(basename "$file")_$i.xml"
  done
  done
for file in edgar64/*; do
  for i in {1..4}; do
    cp "$file" "edgar256/$(basename "$file")_$i.xml"
  done
  done
for file in edgar256/*; do
  for i in {1..4}; do
    cp "$file" "edgar1024/$(basename "$file")_$i.xml"
  done
  done
