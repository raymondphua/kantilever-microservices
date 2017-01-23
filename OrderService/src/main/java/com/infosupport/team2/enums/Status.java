package com.infosupport.team2.enums;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
public enum Status {
    BESTELD, IN_BEHANDELING, INGEPAKT, VERZONDEN;

    @Override
    public String toString() {
        switch(this) {
            case BESTELD: return "Besteld";
            case IN_BEHANDELING: return "In Behandeling";
            case INGEPAKT: return "Ingepakt";
            case VERZONDEN: return "Verzonden";
            default: return "Onbekend status";
        }
    }
}

