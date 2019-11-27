package pl.polsl.temperature.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class AuthenticationRequestFilter extends OncePerRequestFilter {

    private final AuthenticationUserDetailsService authenticationUserDetailsService;
    private final AuthenticationUtils authenticationUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final String requestTokenHeader = request.getHeader("Authorization");
            System.out.println(request.getQueryString());
            String jwtToken = authenticationUtils.getTokenFromHeader(requestTokenHeader);
            String username = authenticationUtils.getUsernameFromToken(jwtToken);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = authenticationUserDetailsService.loadUserByUsername(username);
                if (authenticationUtils.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        } catch (NullPointerException e) {
            System.out.println("NullPointer during filtering");
        } catch (MalformedJwtException e){
            System.out.println("Malformed jwt exception");
        }
        chain.doFilter(request, response);
    }
}