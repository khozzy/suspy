package com.pwr.teamfinder.dto;

import com.pwr.teamfinder.domain.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupForm {

    @NotBlank
    @Size(min=2,max=50)
    @Pattern(regexp="[A-Za-z ,.'-]+")
    private String name;

    @NotBlank
    @Size(min=2,max=50)
    @Pattern(regexp="[A-Za-z ,.'-]+")
    private String surname;

    @NotBlank
    @Size(min=5,max=50)
    @Email
    private String email;

    @NotBlank
    @Size(min=8,max=20)
    private String password;

    @NotBlank
    @Size(min=2,max=50)
    @Pattern(regexp="[A-Za-z ,.'-]+")
    private String city;

    @Max(value = 50)
    @Pattern(regexp = "[A-Za-z ,.'-]+")
    private String street;

    @Max(value = 10)
    @Pattern(regexp = "[0-9]+")
    private String houseNumber;

    @NotBlank
    private Role role;

    @Max(value = 500)
    private String about;

    @NotBlank
    private boolean termsAcceptance;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isTermsAcceptance() {
        return termsAcceptance;
    }

    public void setTermsAcceptance(boolean termsAcceptance) {
        this.termsAcceptance = termsAcceptance;
    }
}
