package com.luxoft.springioc.lab1.model;

import java.util.Objects;

public class UsualPerson implements Person {
    private String name;
    private Country country;
    private int age;

    public UsualPerson(String name, Country country, int age) {
        this.name = name;
        this.country = country;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sayHello(Person person) {
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String toString() {
        return "Name: " + name + "\n"
                + "Age: " + age + "\n"
                + "Country: " + country + "\n";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsualPerson person = (UsualPerson) o;

        if (age != person.age) return false;
        if (!Objects.equals(country, person.country)) return false;
        return Objects.equals(name, person.name);
    }

    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
