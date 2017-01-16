package com.infosupport.team2.enums;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
public enum Status {
    BESTELD, AFGELEVERD;

    @Override
    public String toString() {
        switch(this) {
            case BESTELD: return "Besteld";
            case AFGELEVERD: return "Afgeleverd";
            default: return "Onbekend status";
        }
    }
}

