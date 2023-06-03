package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {

        Admin admin = new Admin(username,password);
        return adminRepository1.save(admin);
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        //add a serviceProvider under the admin and return updated admin
        ServiceProvider serviceProvider = new ServiceProvider(providerName);
        ServiceProvider savedServiceProvider = serviceProviderRepository1.save(serviceProvider);

        Admin admin = adminRepository1.findById(adminId).get();
        admin.getServiceProviderList().add(savedServiceProvider);

        return admin;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
        return null;
    }
}
