package com.vendor.authentication.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vendor.authentication.model.*;
import com.vendor.authentication.repository.VendorRepository;
import com.vendor.authentication.web.dto.VendorRegistrationDto;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Vendor save(VendorRegistrationDto vendorDto) {
		Vendor vendor=new Vendor(vendorDto.getFirstName(),
				vendorDto.getLastName(),vendorDto.getEmail(),
				passwordEncoder.encode(vendorDto.getPassword()), 
				Arrays.asList(new Role("ROLE_USER")));
		return vendorRepo.save(vendor);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Vendor vendor=vendorRepo.findByEmail(username);
		if(vendor == null)
			throw new UsernameNotFoundException("invalid username or password");
		return new org.springframework.security.core.userdetails.User(vendor.getEmail(), vendor.getPassword(), mapRolesToAuthorities(vendor.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	

	/*public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}*/

}
