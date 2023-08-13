package controller;

import com.bank.profile.controller.AccountDetailsIdController;
import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.AccountDetailsIdService;
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

public class TestAccountDetailsIdController {

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
    public void testRead() {
        Mockito.when(serviceMock.findById(anyLong())).thenReturn(mockDto);

        ResponseEntity<AccountDetailsIdDto> response = controller.read(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testReadAllById() {
        List<AccountDetailsIdDto> mockDtoList = Collections.singletonList(mockDto);
        Mockito.when(serviceMock.findAllById(anyList())).thenReturn(mockDtoList);

        ResponseEntity<List<AccountDetailsIdDto>> response = controller.readAllById(Collections.singletonList(1L));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDtoList, response.getBody());
    }

    @Test
    public void testCreate() {
        AccountDetailsIdDto requestDto = new AccountDetailsIdDto();
        Mockito.when(serviceMock.save(any(AccountDetailsIdDto.class))).thenReturn(mockDto);

        ResponseEntity<AccountDetailsIdDto> response = controller.create(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    public void testUpdate() {
        AccountDetailsIdDto requestDto = new AccountDetailsIdDto();
        Mockito.when(serviceMock.update(anyLong(), any(AccountDetailsIdDto.class))).thenReturn(mockDto);

        ResponseEntity<AccountDetailsIdDto> response = controller.update(1L, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
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
