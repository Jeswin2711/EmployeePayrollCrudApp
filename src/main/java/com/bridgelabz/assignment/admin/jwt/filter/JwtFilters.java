package com.bridgelabz.assignment.admin.jwt.filter;

import com.bridgelabz.assignment.admin.jwt.jwtservice.JwtUtils;
import com.bridgelabz.assignment.admin.security.AdminDetails;
import org.springframework.beans.factory.annotation.Autowired;
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

/*
    JwtFiltersEmployee annotated with @Component which extend OncePerRequestFilter class
    which helps us to get the token and to validate
    and if the token is valid it permits the other api's
 */

@Component
public class JwtFilters extends OncePerRequestFilter {
    @Autowired
    private AdminDetails adminDetails;
    @Autowired
    private JwtUtils jwtToken;

    /*
        An overrride class which helps us to do the validation operation
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtToken.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication()== null) {

            UserDetails userDetails = adminDetails.loadUserByUsername(username);

            if (jwtToken.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
