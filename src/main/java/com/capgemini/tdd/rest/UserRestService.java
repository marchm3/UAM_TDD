package com.capgemini.tdd.rest;

import com.capgemini.tdd.dao.entities.UserBE;
import com.capgemini.tdd.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/players")
public class UserRestService
{

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{name}")
    public UserBE findById(@PathVariable String name) {
        return userService.findByName(name);
    }

    @GetMapping(path="/", produces = "application/json")
    public List<UserBE> getEmployees()
    {
        return userService.findAll();
    }

}
