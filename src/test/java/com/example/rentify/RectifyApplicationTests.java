package com.example.rentify;

import com.example.rentify.controller.AuthController;
import com.example.rentify.controller.PropertyController;
import com.example.rentify.model.Property;
import com.example.rentify.model.User;
import com.example.rentify.repository.PropertyRepository;
import com.example.rentify.repository.UserRepository;
import com.example.rentify.service.PropertyService;
import com.example.rentify.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class RentifyApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private PropertyService propertyService;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    @InjectMocks
    private PropertyController propertyController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUserRegistration() throws Exception {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        when(userService.registerUser(any())).thenReturn(user);

        String jsonRequest = objectMapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        User registeredUser = objectMapper.readValue(jsonResponse, User.class);

        assertEquals(user.getEmail(), registeredUser.getEmail());
    }

    @Test
    public void testPropertyPost() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        Property property = new Property();
        property.setPlace("City Center");
        property.setArea(1200);
        property.setNumBedrooms(3);
        property.setNumBathrooms(2);

        when(userService.getCurrentUser()).thenReturn(user);
        when(propertyService.addProperty(any())).thenReturn(property);

        String jsonRequest = objectMapper.writeValueAsString(property);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/property")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        Property savedProperty = objectMapper.readValue(jsonResponse, Property.class);

        assertEquals(property.getPlace(), savedProperty.getPlace());
    }

    @Test
    public void testFetchAllProperties() throws Exception {
        List<Property> properties = new ArrayList<>();
        Property property1 = new Property();
        property1.setId(1L);
        property1.setPlace("City Center");
        property1.setArea(1200);
        property1.setNumBedrooms(3);
        property1.setNumBathrooms(2);

        Property property2 = new Property();
        property2.setId(2L);
        property2.setPlace("Suburb");
        property2.setArea(1500);
        property2.setNumBedrooms(4);
        property2.setNumBathrooms(2);

        properties.add(property1);
        properties.add(property2);

        when(propertyService.getAllProperties()).thenReturn(properties);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/property")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        Property[] fetchedProperties = objectMapper.readValue(jsonResponse, Property[].class);

        assertEquals(2, fetchedProperties.length);
        assertEquals("City Center", fetchedProperties[0].getPlace());
        assertEquals("Suburb", fetchedProperties[1].getPlace());
    }
}