ECHO OFF
SETLOCAL ENABLEEXTENSIONS
SET spark_dir="C:\deploy\spark\spark-2.3.0-bin-hadoop2.7\bin"
ECHO Initializing spark-2.3.0.........................................
pushd "C:\deploy\spark\spark-2.3.0-bin-hadoop2.7\bin"
::echo %CD%
start /b cmd.exe /k "call spark-class.cmd org.apache.spark.deploy.master.Master"
echo Starting worker threads.. in 5 seconds.....
timeout 5
start /b cmd.exe /k "call spark-class.cmd org.apache.spark.deploy.worker.Worker spark://169.254.61.216:7077 --cores 2 --memory 1g"
:: timeout 5
::start /b cmd.exe /k "call spark-class.cmd org.apache.spark.deploy.worker.Worker spark://169.254.61.216:7077"
echo Spark initialization complete... location .. http://169.254.61.216:8081/
echo Exiting in.. 
timeout 5
