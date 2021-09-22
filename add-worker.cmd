ECHO OFF
SETLOCAL ENABLEEXTENSIONS
SET spark_dir="C:\deploy\spark\spark-2.3.0-bin-hadoop2.7\bin"
ECHO Initializing spark-2.3.0.........................................
pushd "C:\deploy\spark\spark-2.3.0-bin-hadoop2.7\bin"
echo %CD%
echo Starting worker threads.. 
start cmd.exe /k "call spark-class.cmd org.apache.spark.deploy.worker.Worker spark://169.254.61.216:7077 --cores 2 --memory 1g"