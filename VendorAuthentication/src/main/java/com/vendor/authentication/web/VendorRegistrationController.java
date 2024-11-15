package com.vendor.authentication.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vendor.authentication.service.VendorService;
import com.vendor.authentication.web.dto.VendorRegistrationDto;

@Controller
@RequestMapping("/registration")
public class VendorRegistrationController {

	private VendorService vendorService;//constructor based dependency injection
	public VendorRegistrationController(VendorService vendorService) {
		super();
		this.vendorService = vendorService;
	}
	
	@ModelAttribute("vendor")//thymeleaf gets vendor object from here
	public VendorRegistrationDto vendorRegistrationDto() {
		return new VendorRegistrationDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerVendor(@ModelAttribute("vendor") VendorRegistrationDto vendorDto) {
		vendorService.save(vendorDto);
		return "redirect:/registration?success";
	}
	
}
