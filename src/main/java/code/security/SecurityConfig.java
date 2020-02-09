package code.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private LoginAccessDeniedHandler accessDeniedHandler;
	@Autowired private SecUserDetails userDetails;
	@Autowired private BCryptPasswordEncoder bcrypt;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

		.antMatchers(HttpMethod.POST, "/process/sign-up").permitAll()
		.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/admin/**").hasRole("ADMIN")
		
	    .antMatchers("/", "/css/**", "/js/**", "/img/**", "/font/**").permitAll().and()
	    
		.formLogin().loginPage("/login").permitAll().and()
		
		.logout()
		.invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").permitAll().and()

		.exceptionHandling().accessDeniedHandler(this.accessDeniedHandler);
	}	
	
	@Autowired 
	public void configureGlobal(AuthenticationManagerBuilder authorize)
			throws Exception {
		authorize.userDetailsService(userDetails).passwordEncoder(this.bcrypt);
	}

}
