package com.pwr.teamfinder.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupForm {

    @NotBlank(message = "{name.error.blank}")
    @Size(min = 2,max = 50, message = "{name.error.size}")
    @Pattern(regexp="[A-Za-z ,.'-]+", message = "{name.error.pattern}")
    private String name;

    @NotBlank(message = "{surname.error.blank}")
    @Size(min = 2,max = 50, message = "{surname.error.size}")
    @Pattern(regexp = "[A-Za-z ,.'-]+",  message = "{surname.error.pattern}")
    private String surname;

    @NotBlank(message = "{email.error.blank}")
    @Size(max = 50,  message = "{email.error.size}")
    @Email(message = "{email.error.pattern}")
    private String email;

    @NotBlank(message = "{password.error.blank}")
    @Size(min = 8,max = 20,  message = "{password.error.size}")
    private String password;

    @NotBlank(message = "{city.error.blank}")
    @Size(min = 2,max = 50, message = "{city.error.size}")
    @Pattern(regexp = "[A-Za-z ,.'-]+", message = "{city.error.pattern}")
    private String city;

    @Size(max = 50, message = "{street.error.size}")
    @Pattern(regexp = "[A-Za-z ,.'-]*", message = "{street.error.pattern}")
    private String street;

    @Size(max = 10, message = "{houseNumber.error.size}")
    @Pattern(regexp = "[0-9]*", message = "{houseNumber.error.pattern}")
    private String houseNumber;

    @NotBlank(message = "{role.error.blank}")
    private String role;

    @Size(max = 500, message = "{about.error.size}")
    @Pattern(regexp = "[A-Za-z ,.'-]*", message = "{about.error.pattern}")
    private String about;

    @NotBlank(message = "{termsAcceptance.error.blank}")
    private String termsAcceptance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTermsAcceptance() {
        return termsAcceptance;
    }

    public void setTermsAcceptance(String termsAcceptance) {
        this.termsAcceptance = termsAcceptance;
    }
}
