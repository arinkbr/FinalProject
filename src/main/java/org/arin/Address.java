package org.arin;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Address {
    private Integer streetNo;
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
            this.Street = street;
            this.city = city;
            this.postalCode = postalCode.toUpperCase();
            this.province = province;
        } else {
            this.streetNo = null;
            this.Street = null;
            this.city = null;
            this.postalCode = null;
            this.province = null;
        }
    }

    public void setPostalCode(String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.postalCode = postalCode.toUpperCase();
        }
    }
}
