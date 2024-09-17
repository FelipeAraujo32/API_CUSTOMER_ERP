package com.customer.customer_api.service;

import java.util.UUID;
import org.springframework.stereotype.Service;

import com.customer.customer_api.convert.CustomerModelConvert;
import com.customer.customer_api.dto.request.CustomerRequestDto;
import com.customer.customer_api.dto.response.CustomerResponsetDto;
import com.customer.customer_api.entity.CustomerModel;
import com.customer.customer_api.repository.CustomerRepository;
import com.customer.customer_api.service.business_exception.CustomerNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ValidationDataService validationDataService;
    private final CustomerModelConvert customerModelConvert;

    public CustomerService(CustomerRepository customerRepository, ValidationDataService validationDataService,
            CustomerModelConvert customerModelConvert) {
        this.customerRepository = customerRepository;
        this.validationDataService = validationDataService;
        this.customerModelConvert = customerModelConvert;
    }

    public CustomerResponsetDto findByCustomer(UUID customerId) {
        CustomerModel customerModel = findCustomerId(customerId);
        return customerModelConvert.toCustomerResponse(customerModel);
    }

    @Transactional
    public CustomerResponsetDto createCustomer(CustomerRequestDto customerRequestDto) {
        CustomerModel convertCustomerModel = customerModelConvert.toCustomerModel(customerRequestDto);
        validationDataService.inputDataValidation(convertCustomerModel);
        CustomerModel customerModel = customerRepository.save(convertCustomerModel);
        return customerModelConvert.toCustomerResponse(customerModel);
    }

    public CustomerResponsetDto updateCustomer(UUID customerId, CustomerRequestDto customerRequestDto) throws CustomerNotFoundException {
            this.findCustomerId(customerId);
            CustomerModel convertCustomerModel = customerModelConvert.toCustomerModel(customerRequestDto);
            validationDataService.inputDataValidation(convertCustomerModel);
            convertCustomerModel.setCustomerId(customerId);
            CustomerModel customerModel= customerRepository.save(convertCustomerModel);
            return customerModelConvert.toCustomerResponse(customerModel);
    }

    public void deleteCustomer(UUID uuid) {
        CustomerModel customerDb = findCustomerId(uuid);
        this.customerRepository.delete(customerDb);
    }

    private CustomerModel findCustomerId(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer with customerId " + customerId + " not found."));
    }
}