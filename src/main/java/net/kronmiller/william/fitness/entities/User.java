package net.kronmiller.william.fitness.entities;

import net.kronmiller.william.fitness.exceptions.InvalidUserException;

/**
 * Created by wkronmiller on 7/15/17.
 */
public class User implements Entity {
    private String firstName, lastName, middleName, email;
    private Integer uuid;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public void validate() throws InvalidUserException {
        String[] requiredFields = {firstName, lastName, email};
        for(String field: requiredFields) {
            if(field == null) {
                System.out.println("First name: "  + firstName);
                System.out.println("Last name: " + lastName);
                System.out.println("Email: " + email);
                throw new InvalidUserException("Required field is null");
            }
            if(field.length() < 1) {
                throw new InvalidUserException("Required field is empty");
            }
        }
        if(email.contains("@") == false) {
            throw new InvalidUserException("User email is invalid");//TODO: more thorough regex checks with email verification
        }
    }
}
