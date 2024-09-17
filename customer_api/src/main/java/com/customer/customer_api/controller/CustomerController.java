package com.customer.customer_api.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.customer.customer_api.dto.request.CustomerRequestDto;
import com.customer.customer_api.dto.response.CustomerResponsetDto;
import com.customer.customer_api.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer Controlelr", description = "API for customer management.")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get a customer by customerId", description = "Retrieve a specific customer based on its CustomerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieve a specific customer based on its CustomerId"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<CustomerResponsetDto> findByCustomer(@PathVariable UUID customerId) {
        CustomerResponsetDto customerResponset = customerService.findByCustomer(customerId);
        return ResponseEntity.ok(customerResponset);
    }

    @PostMapping()
    @Operation(summary = "Create a new customer", description = "Createa a new customer and return the created customes's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid customer data provided")
    })
    public ResponseEntity<CustomerResponsetDto> createCustomer(
            @Valid @RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponsetDto createCustomer = customerService.createCustomer(customerRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(createCustomer.getCutomerId())
                .toUri();

        return ResponseEntity.created(location).body(createCustomer);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update a customer", description = "Update the data of an existing customer based on its customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "422", description = "Invalid customer data provided")
    })
    public ResponseEntity<CustomerResponsetDto> updateCustomer(
            @Valid @RequestBody CustomerRequestDto customerRequestDto, @PathVariable UUID customerIUuid) {
        CustomerResponsetDto updateCustomer = customerService.updateCustomer(customerIUuid, customerRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateCustomer);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete a customer", description = "Delete an existing customer based on its UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

}
