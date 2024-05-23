package com.example.rentify.controller;

import com.example.rentify.model.Property;
import com.example.rentify.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property createdProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(id, property);
        return ResponseEntity.ok(updatedProperty);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}