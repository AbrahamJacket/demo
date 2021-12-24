package com.example.demo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AUTHORS")
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
public class Author {

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "AUTHOR_FIRST_NAME")
    private String firstName;
    @Column(name = "AUTHOR_LAST_NAME")
    private String lastName;
    @Column(name = "AUTHOR_BIRTH_DATE")
    private Date birthDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
