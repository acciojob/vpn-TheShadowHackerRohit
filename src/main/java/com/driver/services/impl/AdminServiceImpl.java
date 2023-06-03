package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
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

    /*
    Administrators: Register themselves using the registerAdmin endpoint.
     Add service providers using the addServiceProvider endpoint.
      Add countries to the service providers using the addCountry endpoint.
       Each time you add a country, you should create a new Country object based on the given country name
        and add it to the country list of the service provider. Note that the user attribute of the country in this case would be null.
     */

    @Override
    public Admin register(String username, String password) {

        Admin admin = new Admin(username,password);
        return adminRepository1.save(admin);
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        //add a serviceProvider under the admin and return updated admin
        ServiceProvider serviceProvider = new ServiceProvider(providerName);


        Admin admin = adminRepository1.findById(adminId).get();
        serviceProvider.setAdmin(admin);
        ServiceProvider savedServiceProvider = serviceProviderRepository1.save(serviceProvider);
        admin.getServiceProviderList().add(serviceProvider);


        return admin;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
        //add a country under the serviceProvider and return respective service provider
        //country name would be a 3-character string out of ind, aus, usa, chi, jpn. Each character can be in uppercase or lowercase.
        // You should create a new Country object based on the given country name and add it to the country list of the service provider.
        // Note that the user attribute of the country in this case would be null.
        //In case country name is not amongst the above-mentioned strings, throw "Country not found" exception

        ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).get();

        countryName = countryName.toUpperCase();
        if(!checkCountryExist(countryName)){
            throw  new Exception("Country not found");
        }
        CountryName countryName1 = CountryName.valueOf(countryName);

        Country country = new Country();
        country.setCountryName(countryName1);
        country.setCode(countryName1.toCode());
        country.setServiceProvider(serviceProvider);

        Country savedCountry = countryRepository1.save(country);
        serviceProvider.getCountryList().add(country);

        return serviceProvider;
    }

    private boolean checkCountryExist(String countryName) {
        if(countryName.equals("IND")||countryName.equals("USA")||countryName.equals("AUS")||
                countryName.equals("CHI")||countryName.equals("JPN")){
            return true;
        }
        return false;
    }
}
