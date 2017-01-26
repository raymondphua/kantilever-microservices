package com.infosupport.team2.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Raymond Phua on 25-1-2017.
 */
public class CustomerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void generateKey_DefaultCustomerFromTestBuilder_CreatesCorrectlyGeneratedKey() {
        //ARRANGE
        Customer customer = TestBuilders.customer();
        String expectedKey = "cst-BarF-1234AB-10";

        //ACT
        customer.generateKey();

        //ASSERT
        assertThat(customer.getCustomerKey(), is(expectedKey));
    }

    @Test
    public void generateKey_CustomerWithNoAddress_ShouldThrowException() {
        Customer customer = TestBuilders.customer().builder().address(null).build();

        thrown.expect(NullPointerException.class);
        customer.generateKey();
    }
}