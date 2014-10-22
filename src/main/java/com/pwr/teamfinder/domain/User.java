package com.pwr.teamfinder.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "user", indexes = {
        @Index(columnList = "email", unique = true)
})
public class User extends BaseEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Embedded
    private Address address;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "about")
    private String about;

    @ManyToMany
    @JoinTable(
            name = "user_team",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "team_id", referencedColumnName = "id")})
    private Set<Team> teams;

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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        User rhs = (User) obj;

        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(name, rhs.getName())
                .append(surname, rhs.getSurname())
                .append(email, rhs.getEmail())
                .append(password, rhs.getPassword())
                .append(role, rhs.getRole())
                .append(about, rhs.getAbout())
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
                .append(role)
                .append(about)
                .toHashCode();
    }
}
