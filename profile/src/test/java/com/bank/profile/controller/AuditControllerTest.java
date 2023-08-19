package com.bank.profile.controller;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
// TODO отрефакторить также, как в AccountDetailsIdControllerTest
public class AuditControllerTest {

    private AuditDto mockDto;
    private AuditService serviceMock;
    private AuditController controller;

    @BeforeEach
    public void setUp() {
        mockDto = new AuditDto();
        mockDto.setId(1L);
        mockDto.setEntityType("Entity");
        mockDto.setOperationType("Create");
        mockDto.setCreatedBy("User");
        mockDto.setModifiedBy("User");
        mockDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        mockDto.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        mockDto.setNewEntityJson("New JSON");
        mockDto.setEntityJson("Old JSON");

        serviceMock = Mockito.mock(AuditService.class);
        controller = new AuditController(serviceMock);
    }

    @Test
    public void testRead() {
        Mockito.when(serviceMock.findById(anyLong())).thenReturn(mockDto);

        AuditDto response = controller.read(1L);

        assertEquals(mockDto, response);
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


