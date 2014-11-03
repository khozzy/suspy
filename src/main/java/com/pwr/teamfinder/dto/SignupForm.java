package com.pwr.teamfinder.dto;

import com.pwr.teamfinder.domain.Address;
import com.pwr.teamfinder.domain.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupForm {

    @NotBlank(message = "{name.error.blank}")
    @Size(min = User.NAME_MIN, max = User.NAME_MAX, message = "{name.error.size}")
    @Pattern(regexp = User.REQUIRED_PATTERN, message = "{name.error.pattern}")
    private String name;

    @NotBlank(message = "{surname.error.blank}")
    @Size(min = User.SURNAME_MIN, max = User.SURNAME_MAX, message = "{surname.error.size}")
    @Pattern(regexp = User.REQUIRED_PATTERN, message = "{surname.error.pattern}")
    private String surname;

    @NotBlank(message = "{email.error.blank}")
    @Size(max = User.EMAIL_MAX, message = "{email.error.size}")
    @Email(message = "{email.error.pattern}")
    @Pattern(regexp = User.EMAIL_PATTERN, message = "{email.error.pattern}")
    private String email;

    @NotBlank(message = "{email.error.blank}")
    @Size(max = User.EMAIL_MAX, message = "{email.error.size}")
    @Email(message = "{email.error.pattern}")
    @Pattern(regexp = User.EMAIL_PATTERN, message = "{email.error.pattern}")
    private String retypeEmail;

    @NotBlank(message = "{password.error.blank}")
    @Size(min = User.PASSWORD_MIN, max = User.PASSWORD_MAX, message = "{password.error.size}")
    private String password;

    @NotBlank(message = "{password.error.blank}")
    @Size(min = User.PASSWORD_MIN, max = User.PASSWORD_MAX, message = "{password.error.size}")
    private String retypePassword;

    @NotBlank(message = "{city.error.blank}")
    @Size(min = Address.CITY_MIN, max = Address.CITY_MAX, message = "{city.error.size}")
    @Pattern(regexp = User.REQUIRED_PATTERN, message = "{city.error.pattern}")
    private String city;

    @Size(max = Address.STREET_MAX, message = "{street.error.size}")
    @Pattern(regexp = User.NON_REQUIRED_PATTERN, message = "{street.error.pattern}")
    private String street;

    @Size(max = Address.HOUSE_NR_MAX, message = "{houseNumber.error.size}")
    @Pattern(regexp = Address.HOUSE_NR_PATTERN, message = "{houseNumber.error.pattern}")
    private String houseNumber;

    @NotBlank(message = "{role.error.blank}")
    private String role;

    @Size(max = User.ABOUT_MAX, message = "{about.error.size}")
    @Pattern(regexp = User.NON_REQUIRED_PATTERN, message = "{about.error.pattern}")
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

    public String getRetypeEmail() {
        return retypeEmail;
    }

    public void setRetypeEmail(String retypeEmail) {
        this.retypeEmail = retypeEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
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
