package ru.skril.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, message = "Name should be at least 3 characters long")
    private String name;
    @Column(name = "substance")
    @NotEmpty(message = "Active substance should not be empty")
    private String substance;
    @Column(name = "country")
    @NotEmpty(message = "Country should not be empty")
    private String country;
    @Column(name = "price")
    @Min(value = 0, message = "Price should be greater than 0")
    private int price;
    @Column(name = "rating")
    @DecimalMin(value = "0.0", message = "Min rating value is 0.0")
    @DecimalMax(value = "5.0", message = "Max rating value is 5.0")
    private double rating;

    public Medication(String name, String substance, String country, int price, double rating) {
        this.name = name;
        this.substance = substance;
        this.country = country;
        this.price = price;
        this.rating = rating;
    }

    public Medication() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Препарат " +
                "id=" + id +
                ", название='" + name + '\'' +
                ", активное вещество='" + substance + '\'' +
                ", страна производства='" + country + '\'' +
                ", цена=" + price +
                "р. , рейтинг=" + rating;
    }
}
