package com.project.one_to_one.controller;


import com.project.one_to_one.entity.Country;
import com.project.one_to_one.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody  Country country)
    {
        Country saveCountry=countryRepository.save(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCountry);
    }

    @GetMapping("/{id}")
    public Optional<Country> showCountry(@PathVariable Integer id)
    {
        return countryRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Country updateCountry(@RequestBody Country country,@PathVariable Integer id)
    {
            country.setCountryId(id);
            return countryRepository.save(country);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer id)
    {
        countryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
