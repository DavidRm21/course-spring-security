package com.curso_api.service;

import com.curso_api.dto.SaveUser;
import com.curso_api.persistence.entity.User;

public interface IUserService {
    User registerOneCustomer(SaveUser newUser);
}
