package com.pwr.teamfinder.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupForm {

    @NotBlank(message = "{name.error}")
    @Size(min = 2,max = 50, message = "blad 1")
    @Pattern(regexp="[A-Za-z ,.'-]+", message = "blad 1")
    private String name;

    @NotBlank(message = "blad 1")
    @Size(min = 2,max = 50, message = "blad 1")
    @Pattern(regexp = "[A-Za-z ,.'-]+",  message = "blad 1")
    private String surname;

    @NotBlank(message = "blad 1")
    @Size(min = 5,max = 50,  message = "blad 1")
    @Email(message = "blad 1")
    private String email;

    @NotBlank(message = "blad 1")
    @Size(min = 8,max = 20,  message = "blad 1")
    private String password;

    @NotBlank(message = "blad 1")
    @Size(min = 2,max = 50, message = "blad 1")
    @Pattern(regexp = "[A-Za-z ,.'-]+", message = "blad 1")
    private String city;

    @Size(max = 50, message = "blad 1")
    @Pattern(regexp = "[A-Za-z ,.'-]*", message = "blad 1")
    private String street;

    @Size(max = 10, message = "blad 1")
    @Pattern(regexp = "[0-9]*", message = "blad 1")
    private String houseNumber;

    @NotBlank(message = "blad 1")
    private String role;

    @Size(max = 500, message = "blad 1")
    @Pattern(regexp = "[A-Za-z ,.'-]*", message = "blad 1")
    private String about;

    @NotBlank(message = "blad 1")
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
