package com.example.rentify.service;

import com.example.rentify.model.Property;
import com.example.rentify.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id " + id));
    }

    public Property updateProperty(Long id, Property property) {
        property.setId(id);
        return propertyRepository.save(property);
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}