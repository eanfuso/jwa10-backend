#bin/bash
cd /opt/tomcat/bin/
./catalina.sh stop
#./shutdown.sh
sleep 1
java -cp tomcat-juli.jar:bootstrap.jar org.apache.catalina.startup.Bootstrap
#./catalina.sh run  > /usr/share/tomcat9/tomcat.log 2>&1 &
cd -
