package org.scapy;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * This Mojo set the default Authenticator instantiating a PasswordAuthenticator using the system properties 
 * http.proxyUser and http.proxyPassword.
 * <br>
 * If these properties are not set, this Mojo does nothing (it only writes a log).
 * 
 * When run the mvn command, add the following parameters: <br>
 * mvn -Dhttp.proxyUser=myusername -Dhttp.proxyPassword=mypassword -Dhttps.proxyHost=my.proxy.com -Dhttps.proxyPort=443 -Djdk.http.auth.tunneling.disabledSchemes="" ....
 * 
 * 
 * @author Giovanni Scapellato
 * 
 * @see java.net.Authenticator
 * @see java.net.PasswordAuthentication
 */
@Mojo(name = "proxysetup")
public class ProxySettingsMojo extends AbstractMojo {


	/**
	 * Execute method of Mojo plugin 
	 */
	public void execute() throws MojoExecutionException
    {
		final String proxyUser = System.getProperty("http.proxyUser");
	    final String proxyPassword = System.getProperty("http.proxyPassword");

	    if (proxyUser != null && proxyPassword != null) {
	    	getLog().info("http.proxyUser and http.proxyPassword found. Setting proxy for user " + proxyUser);
	        Authenticator.setDefault(
	          new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(
	                proxyUser, proxyPassword.toCharArray()
	              );
	            }
	          }
	        );

	        
	    } else {          
	    	getLog().info("http.proxyUser and http.proxyPassword parameters not found ");
	    }
    }

}
