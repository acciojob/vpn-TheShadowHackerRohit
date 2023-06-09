package com.driver.model;
// Note: Do not write @Enumerated annotation above CountryName in this model.

import javax.persistence.*;

@Entity
public class Country {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    CountryName countryName;

    private String code;

    @OneToOne(mappedBy = "originalCountry",cascade = CascadeType.ALL)
    private User user;

    @ManyToOne
    @JoinColumn
    ServiceProvider serviceProvider;

    public Country() {
    }


    public Country(CountryName countryName) {
        this.countryName = countryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
