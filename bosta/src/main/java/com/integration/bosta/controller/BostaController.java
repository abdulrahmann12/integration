package com.integration.bosta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integration.bosta.dto.CustomUserDataDTO;
import com.integration.bosta.service.BostaIntegrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bosta")
@RequiredArgsConstructor
public class BostaController {

	private final BostaIntegrationService bostaIntegrationService;
	
    @GetMapping("/login")
    public String login() {
        return bostaIntegrationService.loginToBosta();
    }
    
    @GetMapping("/full-data")
    public CustomUserDataDTO fullDATA() {
        return bostaIntegrationService.getAllData();
    }
    
    @GetMapping("/orders")
    public String getAllOrders() {
        return bostaIntegrationService.getAllOrders();
    }
}
