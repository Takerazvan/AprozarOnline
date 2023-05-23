package com.codecool.backend.security.token;

import com.codecool.backend.model.users.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Token generateToken(AppUser user){
        String tokenString = UUID.randomUUID().toString();
         Token token=Token.builder().token(tokenString).user(user).build();
         saveToken(token);
         return token;

    }

}
