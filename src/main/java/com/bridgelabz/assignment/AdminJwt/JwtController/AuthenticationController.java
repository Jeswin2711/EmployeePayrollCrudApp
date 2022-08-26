package com.bridgelabz.assignment.AdminJwt.JwtController;


import com.bridgelabz.assignment.model.Admin;
import com.bridgelabz.assignment.AdminSecurity.MyUserDetails;
import com.bridgelabz.assignment.AdminJwt.Jwt.AuthorizationRequest;
import com.bridgelabz.assignment.AdminJwt.Jwt.AuthorizationResponse;
import com.bridgelabz.assignment.AdminJwt.JwtService.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/*
    A controller for Jwt
 */
@RestController
public class AuthenticationController
{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetails userDetailsService;

    @Autowired
    private JwtUtils jwt;

    /*
        this endpoint helps us to return the token by Username and Password
     */
    @RequestMapping(value = "/admin/authenticate",method = RequestMethod.POST)
    public ResponseEntity<AuthorizationResponse> createAuthentication(@RequestBody AuthorizationRequest authorizationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authorizationRequest.getUserName(),
                    authorizationRequest.getPassWord()
            ));
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Invalid Credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(
                authorizationRequest.getUserName()
        );

        String jwtToken = jwt.generateToken(userDetails);

        new Admin(authorizationRequest.getUserName(), authorizationRequest.getPassWord());

        return ResponseEntity.ok(new AuthorizationResponse(jwtToken));
    }
}
