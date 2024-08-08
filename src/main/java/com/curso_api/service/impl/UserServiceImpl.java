package com.curso_api.service.impl;

import com.curso_api.dto.SaveUser;
import com.curso_api.exception.InvalidPasswordException;
import com.curso_api.persistence.entity.User;
import com.curso_api.persistence.repository.IUserRepository;
import com.curso_api.persistence.util.Role;
import com.curso_api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public User registerOneCustomer(SaveUser newUser) {

        validatePassword(newUser);

        User user = User.builder()
                .name(newUser.getName())
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .role(Role.ROLE_CUSTOMER)
                .build();

        userRepository.save(user);
        return null;
    }

    private void validatePassword(SaveUser newUser) {
        if (!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Contraseña no coincide.");
        }
        if (!newUser.getPassword().equals(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("La contraseña no coincide");
        }

    }
}
