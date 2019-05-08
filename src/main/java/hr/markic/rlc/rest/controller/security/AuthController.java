package hr.markic.rlc.rest.controller.security;

import hr.markic.rlc.model.AuthModel;
import hr.markic.rlc.security.AuthenticationRequest;
import hr.markic.rlc.security.jwt.InvalidJwtAuthenticationException;
import hr.markic.rlc.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    AuthService authService;

    @Autowired
    public AuthController(AuthService service){
        this.authService = service;
    }

    @PostMapping("/login")
    public ResponseEntity signIn(@RequestBody AuthenticationRequest data) {
        try {
            return ok(authService.signIn(data));
        } catch (AuthenticationException e) {
            log.error("Invalid username/password supplied.");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity refreshToken(@RequestBody AuthModel model) {
        try {
            return ok(authService.refreshToken(model));
        } catch (InvalidJwtAuthenticationException e) {
            log.error("Invalid refresh token supplied.");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error("Internal error occured.");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
