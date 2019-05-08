package hr.markic.rlc.service;

import hr.markic.rlc.domain.User;
import hr.markic.rlc.model.AuthModel;
import hr.markic.rlc.repository.UserRepository;
import hr.markic.rlc.security.AuthenticationRequest;
import hr.markic.rlc.security.jwt.InvalidJwtAuthenticationException;
import hr.markic.rlc.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    public AuthModel signIn(AuthenticationRequest data) throws AuthenticationException {
        String username = data.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
        return createAuthModelWithToken(username);
    }

    public AuthModel refreshToken(AuthModel data) throws InvalidJwtAuthenticationException {
        String refreshToken = data.getRefreshToken();
        Boolean valid = jwtTokenProvider.validateToken(refreshToken);
        if (valid){
            String username = jwtTokenProvider.getUsername(refreshToken);
            return createAuthModelWithToken(username);
        } else {
            throw new InvalidJwtAuthenticationException("Refresh token not valid.");
        }
    }


    private AuthModel createAuthModelWithToken(String username) {
        List<String> roles = this.userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles();
        String accessToken = jwtTokenProvider.createAccessToken(username, roles);
        String refreshToken = jwtTokenProvider.createRefreshToken(username);
        AuthModel auth  = new AuthModel();
        auth.setAccessToken(accessToken);
        auth.setRefreshToken(refreshToken);
        return auth;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null)?((User) auth.getPrincipal()):null;
    }
}
