/**
 * 
 */
package com.zyj.security.propertis;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "zyj.security")
public class SecurityProperties {
	
	private BrowserProperties browser = new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
}

