package clone.colley.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static clone.colley.security.jwt.JwtTokenUtils.*;

@Component
public class JwtDecoder {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String decodeUsername(String token) {
        System.out.println(token);
        DecodedJWT decodedJWT = isValidToken(token)
                .orElseThrow(() -> new IllegalArgumentException("유효한 토큰이 아닙니다 1."));

        Date expiredDate = decodedJWT
                .getClaim(CLAIM_EXPIRED_DATE)
                .asDate();

        Date now = new Date();
        if (expiredDate.before(now)) {
            System.out.println(token);
            throw new IllegalArgumentException("유효한 토큰이 아닙니다 2.");
        }

        String username = decodedJWT
                .getClaim(CLAIM_USER_NAME)
                .asString();



        System.out.println("토큰검증 : " + username);
        return username;
    }

    private Optional<DecodedJWT> isValidToken(String token) {
        DecodedJWT jwt = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();

            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }
}