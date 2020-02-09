package code.components;

import org.springframework.stereotype.Component;

import lombok.var;

@Component
public class PasswordValidator {

	private String specialCharacters = "!\"#$%&'()*+,-./:;<=>?@[\\/]^_`{|}~";
	private String numbers = "1234567890";
	
	public boolean check(String password) {
		if (length(password) && space(password) &&
			upperCase(password) && lowerCase(password) &&
		    number(password) && specialCharacter(password)) {
			return true;
		}
		return false;
	}
	
	public boolean length(String password) {
		if (8 <= password.length() && password.length() <= 20)
			return true;
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean space(String password) {
		for (int i=0; i<password.length(); i++) {
			if (Character.isSpace(password.charAt(i))) {
				return false;
			}
		} return true;
	}
	
	public boolean upperCase(String password) {
		for (int i=0; i<password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		} return false;
	}
	
	public boolean lowerCase(String password) {
		for (int i=0; i<password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				return true;
			}
		} return false;
	}
	
	public boolean number(String password) {
		for (int i=0; i<numbers.length(); i++) {
			var number = String.valueOf(numbers.charAt(i));
			if (password.contains(number)) {
				return true;
			}
		} return false;
	}
	
	public boolean specialCharacter(String password) {
		for (int i=0; i<specialCharacters.length(); i++) {
			var specialCharacter = String.valueOf(specialCharacters.charAt(i));
			if (password.contains(specialCharacter)) {
				return true;
			}
		} return false;
	}
	
}
