package top.yekongle.jwt.service;

import top.yekongle.jwt.model.entity.User;

public interface AuthService {
    User register( User userToAdd );
    String login( String username, String password );
}

