package com.example.tidsrejseagentur.customer;

public class customer {
    private String firstName;
    private String lastName;
    private String email;


    public customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return firstName + lastName +" - " + email;
    }
}
