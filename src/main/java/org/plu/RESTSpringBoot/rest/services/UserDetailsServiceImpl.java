package org.plu.RESTSpringBoot.rest.services;

import org.plu.RESTSpringBoot.mappers.ApplicaionUserToUserDtoMapper;
import org.plu.RESTSpringBoot.model.dto.FollowDto;
import org.plu.RESTSpringBoot.model.dto.UserDto;
import org.plu.RESTSpringBoot.repository.ApplicationUserRepository;
import org.plu.RESTSpringBoot.model.entities.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

    public void signUp(ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }


    public UserDto login(ApplicationUser user) {
        ApplicationUser existingUser = applicationUserRepository.findByUsername(user.getUsername());
        if (existingUser == null)
            return new UserDto();

        if(bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())){
            return new ApplicaionUserToUserDtoMapper().map(existingUser);
        }

        return null;
    }

    public void updateUserInfo(UserDto newUserInformations) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(newUserInformations.getUsername());
        if(applicationUser != null) {
            applicationUser.setEmail(newUserInformations.getEmail());
            applicationUser.setPassword(bCryptPasswordEncoder.encode(newUserInformations.getPassword()));
            applicationUserRepository.save(applicationUser);
        }
    }

    public boolean follow(FollowDto followDto) {
        ApplicationUser follower = applicationUserRepository.findByUsername(followDto.getFollowerUsername());
        ApplicationUser following = applicationUserRepository.findByUsername(followDto.getFollowingUsername());

        if(follower == null || following == null) {
            return false;
        }

        if (follower.getFollowing().contains(following)) {
            return false;
        }

        follower.getFollowing().add(following);
        applicationUserRepository.save(follower);

        ApplicationUser fling = applicationUserRepository.findByUsername(followDto.getFollowingUsername());
        System.out.println(fling.getFollowedBy().size());

        return true;
    }

    public boolean unfollow(FollowDto followDto) {
        ApplicationUser follower = applicationUserRepository.findByUsername(followDto.getFollowerUsername());
        ApplicationUser following = applicationUserRepository.findByUsername(followDto.getFollowingUsername());

        if(follower == null || following == null) {
            return false;
        }

        if (!follower.getFollowing().contains(following)) {
            return false;
        }

        follower.getFollowing().remove(following);
        applicationUserRepository.save(follower);

        ApplicationUser fling = applicationUserRepository.findByUsername(followDto.getFollowingUsername());
        System.out.println(fling.getFollowedBy().size());

        return true;
    }

    public boolean uploadPhoto(MultipartFile photo, String username) throws IOException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if(applicationUser == null) {
            return false;
        }
        File saveFile = new File("E:/Program Files/profile/"+applicationUser.getId()+"." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1));
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        fileOutputStream.write(photo.getBytes());
        fileOutputStream.close();
        System.out.println(username);
        return true;
    }

    public int getFollowerNr(String username) {
        if(username == null) {
            return -1;
        }

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if(applicationUser == null) {
            return -1;
        }

        return applicationUser.getFollowedBy().size();
    }

    public int getFollowingNr(String username) {
        if(username == null) {
            return -1;
        }

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if(applicationUser == null) {
            return -1;
        }

        return applicationUser.getFollowing().size();
    }
}