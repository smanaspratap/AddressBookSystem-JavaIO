package com.addressbook;

import java.util.Objects;

/**
 * Represents a Contact in the Address Book.
 * UC1: Basic contact with all required fields.
 * UC6: Overridden equals() and hashCode() to detect duplicates by first + last name.
 */
public class Contact {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    // Default constructor
    public Contact() {}

    // Parameterized constructor
    public Contact(String firstName, String lastName, String address,
                   String city, String state, String zip,
                   String phoneNumber, String email) {
        this.firstName   = firstName;
        this.lastName    = lastName;
        this.address     = address;
        this.city        = city;
        this.state       = state;
        this.zip         = zip;
        this.phoneNumber = phoneNumber;
        this.email       = email;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getFirstName()   { return firstName;   }
    public String getLastName()    { return lastName;    }
    public String getAddress()     { return address;     }
    public String getCity()        { return city;        }
    public String getState()       { return state;       }
    public String getZip()         { return zip;         }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail()       { return email;       }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setFirstName(String firstName)     { this.firstName   = firstName;   }
    public void setLastName(String lastName)       { this.lastName    = lastName;    }
    public void setAddress(String address)         { this.address     = address;     }
    public void setCity(String city)               { this.city        = city;        }
    public void setState(String state)             { this.state       = state;       }
    public void setZip(String zip)                 { this.zip         = zip;         }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email)             { this.email       = email;       }

    // ── UC6: Duplicate detection by first + last name ─────────────────────

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contact)) return false;
        Contact other = (Contact) obj;
        return firstName.equalsIgnoreCase(other.firstName)
            && lastName.equalsIgnoreCase(other.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase());
    }

    // ── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Contact{" +
               "Name='"        + firstName + " " + lastName + "'" +
               ", Address='"   + address   + "'" +
               ", City='"      + city      + "'" +
               ", State='"     + state     + "'" +
               ", Zip='"       + zip       + "'" +
               ", Phone='"     + phoneNumber + "'" +
               ", Email='"     + email     + "'" +
               "}";
    }
}
