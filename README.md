# Spark app for text analysis

### To build:
```
sbt package
```

### To use:
```
spark-submit \
  --class "SimpleApp" \
    --master local[4] \
      target/scala-2.12/simple-project_2.12-1.0.jar
```

### master branch build status:

[![Build Status](https://travis-ci.org/hbsock/spark-app-text-analysis.svg?branch=master)](https://travis-ci.org/hbsock/spark-app-text-analysis)
