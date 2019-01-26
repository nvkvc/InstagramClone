package org.plu.RESTSpringBoot.mappers;

import org.plu.RESTSpringBoot.model.dto.UserDto;
import org.plu.RESTSpringBoot.model.entities.ApplicationUser;

public class ApplicaionUserToUserDtoMapper implements Mapper<ApplicationUser, UserDto> {

    @Override
    public UserDto map(ApplicationUser from) {
        if(from == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(from.getId());
        userDto.setEmail(from.getEmail());
        userDto.setPassword(from.getPassword());
        userDto.setUsername(from.getUsername());
        return userDto;
    }
}
