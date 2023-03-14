package com.test.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-controller")
public class UserController {

    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hello Test security");
    }
}
