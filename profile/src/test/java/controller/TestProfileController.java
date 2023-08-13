package controller;


import com.bank.profile.controller.ProfileController;
import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.ProfileService;
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

public class TestProfileController {

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
    public void testRead() {
        Mockito.when(serviceMock.findById(anyLong())).thenReturn(mockDto);

        ResponseEntity<ProfileDto> response = controller.read(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testCreate() {
        Mockito.when(serviceMock.save(any(ProfileDto.class))).thenReturn(mockDto);

        ResponseEntity<ProfileDto> response = controller.create(mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testUpdate() {
        Mockito.when(serviceMock.update(anyLong(), any(ProfileDto.class))).thenReturn(mockDto);

        ResponseEntity<ProfileDto> response = controller.update(1L, mockDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testReadAllById() {
        List<ProfileDto> mockDtoList = Collections.singletonList(mockDto);
        Mockito.when(serviceMock.findAllById(anyList())).thenReturn(mockDtoList);

        ResponseEntity<List<ProfileDto>> response = controller.readAllById(Collections.singletonList(1L));

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
