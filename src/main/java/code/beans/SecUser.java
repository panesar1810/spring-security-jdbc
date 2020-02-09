package code.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SecUser {

	private long userId;
	private String username;
	private String password;
	private boolean enabled;
	
	public SecUser(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
}
