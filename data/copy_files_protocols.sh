#!/bin/bash
mkdir -p protocols2k
mkdir -p protocols4k
mkdir -p protocols8k
mkdir -p protocols16k
for file in protocols1k/*; do
  for i in {1..2}; do
    cp "$file" "protocols2k/$(basename "$file")_$i.xml"
  done
  done

for file in protocols1k/*; do
  for i in {1..4}; do
    cp "$file" "protocols4k/$(basename "$file")_$i.xml"
  done
  done

for file in protocols1k/*; do
  for i in {1..8}; do
    cp "$file" "protocols8k/$(basename "$file")_$i.xml"
  done
  done

for file in protocols1k/*; do
  for i in {1..16}; do
    cp "$file" "protocols16k/$(basename "$file")_$i.xml"
  done
  done
