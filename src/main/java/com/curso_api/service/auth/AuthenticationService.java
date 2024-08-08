package com.curso_api.service.auth;

import com.curso_api.dto.RegisterUser;
import com.curso_api.dto.SaveUser;
import com.curso_api.persistence.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public RegisterUser registerOneCustomer(SaveUser newUser) {
        User user  = userService.registerOneCustomer(newUser);
        return null;
    }
}
