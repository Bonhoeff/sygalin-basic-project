package {{.PackageName}}.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import {{.PackageName}}.configurations.AppException;
import {{.PackageName}}.configurations.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class TokenManagerService implements TokenManager{
    @Value("${application.JWT-KEY}")
    private String jwtKey;
    @Value("${application.name}")
    private String appName;
    final String signMethod;
    final String duration;
    private String forUser;

    public TokenManagerService(String signMethod, String duration, String forUser) {
        this.signMethod = signMethod;
        this.duration = duration;
        this.forUser = forUser;
    }

    @Override
    public String generateToken(Map<String,String> claims)  {
        String[] durationArray = duration.split("|");
        int durationTIme;
        if (durationArray.length==3){
            try{
                durationTIme = Integer.parseInt(durationArray[0]);
            }catch (NumberFormatException e){
               durationTIme = 0;
            }
            durationTIme = switch (durationArray[2]) {
                case "M" ->durationTIme*60*1000;
                case "H" ->durationTIme*60*60*1000;
                case "D" ->durationTIme*24*60*60*1000;
                default -> 0;
            };
        Algorithm algorithm = switch (signMethod){
           case "HMAC"->Algorithm.HMAC256(jwtKey);
            default -> throw new IllegalStateException("Unexpected value: " + signMethod);
        };
            JWTCreator.Builder tokenBuilder = JWT
                    .create()
                    .withExpiresAt(new Date(System.currentTimeMillis()+durationTIme))
                    .withIssuer(appName)
                    .withSubject("ACCESS");
            claims.forEach((k,v)->{
                    tokenBuilder.withClaim(k,v);
            });
            return tokenBuilder.sign(algorithm);
        }
        return null;
    }

    @Override
    public boolean isCorrectToken(String token) {
        Algorithm algorithm = switch (signMethod){
            case "HMAC"->Algorithm.HMAC256(jwtKey);
            default -> throw new IllegalStateException("Unexpected value: " + signMethod);
        };
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try{
            DecodedJWT verify = jwtVerifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Map<String, String> getClaims(ArrayList<String> claimsKeys,String token) throws Exception {
        Map<String,String> claims = new HashMap<>();
        Algorithm algorithm = switch (signMethod){
            case "HMAC"->Algorithm.HMAC256(jwtKey);
            default -> throw new AppException("Unexpected value: " + signMethod, HttpStatus.FORBIDDEN.value());
        };
        try{
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT verify = jwtVerifier.verify(token);
            claimsKeys.forEach(v->{
                claims.put(v, String.valueOf(verify.getClaim(v)));
            });
            return claims;
        }catch (TokenExpiredException e) {
            throw new Exception(e.getLocalizedMessage());
        }
        catch (Exception e){
            throw new IllegalArgumentException();
        }
        //return null;
    }

    @Override
    public void setForUser(String forUser) {
        this.forUser = forUser;
    }
}
