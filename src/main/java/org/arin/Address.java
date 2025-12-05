package org.arin;

import lombok.*;

@Setter
@Getter
public class Address {
    private int streetNo;
    private String Street;
    private String city;
    private Province province;
    private String postalCode;

    public enum Province {
        AB,
        BC,
        QC,
        ON,
        NB,
        MB,
        NL,
        NS,
        PE,
        SK,
    }
}

