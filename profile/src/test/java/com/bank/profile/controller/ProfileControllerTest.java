package com.bank.profile.controller;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.ProfileService;
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

@DisplayName("Тестируем методы контроллера ProfileController")
public class ProfileControllerTest {

    private ProfileDto mockDto;
    private ProfileService serviceMock;
    private ProfileController controller;

    @BeforeEach
    public void setUp() {
        PassportDto mockPassportDto = Mockito.mock(PassportDto.class);
        ActualRegistrationDto mockActualRegistrationDto = Mockito.mock(ActualRegistrationDto.class);
        mockDto = new ProfileDto();
        mockDto.setId(1L);
        mockDto.setPhoneNumber(1234567890L);
        mockDto.setEmail("test@example.com");
        mockDto.setNameOnCard("John Wick");
        mockDto.setInn(123456789012L);
        mockDto.setSnils(98765432109L);
        mockDto.setPassport(mockPassportDto);
        mockDto.setActualRegistration(mockActualRegistrationDto);

        serviceMock = Mockito.mock(ProfileService.class);
        controller = new ProfileController(serviceMock);
    }

    @Test
    @DisplayName("поиск по id, позитивный сценарий")
    public void readPositiveTest() {
        Mockito.when(serviceMock.findById(anyLong())).thenReturn(mockDto);

        ResponseEntity<ProfileDto> response = controller.read(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    @DisplayName("создание аккаунта, позитивный сценарий")
    public void createPositiveTest() {
        Mockito.when(serviceMock.save(any(ProfileDto.class))).thenReturn(mockDto);

        ResponseEntity<ProfileDto> response = controller.create(mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    @DisplayName("обновление аккаунта, позитивный сценарий")
    public void updatePositiveTest() {
        Mockito.when(serviceMock.update(anyLong(), any(ProfileDto.class))).thenReturn(mockDto);

        ResponseEntity<ProfileDto> response = controller.update(1L, mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    @DisplayName("поиск по нескольким id, позитивный сценарий")
    public void readAllByIdPositiveTest() {
        List<ProfileDto> mockDtoList = Collections.singletonList(mockDto);
        Mockito.when(serviceMock.findAllById(anyList())).thenReturn(mockDtoList);

        ResponseEntity<List<ProfileDto>> response = controller.readAllById(Collections.singletonList(1L));

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
