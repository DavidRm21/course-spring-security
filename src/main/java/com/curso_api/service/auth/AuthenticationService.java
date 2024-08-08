package com.curso_api.service.auth;

import com.curso_api.dto.RegisterUser;
import com.curso_api.dto.SaveUser;
import com.curso_api.persistence.entity.User;
import com.curso_api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IUserService userService;
    private final JwtService jwtService;


    public RegisterUser registerOneCustomer(SaveUser newUser) {
        User user  = userService.registerOneCustomer(newUser);

        RegisterUser userDto = RegisterUser.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();

        String jwt = jwtService.generateToken(user);
        userDto.setJwt(jwt);

        return userDto;
    }
}
