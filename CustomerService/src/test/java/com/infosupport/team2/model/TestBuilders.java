package com.infosupport.team2.model;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
public class TestBuilders {

    public static Customer customer() {
        Address address = Address.builder().zip("1234AB").houseNumber("10").build();

        return Customer.builder().name("Foo Bar").address(address).build();
    }
}
