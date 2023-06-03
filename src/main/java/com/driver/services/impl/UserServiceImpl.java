package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;

    @Autowired
    ServiceProviderRepository serviceProviderRepository3;

    @Autowired
    CountryRepository countryRepository3;

    /*
    Users: Register themselves using the registerUser endpoint. Each time you register a user,
     you should create a new Country object based on the given country name and assign it as the original country of the user.
      Note that the service provider attribute of the country in this case would be null.
       Subscribe to a service provider using the subscribe endpoint
     */

    @Override
    public User register(String username, String password, String countryName) throws Exception{
        countryName = countryName.toUpperCase();
        if(!checkCountryExist(countryName)){
            throw  new Exception("Country not found");
        }
        CountryName countryName1 = CountryName.valueOf(countryName);

        Country country = new Country();
        country.setCountryName(countryName1);
        country.setCode(countryName1.toCode());

        Country savedCountry = countryRepository3.save(country);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCountry(savedCountry);

        User savedUser = userRepository3.save(user);

        return savedUser;
    }
    private boolean checkCountryExist(String countryName) {
        if(countryName.equals("IND")||countryName.equals("USA")||countryName.equals("AUS")||
                countryName.equals("CHI")||countryName.equals("JPN")){
            return true;
        }
        return false;
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {

        User user = userRepository3.findById(userId).get();
        ServiceProvider serviceProvider = serviceProviderRepository3.findById(serviceProviderId).get();

        serviceProvider.getUsers().add(user);
        user.getServiceProviderList().add(serviceProvider);

        return user;
    }
}
