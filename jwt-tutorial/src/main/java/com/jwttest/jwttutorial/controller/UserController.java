package com.jwttest.jwttutorial.controller;


import com.jwttest.jwttutorial.dto.UserDto;
import com.jwttest.jwttutorial.entity.User;
import com.jwttest.jwttutorial.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserDto> getMyUserInfo(HttpServletRequest request) {
        // Token을 통해 유저이름과 닉네임, 권한을 조회하여 반환 (단, 본인한정)
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }
//    {
//        "username": "admin",
//            "nickname": "admin",
//            "authorityDtoSet": [
//        {
//            "authorityName": "ROLE_ADMIN"
//        },
//        {
//            "authorityName": "ROLE_USER"
//        }
//    ]
//    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }
}
