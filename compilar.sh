#bin/bash
#cd /home/pomi/r710/
sudo rm -rf /opt/tomcat/webapps/ROOT/
sudo rm -rf /opt/tomcat/webapps/ROOT.war
mvn clean install -DskipTests
#mvn install -DskipTests
sudo mv target/ROOT /opt/tomcat/webapps/

#cd /opt/tomcat/bin/
#./catalina.sh run
#cd /root/eclipse/jee-2020-062/pomi_02/
