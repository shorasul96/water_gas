package com.reem96.domain.validation.water;

import com.reem96.domain.dto.WaterDto;
import com.reem96.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckUserForWaterValidator implements ConstraintValidator<CheckUserForWater, WaterDto> {

    @Autowired
    private UserRepository userRepository;

   @Override
    public boolean isValid(WaterDto waterDto, ConstraintValidatorContext context) {
       if (!(waterDto.getUserId() != null && userRepository.findById(waterDto.getUserId()).isPresent())) {
           context.disableDefaultConstraintViolation();
           context.buildConstraintViolationWithTemplate("User id not found").addConstraintViolation();
           return false;
       }
       return true;
   }
}
