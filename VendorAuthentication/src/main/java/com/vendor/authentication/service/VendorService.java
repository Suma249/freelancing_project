package com.vendor.authentication.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vendor.authentication.model.Vendor;
import com.vendor.authentication.web.dto.VendorRegistrationDto;

public interface VendorService extends UserDetailsService{
	Vendor save(VendorRegistrationDto vendor);
}
