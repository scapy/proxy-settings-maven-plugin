package org.scapy;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "proxysetup")
public class ProxySettingsMojo extends AbstractMojo {


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
