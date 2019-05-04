package hr.markic.rlc.rest.controller.security;

import hr.markic.rlc.model.AuthModel;
import hr.markic.rlc.repository.UserRepository;
import hr.markic.rlc.security.AuthenticationRequest;
import hr.markic.rlc.security.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity signIn(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            AuthModel auth = createAuthModelWithToken(username);
            return ok(auth);
        } catch (AuthenticationException e) {
            log.error("Invalid username/password supplied.");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity refreshToken(@RequestBody AuthModel model) {

        try {
            String refreshToken = model.getRefreshToken();
            Boolean valid = jwtTokenProvider.validateToken(refreshToken);
            if (valid){
                String username = jwtTokenProvider.getUsername(refreshToken);
                AuthModel auth = createAuthModelWithToken(username);
                return ok(auth);
            } else {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error("Invalid refresh token supplied");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
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
}
