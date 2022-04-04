# automationpracticedemo

## Pre-requisite
- Install mvn
- Java jdk 1.8 
- add mvn and javahome in PATH

## Create from scratch
Run 
```bash
mvn archetype:generate                      \
   "-DarchetypeGroupId=io.cucumber"           \
   "-DarchetypeArtifactId=cucumber-archetype" \
   "-DarchetypeVersion=7.0.0"               \
   "-DgroupId=com.globaltechchallenge.automationpracticedemo.web"                  \
   "-DartifactId=automationpracticedemo"               \
   "-Dpackage=com.globaltechchallenge.automationpracticedemo.web"                  \
   "-Dversion=1.0.0-SNAPSHOT"                 \
   "-DinteractiveMode=false"

```


### To run
```bash
mvn clean verify
```
