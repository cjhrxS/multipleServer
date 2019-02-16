package ua.ita.smartcarservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import ua.ita.smartcarservice.service.impl.UserDetailsServiceImpl;

/**
 * 
 * This class : 
 * 1. Get JWT token from header 
 * 2  Validate JWT 
 * 3. Parse username from validated JWT 
 * 4. Load data from users table, then build an authentication object 
 * 5. Set the authentication object to Security Context
 *
 */

public class JwtAuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private UserDetailsServiceImpl userDetailServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String jwt = getJwt(request);
			System.out.println("With token everything ok: "+ jwt);
			if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
				String username = tokenProvider.getUserNameFromJwtToken(jwt);
				System.out.println("username is not ok: " + username);

				UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		} catch (Exception e) {
			
			System.out.println("Can NOT set user authentication: " + e);

		}
		
		filterChain.doFilter(request, response);

	}

	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}

		return null;
	}

}
