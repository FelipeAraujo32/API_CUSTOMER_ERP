package com.customer.customer_api.convert;

import org.springframework.stereotype.Component;

import com.customer.customer_api.dto.request.CustomerRequestDto;
import com.customer.customer_api.dto.response.CustomerResponsetDto;
import com.customer.customer_api.entity.CustomerModel;

@Component
public class CustomerModelConvert {
    
    public CustomerModel toCustomerModel(CustomerRequestDto customerDto){
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerId(customerDto.getCutomerId());
        customerModel.setName(customerDto.getName());
        customerModel.setEmail(customerDto.getEmail());
        customerModel.setPhone(customerDto.getPhone());
        customerModel.setCep(customerDto.getCep());
        return customerModel;
    }

    public CustomerResponsetDto toCustomerResponse(CustomerModel customerModel){
        CustomerResponsetDto customerResponse = new CustomerResponsetDto();
        customerResponse.setCutomerId(customerModel.getCustomerId());
        customerResponse.setName(customerModel.getName());
        return customerResponse;
    }
}
