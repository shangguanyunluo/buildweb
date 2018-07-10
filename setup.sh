
# package the project
mvn clean package  -Dmaven.test.skip=true

# rename the war package
mv target/buildweb-0.0.1-SNAPSHOT.war buildweb.war

# move war package to tomcat
mv target/buildweb.war /opt/apache-tomcat-8.5.32/webapps/