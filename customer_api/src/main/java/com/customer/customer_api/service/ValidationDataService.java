package com.customer.customer_api.service;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.customer.customer_api.entity.CustomerModel;
import com.customer.customer_api.service.business_exception.BusinessException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class ValidationDataService {
    
    private final Validator validator;

    public ValidationDataService(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void inputDataValidation(CustomerModel customer) throws BusinessException{
        Set<ConstraintViolation<CustomerModel>> stringValidations = validator.validate(customer);

        if(!stringValidations.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CustomerModel> validations : stringValidations){
                sb.append(validations.getMessage()).append("\n");
            }
            throw new BusinessException(sb.toString());
        }
    }
}
