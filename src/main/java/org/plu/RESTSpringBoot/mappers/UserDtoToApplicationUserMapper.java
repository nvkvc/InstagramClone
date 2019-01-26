package org.plu.RESTSpringBoot.mappers;


import org.plu.RESTSpringBoot.model.dto.UserDto;
import org.plu.RESTSpringBoot.model.entities.ApplicationUser;

public class UserDtoToApplicationUserMapper implements Mapper<UserDto, ApplicationUser> {

    @Override
    public ApplicationUser map(UserDto from) {
        if(from == null) {
            return null;
        }
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(from.getUsername());
        applicationUser.setPassword(from.getPassword());
        applicationUser.setEmail(from.getEmail());
        return applicationUser;
    }
}
