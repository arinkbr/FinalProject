package org.arin;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Address {
    private Integer streetNo;
    private String street;
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
        SK
    }

    /**
     * Checks if a postal code is valid.
     * Format must be letter-digit-letter-digit-letter-digit, length 6.
     * @param postalCode the input postal code
     * @return true if valid, false otherwise
     */
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null || postalCode.length() != 6) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            char c = postalCode.charAt(i);

            if (i % 2 == 0) {
                if (!Character.isLetter(c))
                    return false;
            } else {
                if (!Character.isDigit(c))
                    return false;
            }
        }

        return true;
    }

    public Address(int streetNo, String street, String city, String postalCode, Province province) {

        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.postalCode = postalCode.toUpperCase();
            this.province = province;
        } else {
            this.streetNo = null;
            this.street = null;
            this.city = null;
            this.postalCode = null;
            this.province = null;
        }
    }

    /**
     * Sets the postal code only if it is valid and stores it in uppercase
     * @param postalCode the new postal code
     */
    public void setPostalCode(String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.postalCode = postalCode.toUpperCase();
        }
    }
}
