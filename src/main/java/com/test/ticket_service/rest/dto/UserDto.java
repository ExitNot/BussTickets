package com.test.ticket_service.rest.dto;

import com.test.ticket_service.data.entity.User;
import org.springframework.hateoas.RepresentationModel;

public class UserDto extends RepresentationModel<UserDto> implements Dto<User> {

    private Long id;
    private String name;
    private String surname;
    private String middlename;

    public UserDto(Long id, String name, String surname, String middlename) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Override
    public User createEntity() {
        return new User(name, surname, middlename);
    }
}
