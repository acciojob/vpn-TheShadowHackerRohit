package com.driver.model;

import javax.persistence.*;

public class Connection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    ServiceProvider serviceProvider;

    public Connection() {
    }
}
