package com.example.controller.jwt;

import com.example.model.account.AppUser;
import com.example.model.jwt.JwtRequest;
import com.example.model.jwt.JwtResponse;
import com.example.service.account.IAppUserService;
import com.example.service.jwt.JwtUserDetailsService;
import com.example.util.JwtTokenUtil;
import com.example.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private TokenUtil tokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        if (authenticationRequest.getUsername() == null || authenticationRequest.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        if (userDetails == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Authentication
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        AppUser appUser = this.appUserService.findAppUserByUsername(authenticationRequest.getUsername());
        System.out.println(appUser.getCreationDate());
        Date date = new Date(System.currentTimeMillis());

        System.out.println(date.toLocalDate());
        System.out.println(appUser.getCreationDate().toLocalDate().plusDays(30));
        // Check Password Expired
        if (date.toLocalDate().compareTo(appUser.getCreationDate().toLocalDate().plusDays(30)) >= 0) {
            return new ResponseEntity<>("PasswordExpired", HttpStatus.UNAUTHORIZED);
        }
        // Get roles list
        List<String> grantList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Create token
        final String token = jwtTokenUtil.generateToken(userDetails);

        this.tokenUtil.getTokenMap().put(userDetails.getUsername(), token);

        return ResponseEntity.ok(new JwtResponse(token, grantList, userDetails.getUsername()));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
