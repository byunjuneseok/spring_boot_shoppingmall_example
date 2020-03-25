package com.mixcape.backend.service.impl;

import com.mixcape.backend.entity.User;
import com.mixcape.backend.exception.UnauthorizedException;
import com.mixcape.backend.repository.UserRepository;
import com.mixcape.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String token) {
        try {
            String[] split = token.split(" ");
            String type = split[0];
            String credential = split[1];
            if ("Basic".equalsIgnoreCase(type)) {
                String decoded = new String(Base64Utils.decodeFromString(credential));
                String[] usernameAndPassword = decoded.split(":");
                User user = userRepository.findByUsernameAndPassword(
                        usernameAndPassword[0],
                        usernameAndPassword[1]
                );
                if (user == null) {
                    throw new UnauthorizedException("Invalid Crediential");
                }
                else {
                    return user;
                }
            }
            else {
                throw new UnauthorizedException("Unsupported Type : " + type);
            }
        }
        catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            throw new UnauthorizedException("Invalid Credential");
        }
    }
}
