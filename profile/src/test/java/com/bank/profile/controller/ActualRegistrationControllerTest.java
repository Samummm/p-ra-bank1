package com.bank.profile.controller;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.service.ActualRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

@DisplayName("Тестируем методы контроллера ActualRegistrationController")
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
    @DisplayName("поиск по id, позитивный сценарий")
    public void readPositiveTest() {
        Mockito.when(serviceMock.findById(anyLong())).thenReturn(mockDto);

        ResponseEntity<ActualRegistrationDto> response = controller.read(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    @DisplayName("создание аккаунта, позитивный сценарий")
    public void createPositiveTest() {
        Mockito.when(serviceMock.save(any(ActualRegistrationDto.class))).thenReturn(mockDto);

        ResponseEntity<ActualRegistrationDto> response = controller.create(mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    @DisplayName("обновление аккаунта, позитивный сценарий")
    public void updatePositiveTest() {
        Mockito.when(serviceMock.update(anyLong(), any(ActualRegistrationDto.class))).thenReturn(mockDto);

        ResponseEntity<ActualRegistrationDto> response = controller.update(1L, mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    @DisplayName("поиск по нескольким id, позитивный сценарий")
    public void readAllByIdPositiveTest() {
        List<ActualRegistrationDto> mockDtoList = Collections.singletonList(mockDto);
        Mockito.when(serviceMock.findAllById(anyList())).thenReturn(mockDtoList);

        ResponseEntity<List<ActualRegistrationDto>> response = controller.readAllById(Collections.singletonList(1L));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDtoList, response.getBody());
    }

    @Test
    @DisplayName("ошибка несуществующего id, негативный сценарий")
    public void readNegativeTest() {
        Mockito.when(serviceMock.findById(anyLong())).thenThrow(new IllegalArgumentException("Invalid data"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.read(1L);
        });

        assertEquals("Invalid data", exception.getMessage());
    }
}

