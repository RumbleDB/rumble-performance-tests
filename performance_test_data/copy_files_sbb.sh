#!/bin/bash
mkdir -p sbbxml1024
mkdir -p sbbxml256
mkdir -p sbbxml64
mkdir -p sbbxml16
for file in sbbxml1/*; do
  for i in {1..16}; do
    cp "$file" "sbbxml16/$(basename "$file")_$i.xml"
  done
  done
for file in sbbxml16/*; do
  for i in {1..4}; do
    cp "$file" "sbbxml64/$(basename "$file")_$i.xml"
  done
  done
for file in sbbxml64/*; do
  for i in {1..4}; do
    cp "$file" "sbbxml256/$(basename "$file")_$i.xml"
  done
  done
for file in sbbxml256/*; do
  for i in {1..4}; do
    cp "$file" "sbbxml1024/$(basename "$file")_$i.xml"
  done
  done
