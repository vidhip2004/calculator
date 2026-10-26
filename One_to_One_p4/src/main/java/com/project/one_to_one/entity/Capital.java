package com.project.one_to_one.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="Capital_p4")
public class Capital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer capitalId;
    private String cityName;
    private long population;
    @OneToOne(mappedBy = "capital")
    @JsonIgnore
    private Country country;

    public Capital() {
    }

    public Integer getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(Integer capitalId) {
        this.capitalId = capitalId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
