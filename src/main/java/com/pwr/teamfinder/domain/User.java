package com.pwr.teamfinder.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "resetPasswordCode", unique = true)
})
public class User extends BaseEntity {

    public static final int NAME_MIN = 2;
    public static final int NAME_MAX = 50;
    public static final int SURNAME_MIN = 2;
    public static final int SURNAME_MAX = 50;
    public static final int EMAIL_MAX = 250;
    public static final String EMAIL_PATTERN = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    public static final String REQUIRED_PATTERN = "[A-Za-z ,.'-żźćńółęąśŻŹĆĄŚĘŁÓŃ]+";
    public static final String NON_REQUIRED_PATTERN = "[A-Za-z ,.'-żźćńółęąśŻŹĆĄŚĘŁÓŃ]*";
    public static final int PASSWORD_MIN = 8;
    public static final int PASSWORD_MAX = 30;
    public static final int RANDOM_CODE_LENGTH = 16;
    public static final int ABOUT_MAX = 500;

    @NotNull
    @Column(name = "name", nullable = false, length = NAME_MAX)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false, length = SURNAME_MAX)
    private String surname;

    @NotNull
    @Column(name = "email", nullable = false, length = EMAIL_MAX)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Column(name = "about", length = ABOUT_MAX)
    private String about;

    @Column(length = RANDOM_CODE_LENGTH)
    private String verificationCode;

    @Column(length = RANDOM_CODE_LENGTH)
    private String resetPasswordCode;

    @ManyToMany
    @JoinTable(
            name = "user_team",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")})
    private Set<Team> teams;

    @OneToMany
    private Set<User> friends;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String forgotPasswordCode) {
        this.resetPasswordCode = forgotPasswordCode;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        User rhs = (User) obj;

        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(name, rhs.getName())
                .append(surname, rhs.getSurname())
                .append(email, rhs.getEmail())
                .append(password, rhs.getPassword())
                .append(address, rhs.getAddress())
                .append(roles, rhs.getRoles())
                .append(about, rhs.getAbout())
                .append(verificationCode, rhs.getVerificationCode())
                .append(resetPasswordCode, rhs.getResetPasswordCode())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(193, 541)
                .appendSuper(super.hashCode())
                .append(name)
                .append(surname)
                .append(email)
                .append(password)
                .append(address)
                .append(roles)
                .append(about)
                .append(verificationCode)
                .append(resetPasswordCode)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", roles=" + roles +
                ", about='" + about + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", resetPasswordCode='" + resetPasswordCode + '\'' +
                ", teams=" + teams +
                ", friends=" + friends +
                '}';
    }
}
