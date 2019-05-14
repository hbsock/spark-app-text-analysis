#!/bin/bash

spark-submit \
    --class "textanalysis.SimpleApp" \
    --master local[4] \
    target/scala-2.12/simple-project_2.12-1.0.jar \
    "/home/hanbinsock/programman/haskell/web-scraper/output/a-will-eternal/" \
    "/home/hanbinsock/output/will-eternal-total-word-count"

