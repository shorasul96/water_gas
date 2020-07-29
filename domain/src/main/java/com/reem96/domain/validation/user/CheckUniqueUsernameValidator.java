package com.reem96.domain.validation.user;

import com.reem96.domain.dto.UserDto;
import com.reem96.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckUniqueUsernameValidator implements ConstraintValidator<CheckUniqueUsername, UserDto> {

    @Autowired
    private UserRepository userRepository;

   @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        if(userDto.getId() == null && userRepository.findByUsernameIgnoreCase(userDto.getUsername()).isPresent()){
            return false;
        }
        return true;
    }
}
