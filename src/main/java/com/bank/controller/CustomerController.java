package com.bank.controller;

import com.bank.service.CustomerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping()
@Validated
public class CustomerController {
    private final CustomerService service;
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    CustomerController(CustomerService aService) {
        this.service = aService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCustomers() {
        return gson.toJson(service.getAllCustomers());
    }

    @GetMapping(value = "/{ids}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCustomersById(@PathVariable @NotBlank List<Integer> ids) {
        return gson.toJson(service.getCustomersById(ids));
    }
}
