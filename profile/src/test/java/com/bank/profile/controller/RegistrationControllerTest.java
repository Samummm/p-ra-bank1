package com.bank.profile.controller;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

public class RegistrationControllerTest {

    private RegistrationDto mockDto;
    private RegistrationService serviceMock;
    private RegistrationController controller;

    @BeforeEach
    public void setUp() {
        mockDto = new RegistrationDto();
        mockDto = new RegistrationDto();
        mockDto.setId(1L);
        mockDto.setCountry("Country");
        mockDto.setRegion("Region");
        mockDto.setCity("City");
        mockDto.setDistrict("District");
        mockDto.setLocality("Locality");
        mockDto.setStreet("Street");
        mockDto.setHouseNumber("123");
        mockDto.setHouseBlock("Block A");
        mockDto.setFlatNumber("456");
        mockDto.setIndex(123456L);

        serviceMock = Mockito.mock(RegistrationService.class);
        controller = new RegistrationController(serviceMock);
    }

    @Test
    public void testRead() {
        Mockito.when(serviceMock.findById(anyLong())).thenReturn(mockDto);

        ResponseEntity<RegistrationDto> response = controller.read(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testCreate() {
        Mockito.when(serviceMock.save(any(RegistrationDto.class))).thenReturn(mockDto);

        ResponseEntity<RegistrationDto> response = controller.create(mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testUpdate() {
        Mockito.when(serviceMock.update(anyLong(), any(RegistrationDto.class))).thenReturn(mockDto);

        ResponseEntity<RegistrationDto> response = controller.update(1L, mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testReadAllById() {
        List<RegistrationDto> mockDtoList = Collections.singletonList(mockDto);
        Mockito.when(serviceMock.findAllById(anyList())).thenReturn(mockDtoList);

        ResponseEntity<List<RegistrationDto>> response = controller.readAllById(Collections.singletonList(1L));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDtoList, response.getBody());
    }

    @Test
    public void testRead_InvalidData() {
        Mockito.when(serviceMock.findById(anyLong())).thenThrow(new IllegalArgumentException("Invalid data"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.read(1L);
        });

        assertEquals("Invalid data", exception.getMessage());
    }
}
