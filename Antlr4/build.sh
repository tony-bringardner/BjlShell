# run antlr tool
antlr=/usr/local/lib/antlr-4.13.2-complete.jar
out=/Volumes/Data/eclipse-workspace-jmail/BjlFileSystemConsole/fssh/us/bringardner/filesource/sh
src=/Volumes/Data/eclipse-workspace-jmail/BjlFileSystemConsole/Antlr4
#antlr4 -package us.bringardner.filesource.sh -o $out $src/FileSourceShLexer.g4 $out $src/FileSourceSh.g4 
java -Xmx500M -cp "$antlr:$CLASSPATH" org.antlr.v4.Tool -visitor -listener -package us.bringardner.filesource.sh -o $out $src/FileSourceSh.g4
