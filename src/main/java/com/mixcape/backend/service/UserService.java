package com.mixcape.backend.service;

import com.mixcape.backend.entity.User;

public interface UserService {
    User join(String username, String password);
    User updatePassword(Integer userID, String password);
    void withdraw(Integer userID);
}
