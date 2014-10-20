package com.pwr.teamfinder.dto;

import com.pwr.teamfinder.config.I18nConfiguration;
import com.pwr.teamfinder.domain.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Properties;

public class SignupForm {

    @Resource
    I18nConfiguration messageSource;

    @NotBlank(message = "blad 1")
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

    @Max(value = 50, message = "blad 1")
    @Pattern(regexp = "[A-Za-z ,.'-]+", message = "blad 1")
    private String street;

    @Max(value = 10, message = "blad 1")
    @Pattern(regexp = "[0-9]+", message = "blad 1")
    private String houseNumber;

    @NotBlank(message = "blad 1")
    private String role;

    @Max(value = 500, message = "blad 1")
    @Pattern(regexp = "[A-Za-z ,.'-]+", message = "blad 1")
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
