package controller;


import com.bank.profile.controller.PassportController;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.service.PassportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

public class TestPassportController {

    private PassportDto mockDto;
    private PassportService serviceMock;
    private PassportController controller;

    @BeforeEach
    public void setUp() {
        RegistrationDto mockRegistrationDto = Mockito.mock(RegistrationDto.class);
        mockDto = new PassportDto();
        mockDto.setId(1L);
        mockDto.setSeries(1234);
        mockDto.setNumber(567890L);
        mockDto.setLastName("Last");
        mockDto.setFirstName("First");
        mockDto.setMiddleName("Middle");
        mockDto.setGender("Male");
        mockDto.setBirthDate(LocalDate.of(1990, 1, 15));
        mockDto.setBirthPlace("Birth Place");
        mockDto.setIssuedBy("Issuer");
        mockDto.setDateOfIssue(LocalDate.of(2020, 5, 10));
        mockDto.setDivisionCode(123456);
        mockDto.setExpirationDate(LocalDate.of(2030, 12, 31));
        mockDto.setRegistration(mockRegistrationDto);

        serviceMock = Mockito.mock(PassportService.class);
        controller = new PassportController(serviceMock);
    }

    @Test
    public void testRead() {
        Mockito.when(serviceMock.findById(anyLong())).thenReturn(mockDto);

        ResponseEntity<PassportDto> response = controller.read(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testCreate() {
        Mockito.when(serviceMock.save(any(PassportDto.class))).thenReturn(mockDto);

        ResponseEntity<PassportDto> response = controller.create(mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testUpdate() {
        Mockito.when(serviceMock.update(anyLong(), any(PassportDto.class))).thenReturn(mockDto);

        ResponseEntity<PassportDto> response = controller.update(1L, mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testReadAllById() {
        List<PassportDto> mockDtoList = Collections.singletonList(mockDto);
        Mockito.when(serviceMock.findAllById(anyList())).thenReturn(mockDtoList);

        ResponseEntity<List<PassportDto>> response = controller.readAllById(Collections.singletonList(1L));

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