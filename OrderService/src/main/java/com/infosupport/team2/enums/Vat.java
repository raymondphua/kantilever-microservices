package com.infosupport.team2.enums;

import java.math.BigDecimal;

public enum Vat {
    HIGH(new BigDecimal("0.21")),
    LOW(new BigDecimal("0.06")),
    NONE(new BigDecimal("0.00"));
  
    private final BigDecimal value;  
  
    Vat(BigDecimal val) {  
        value = val;  
    }  
  
    public BigDecimal getValue() {  
        return value;  
    }  
}  