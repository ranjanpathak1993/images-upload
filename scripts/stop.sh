#!/bin/bash
if pgrep -f "upload-app.jar"
then
  pkill -f upload-app.jar
fi
