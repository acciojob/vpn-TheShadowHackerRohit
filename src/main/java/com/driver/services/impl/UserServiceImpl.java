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

    @Override
    public User register(String username, String password, String countryName) throws Exception {
        User user = new User();
        if (countryName.equalsIgnoreCase("IND") || countryName.equalsIgnoreCase("USA") || countryName.equalsIgnoreCase("JPN") || countryName.equalsIgnoreCase("AUS") || countryName.equalsIgnoreCase("CHI")) {
            user.setUsername(username);
            user.setPassword(password);

            Country country = new Country(); //linking
            if (countryName.equalsIgnoreCase("IND")) {
                country.setCountryName(CountryName.IND);
                country.setCode(CountryName.IND.toCode());
            }
            if (countryName.equalsIgnoreCase("USA")) {
                country.setCountryName(CountryName.USA);
                country.setCode(CountryName.USA.toCode());
            }
            if (countryName.equalsIgnoreCase("JPN")) {
                country.setCountryName(CountryName.JPN);
                country.setCode(CountryName.JPN.toCode());
            }
            if (countryName.equalsIgnoreCase("CHI")) {
                country.setCountryName(CountryName.CHI);
                country.setCode(CountryName.CHI.toCode());
            }
            if (countryName.equalsIgnoreCase("AUA")) {
                country.setCountryName(CountryName.AUS);
                country.setCode(CountryName.AUS.toCode());
            }

            country.setUser(user); //reverse linking
            user.setOriginalCountry(country);
            user.setConnected(false); //vpn main goal

            String code = country.getCode() + "." + userRepository3.save(user).getId();
            user.setOriginalIp(code); //new

            userRepository3.save(user);


        } else {  //means user is null
            throw new Exception("Country not found");
        }
        return user;
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {

        User user = userRepository3.findById(userId).get();

        ServiceProvider serviceProvider = serviceProviderRepository3.findById(serviceProviderId).get();
        user.getServiceProviderList().add(serviceProvider);
        serviceProvider.getUsers().add(user);
        serviceProviderRepository3.save(serviceProvider);
        return user;
    }
}


















//package com.driver.services.impl;
//
//import com.driver.model.Country;
//import com.driver.model.CountryName;
//import com.driver.model.ServiceProvider;
//import com.driver.model.User;
//import com.driver.repository.CountryRepository;
//import com.driver.repository.ServiceProviderRepository;
//import com.driver.repository.UserRepository;
//import com.driver.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    UserRepository userRepository3;
//
//    @Autowired
//    ServiceProviderRepository serviceProviderRepository3;
//
//    @Autowired
//    CountryRepository countryRepository3;
//
//    /*
//    Users: Register themselves using the registerUser endpoint. Each time you register a user,
//     you should create a new Country object based on the given country name and assign it as the original country of the user.
//      Note that the service provider attribute of the country in this case would be null.
//       Subscribe to a service provider using the subscribe endpoint
//     */
//
//    @Override
//    public User register(String username, String password, String countryName) throws Exception{
//        countryName = countryName.toUpperCase();
//        if(!checkCountryExist(countryName)){
//            throw  new Exception("Country not found");
//        }
//        CountryName countryName1 = CountryName.valueOf(countryName);
//
//        Country country = new Country();
//        country.setCountryName(countryName1);
//        country.setCode(countryName1.toCode());
//
//        Country savedCountry = countryRepository3.save(country);
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setOriginalCountry(savedCountry);
//
//        User savedUser = userRepository3.save(user);
//
//        return savedUser;
//    }
//    private boolean checkCountryExist(String countryName) {
//        if(countryName.equals("IND")||countryName.equals("USA")||countryName.equals("AUS")||
//                countryName.equals("CHI")||countryName.equals("JPN")){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public User subscribe(Integer userId, Integer serviceProviderId) {
//
//        User user = userRepository3.findById(userId).get();
//        ServiceProvider serviceProvider = serviceProviderRepository3.findById(serviceProviderId).get();
//
//        serviceProvider.getUsers().add(user);
//        user.getServiceProviderList().add(serviceProvider);
//
//        return user;
//    }
//}
