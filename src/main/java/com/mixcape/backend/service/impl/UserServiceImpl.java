package com.mixcape.backend.service.impl;

import com.mixcape.backend.entity.User;
import com.mixcape.backend.exception.AlreadyExistsException;
import com.mixcape.backend.exception.UnauthorizedException;
import com.mixcape.backend.repository.UserRepository;
import com.mixcape.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User join(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new AlreadyExistsException("Duplicated username.");
        }
        return userRepository.save(new User(username, password));
    }

    @Override
    public User updatePassword(Integer userID, String password) {
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException(userID.toString()));
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public void withdraw(Integer userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException(userID.toString()));
        userRepository.delete(user);
    }
}
