package hr.markic.rlc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthModel {
    String username;
    @JsonProperty("access_token")
    String accessToken;
    @JsonProperty("refresh_token")
    String refreshToken;
}
