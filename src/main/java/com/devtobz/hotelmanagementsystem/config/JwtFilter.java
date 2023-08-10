package com.devtobz.hotelmanagementsystem.config;

import com.devtobz.hotelmanagementsystem.service.serviceImpl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private EmployeeUserDetailsService employeeUserDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException
    {
        final String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            EmployeeUserDetails employeeUserDetails = this.employeeUserDetailsService.loadUserByUsername(username);
            if(jwtService.tokenIsValid(employeeUserDetails,jwt)){
                UsernamePasswordAuthenticationToken authToken
                        = new UsernamePasswordAuthenticationToken(employeeUserDetails,
                        null,
                        employeeUserDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

    }
}
