package ucg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.datasource")
public class ApplicationConfiguration {
	private String	url;
	private String	username;
	private String	password;

	@Override
	public String toString() {
		return "ApplicationConfiguration [url=" + url + ", username=" + username + ", password=" + "\010*\010*\010*\010*\010*\010*]";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
