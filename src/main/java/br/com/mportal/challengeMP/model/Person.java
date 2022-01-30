package br.com.mportal.challengeMP.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String gender;
    private String accessIp;
    private int age;
    private Date birthDate;

    public Person(){}

    public Person(String name, String lastName, String email, String gender, String accessIp, int age, Date birthDate){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.accessIp = accessIp;
        this.age = age;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
