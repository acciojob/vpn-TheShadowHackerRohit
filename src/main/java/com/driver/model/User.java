package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String username;

    private String password;

    private String originalIP;

    private String maskedIP;

    private Boolean connected;

    private Country country;

    @ManyToMany
    @JoinColumn
    List<ServiceProvider> serviceProviderList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Connection> connectionList = new ArrayList<>();

    public User() {
    }

    public User(int id, String username, String password, String originalIP, String maskedIP, Boolean connected, Country country, List<ServiceProvider> serviceProviderList, List<Connection> connectionList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.originalIP = originalIP;
        this.maskedIP = maskedIP;
        this.connected = connected;
        this.country = country;
        this.serviceProviderList = serviceProviderList;
        this.connectionList = connectionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalIP() {
        return originalIP;
    }

    public void setOriginalIP(String originalIP) {
        this.originalIP = originalIP;
    }

    public String getMaskedIP() {
        return maskedIP;
    }

    public void setMaskedIP(String maskedIP) {
        this.maskedIP = maskedIP;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }
}
