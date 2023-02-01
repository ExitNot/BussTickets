package com.test.ticket_service.business.service;

import com.test.ticket_service.business.CrudService;
import com.test.ticket_service.data.entity.User;

import java.util.Optional;

public interface UserService extends CrudService<User, Long> {

    Optional<User> readByFields(String name, String surname, String middlename);
}
