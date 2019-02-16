package ua.ita.smartcarservice.security;

import io.jsonwebtoken.SignatureException;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import ua.ita.smartcarservice.service.UserPrinciple;

/**
 * 
 * This class implements useful functions:
 * 1. Generate a JWT token 
 * 2. Validate a JWT token 
 * 3. Parse username from JWT token
 *
 */

@Component
public class TokenProvider {


	public String generateToken(Authentication authentication) {

		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

		return Jwts
		        .builder().setSubject((userPrinciple.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + WebSecurityConstant.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, WebSecurityConstant.SECRET).compact();

	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(WebSecurityConstant.SECRET).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid JWT signature: " + e);
		} catch (MalformedJwtException e) {
			System.out.println("Invalid JWT token: " + e);
		} catch (ExpiredJwtException e) {
			System.out.println("Expired JWT token: " + e);
		} catch (UnsupportedJwtException e) {
			System.out.println("Unsupported JWT token: " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("JWT claims string is empty: " + e);
		}

		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(WebSecurityConstant.SECRET).parseClaimsJws(token).getBody().getSubject();
	}

}
