package com.example.entity.socialLogin.oauth.controller;

import com.example.entity.domain.User;
import com.example.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final UserRepository userRepository;

    @GetMapping("/members")
    public ResponseEntity<List<User>> members() {
        List<User> result = userRepository.findAll();
        return ResponseEntity.ok().body(result);
    }
}
