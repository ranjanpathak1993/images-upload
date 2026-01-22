#!/bin/bash
cd /home/ec2-user/images-upload
nohup java -jar upload-app.jar > app.log 2>&1 &
