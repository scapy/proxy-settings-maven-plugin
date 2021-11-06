# proxy-settings-maven-plugin
Set Basic Java Basic Authentication behind enterprise proxy using Basic Authentication in maven goal. 

You have to include this plugin in your pom.xml and then you have to run maven adding the proxy parameters:

mvn -Dhttp.proxyUser=myusername -Dhttp.proxyPassword=mypassword -Dhttps.proxyHost=my.proxy.com -Dhttps.proxyPort=443 -Djdk.http.auth.tunneling.disabledSchemes="" ...
