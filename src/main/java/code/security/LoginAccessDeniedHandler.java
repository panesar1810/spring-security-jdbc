package code.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginAccessDeniedHandler implements AccessDeniedHandler {
	
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			out.println( authentication.getName() + 
					" was trying to access protected resource " + request.getRequestURI());
		}
		response.sendRedirect(request.getContextPath() + "/access-denied");
	}

}
