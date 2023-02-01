package com.test.ticket_service.business.service.impl;

import com.test.ticket_service.business.service.UserService;
import com.test.ticket_service.data.UserRepo;
import com.test.ticket_service.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User create(User data) {
        return userRepo.save(data);
    }

    @Override
    public Optional<User> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> readByFields(String name, String surname, String middlename) {
        User data = new User(name, surname, middlename);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<User> example = Example.of(data);
        Optional<User> oUser = userRepo.findOne(example);

        if (oUser.isEmpty())
            return Optional.empty();

        return oUser;
    }

    @Override
    public Page<User> readAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(Long id, User newData) {
    }

    @Override
    public void delete(Long id) {
    }
}
