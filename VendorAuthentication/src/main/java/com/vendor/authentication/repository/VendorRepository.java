package com.vendor.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendor.authentication.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{
	Vendor findByEmail(String email);
}
