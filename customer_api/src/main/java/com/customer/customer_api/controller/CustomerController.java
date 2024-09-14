package com.customer.customer_api.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.customer.customer_api.dto.CustomerDTO;
import com.customer.customer_api.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(
    name = "Customer Controlelr", 
    description = "API for customer management.")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping()
    @Operation(
        summary = "Get all Customers", 
        description = "Retrieve a list of all registered customers")
    public ResponseEntity<List<CustomerDTO>> findAllCustomers() {

        var findAllCustomers = service.findAllCustomer();

        var CustomerDTO = findAllCustomers.stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CustomerDTO);
    }

    @GetMapping("/{uuid}")
    @Operation(
        summary = "Get a customer by UUID",
        description = "Retrieve a specific customer based on its UUID")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Retrieve a specific customer based on its UUID"),
        @ApiResponse(
            responseCode = "404",
            description = "Customer not found")
    })
    public ResponseEntity<CustomerDTO> findCustomer(@PathVariable UUID uuid) {

        var findCustomer = service.findByCustomer(uuid);
        return ResponseEntity.ok(new CustomerDTO(findCustomer));
    }

    @PostMapping()
    @Operation(
        summary = "Create a new customer",
        description = "Createa a new customer and return the created customes's data")
        @ApiResponses(value = {
            @ApiResponse(
                responseCode = "201", 
                description = "Customer created successfully"),
        @ApiResponse(
            responseCode = "422",
            description = "Invalid customer data provided")
        })
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {

        var createCustomer = service.createCustomer(customerDTO.toCustomer());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(createCustomer.getUuid())
                .toUri();

        return ResponseEntity.created(location).body(new CustomerDTO(createCustomer));
    }

    @PutMapping("/{uuid}")
    @Operation(
        summary = "Update a customer", 
        description = "Update the data of an existing customer based on its UUID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", 
            description = "Customer updated successfully"),
            @ApiResponse(responseCode = "404", 
            description = "Customer not found"),
            @ApiResponse(responseCode = "422", 
            description = "Invalid customer data provided")
    })
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable UUID uuid,
            @Valid @RequestBody CustomerDTO customerDTO) throws Exception {

        var updateCustomer = service.updateCustomer(uuid,customerDTO.toCustomer());
        return ResponseEntity.status(HttpStatus.OK).body(new CustomerDTO(updateCustomer));
    }

    @DeleteMapping("/{uuid}")
    @Operation(
        summary = "Delete a customer", 
        description = "Delete an existing customer based on its UUID")
    @ApiResponses(value = { 
        @ApiResponse(
            responseCode = "204", 
            description = "User deleted successfully"),
        @ApiResponse(
            responseCode = "404", 
            description = "User not found")
    })
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID uuid) {
        service.deleteCustomer(uuid);
        return ResponseEntity.noContent().build();
    }

}
