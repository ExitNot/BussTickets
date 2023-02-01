package com.test.ticket_service.rest.controller;

import com.test.ticket_service.business.service.UserService;
import com.test.ticket_service.data.entity.User;
import com.test.ticket_service.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;
    private final PagedResourcesAssembler<User> pagedResourcesAssembler;

    @Autowired
    public UserController(UserService userService, PagedResourcesAssembler<User> pagedResourcesAssembler) {
        this.userService = userService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping("/signUp")
    public Long signUpPage(@RequestBody UserDto data) {
        User user = userService.create(data.createEntity());
        // Missed pwd processes
        return user.getId();
    }
}
