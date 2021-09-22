ECHO OFF
SETLOCAL ENABLEEXTENSIONS
SET spark_dir="C:\deploy\spark\spark-2.3.0-bin-hadoop2.7\bin"
ECHO Submitting task to spark...
pushd "C:\deploy\spark\spark-2.3.0-bin-hadoop2.7\bin"

start cmd.exe /k "call spark-submit.cmd --class "com.sm.atlas_v1.App" --master spark://169.254.61.216:7077 --driver-java-options "-Dlog4j.configuration=file:///C:/EclipseMars2//log4j-driver.properties" --conf "spark.executor.extraJavaOptions=-Dlog4j.configuration=file:///C:/EclipseMars2//log4j-executor.properties" atlas_v1-0.0.1-SNAPSHOT-jar-with-dependencies.jar"

echo submitting..

