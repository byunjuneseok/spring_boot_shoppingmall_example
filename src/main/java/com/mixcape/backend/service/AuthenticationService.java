package com.mixcape.backend.service;

import com.mixcape.backend.entity.User;

public interface AuthenticationService {
    User authenticate(String token);
}
