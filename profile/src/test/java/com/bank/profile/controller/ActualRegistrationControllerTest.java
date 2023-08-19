package com.bank.profile.controller;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.service.ActualRegistrationService;
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
// TODO отрефакторить также, как в AccountDetailsIdControllerTest
public class ActualRegistrationControllerTest {

    private ActualRegistrationDto mockDto;
    private ActualRegistrationService serviceMock;
    private ActualRegistrationController controller;

    @BeforeEach
    public void setUp() {
        mockDto = new ActualRegistrationDto();
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

        serviceMock = Mockito.mock(ActualRegistrationService.class);
        controller = new ActualRegistrationController(serviceMock);
    }

    @Test
    public void testRead() {
        Mockito.when(serviceMock.findById(anyLong())).thenReturn(mockDto);

        ResponseEntity<ActualRegistrationDto> response = controller.read(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testCreate() {
        Mockito.when(serviceMock.save(any(ActualRegistrationDto.class))).thenReturn(mockDto);

        ResponseEntity<ActualRegistrationDto> response = controller.create(mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testUpdate() {
        Mockito.when(serviceMock.update(anyLong(), any(ActualRegistrationDto.class))).thenReturn(mockDto);

        ResponseEntity<ActualRegistrationDto> response = controller.update(1L, mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testReadAllById() {
        List<ActualRegistrationDto> mockDtoList = Collections.singletonList(mockDto);
        Mockito.when(serviceMock.findAllById(anyList())).thenReturn(mockDtoList);

        ResponseEntity<List<ActualRegistrationDto>> response = controller.readAllById(Collections.singletonList(1L));

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

