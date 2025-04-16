package com.asthana.Batch_Processing_1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Iris {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //    @Column(name = "id")
    private UUID id; 
    private double sepal_length;
    private double sepal_width;
    private double petal_length;
    private double petal_width;
    private String variety;
    private double total;

    public Iris(UUID id, double sepal_length, double sepal_width, double petal_length, double petal_width, String variety, double total) {
        this.id = id;
        this.sepal_length = sepal_length;
        this.sepal_width = sepal_width;
        this.petal_length = petal_length;
        this.petal_width = petal_width;
        this.variety = variety;
        this.total = total;
    }

    public Iris() {
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getSepal_length() {
        return sepal_length;
    }

    public void setSepal_length(double sepal_length) {
        this.sepal_length = sepal_length;
    }

    public double getSepal_width() {
        return sepal_width;
    }

    public void setSepal_width(double sepal_width) {
        this.sepal_width = sepal_width;
    }

    public double getPetal_length() {
        return petal_length;
    }

    public void setPetal_length(double petal_length) {
        this.petal_length = petal_length;
    }

    public double getPetal_width() {
        return petal_width;
    }

    public void setPetal_width(double petal_width) {
        this.petal_width = petal_width;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
