package com.infosupport.team2.util;

import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Raymond Phua on 18-1-2017.
 */
public class PriceValidatorTest {

    @Test
    public void calcTotalPrice_NoProductsInOrder_ReturnsZero() {
        //Arrange
        Order order = TestBuilders.order().builder().orderedProducts(new ArrayList<>()).build();

        //Act
        double totalPrice = PriceValidator.calcTotalPrice(order);

        //Assert
        assertThat(totalPrice, is(0.0));
    }

    @Test
    public void calcTotalPrice_ProductsInOrderWithQuantity_Returns55() {
        //Arrange
        Order order = TestBuilders.order();

        //Act
        double totalPrice = PriceValidator.calcTotalPrice(order);

        //Assert
        assertThat(totalPrice, is(55.0));
    }

    @Test
    public void calcTotalPrice_ProductsInOrderWithoutQuantity_Returns30() {
        //Arrange
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().price(10).quantity(1).build());
        products.add(Product.builder().price(5).quantity(1).build());
        products.add(Product.builder().price(15).quantity(1).build());

        Order order = TestBuilders.order().builder().orderedProducts(products).build();

        //Act
        double totalPrice = PriceValidator.calcTotalPrice(order);

        //Assert
        assertThat(totalPrice, is(30.0));
    }

    @Test
    public void calcTotalPrice_ProductsInOrderWithDecimalPrice_Returns55_50() {
        //Arrange
        List<Product> products = TestBuilders.order().getOrderedProducts();
        products.add(Product.builder().quantity(1).price(0.50).build());

        Order order = TestBuilders.order().builder().orderedProducts(products).build();

        //Act
        double totalPrice = PriceValidator.calcTotalPrice(order);

        //Assert
        assertThat(totalPrice, is(55.50));
    }

}