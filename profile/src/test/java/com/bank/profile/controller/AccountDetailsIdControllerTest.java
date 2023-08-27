package com.bank.profile.controller;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.AccountDetailsIdService;
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

@DisplayName("Тестируем методы контроллера AccountDetailsIdController")
public class AccountDetailsIdControllerTest {

    private AccountDetailsIdDto mockDto;
    private AccountDetailsIdService serviceMock;
    private AccountDetailsIdController controller;

    @BeforeEach
    public void setUp() {
        ProfileDto mockProfileDto = Mockito.mock(ProfileDto.class);

        mockDto = new AccountDetailsIdDto();
        mockDto.setId(1L);
        mockDto.setAccountId(1L);
        mockDto.setProfile(mockProfileDto);

        serviceMock = Mockito.mock(AccountDetailsIdService.class);
        controller = new AccountDetailsIdController(serviceMock);
    }

    @Test
    @DisplayName("поиск по id, позитивный сценарий")
    public void readPositiveTest() {
        Mockito.when(serviceMock.findById(anyLong())).thenReturn(mockDto);

        ResponseEntity<AccountDetailsIdDto> response = controller.read(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    @DisplayName("поиск по нескольким id, позитивный сценарий")
    public void readAllByIdPositiveTest() {
        List<AccountDetailsIdDto> mockDtoList = Collections.singletonList(mockDto);
        Mockito.when(serviceMock.findAllById(anyList())).thenReturn(mockDtoList);

        ResponseEntity<List<AccountDetailsIdDto>> response = controller.readAllById(Collections.singletonList(1L));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDtoList, response.getBody());
    }

    @Test
    @DisplayName("создание аккаунта, позитивный сценарий")
    public void createPositiveTest() {
        AccountDetailsIdDto requestDto = new AccountDetailsIdDto();
        Mockito.when(serviceMock.save(any(AccountDetailsIdDto.class))).thenReturn(mockDto);

        ResponseEntity<AccountDetailsIdDto> response = controller.create(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    @DisplayName("обновление аккаунта, позитивный сценарий")
    public void updatePositiveTest() {
        AccountDetailsIdDto requestDto = new AccountDetailsIdDto();
        Mockito.when(serviceMock.update(anyLong(), any(AccountDetailsIdDto.class))).thenReturn(mockDto);

        ResponseEntity<AccountDetailsIdDto> response = controller.update(1L, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
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
