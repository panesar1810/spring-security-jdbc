package code.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import code.database.SecurityRepository;

@Service
public class SecUserDetails implements UserDetailsService {
	
	@Autowired @Lazy private SecurityRepository secRepo;
	
	public UserDetails loadUserByUsername(String username) {
		
		code.beans.SecUser secuser = secRepo.findUserAccount(username);
		
		if (null == secuser)
			throw new UsernameNotFoundException("User " + username + " was not found");

		List<String> roleNames = secRepo.findRolesByUserId(secuser.getUserId());
		List<GrantedAuthority> grantedList = new ArrayList<GrantedAuthority>();
		
		if (roleNames != null) {			
			for(String role: roleNames)
				grantedList.add(new SimpleGrantedAuthority(role));
		}
		UserDetails userDetails = (UserDetails) new User(
				secuser.getUsername(), secuser.getPassword(), grantedList);
		
		return userDetails;
	}

}
