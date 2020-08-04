package com.reem96.domain.validation.gas;

import com.reem96.domain.dto.GasDto;
import com.reem96.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckUserForGasValidator implements ConstraintValidator<CheckUserForGas, GasDto> {

    @Autowired
    private UserRepository userRepository;

   @Override
    public boolean isValid(GasDto gasDto, ConstraintValidatorContext context) {
       if (!(gasDto.getUserId() != null && userRepository.findById(gasDto.getUserId()).isPresent())) {
           context.disableDefaultConstraintViolation();
           context.buildConstraintViolationWithTemplate("User id not found").addConstraintViolation();
           return false;
       }
       return true;
   }
}
