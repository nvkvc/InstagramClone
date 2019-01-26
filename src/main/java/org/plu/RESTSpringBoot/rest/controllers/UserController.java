package org.plu.RESTSpringBoot.rest.controllers;

import org.plu.RESTSpringBoot.mappers.UserDtoToApplicationUserMapper;
import org.plu.RESTSpringBoot.model.dto.FollowDto;
import org.plu.RESTSpringBoot.model.dto.UserDto;
import org.plu.RESTSpringBoot.repository.ApplicationUserRepository;
import org.plu.RESTSpringBoot.model.entities.ApplicationUser;
import org.plu.RESTSpringBoot.rest.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Kada se novi koristinik registruje dodaje ga u bazu.
     * Samo pre snimanja baze radi enkripciju password
     */
    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserDto user) {
        userDetailsService.signUp(new UserDtoToApplicationUserMapper().map(user));
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto user) {
        return userDetailsService.login(new UserDtoToApplicationUserMapper().map(user));
    }

    @PostMapping("/update")
    public boolean updateUser(@RequestBody UserDto user) {
        if(user == null) {
            return false;
        }
        userDetailsService.updateUserInfo(user);
        return true;
    }

    @PostMapping("/follow")
    public boolean follow(@RequestBody FollowDto followDto) {
        if(followDto == null) {
            return false;
        }

        if(followDto.getFollowerUsername() == null || followDto.getFollowingUsername() == null) {
            return false;
        }

        return userDetailsService.follow(followDto);
    }

    @PostMapping("/unfollow")
    public boolean unfollow(@RequestBody FollowDto followDto) {
        if(followDto == null) {
            return false;
        }

        if(followDto.getFollowerUsername() == null || followDto.getFollowingUsername() == null) {
            return false;
        }

        return userDetailsService.unfollow(followDto);
    }

    @PostMapping(path = "/uploadPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean uploadPhoto(@RequestParam("photo") MultipartFile file,
                                         @RequestParam("username") String username)
            throws IOException {
        return userDetailsService.uploadPhoto(file, username);
    }
}