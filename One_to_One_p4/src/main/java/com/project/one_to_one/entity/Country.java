package com.project.one_to_one.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Country_p4")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;
    private String countryName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="capital_id")
    private Capital capital;

    public Country() {
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }
}
