package com.infosupport.team2.model;

import builders.TestBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Raymond Phua on 25-1-2017.
 */
public class OrderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void generateKey_DefaultOrderFromTestBuilder_CreatesCorrectlyGeneratedKey() {
        //ARRANGE
        Order order = TestBuilders.order();
        String expectedKey = "ord-20171225-000000011";

        //ACT
        order.generateKey(11);

        //ASSERT
        assertThat(order.getOrderKey(), is(expectedKey));
    }

    @Test
    public void generateKey_OrderWithNoDate_ShouldThrowException() {
        Order order = TestBuilders.order().builder().orderDate(null).build();

        thrown.expect(NullPointerException.class);
        order.generateKey(1);
    }

}