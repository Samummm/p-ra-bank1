package com.bank.profile.service;
// TODO разверни импорты
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsIdEntity;
import com.bank.profile.mapper.AccountDetailsIdMapper;
import com.bank.profile.repository.AccountDetailsIdRepository;
import com.bank.profile.service.impl.AccountDetailsIdServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountDetailsIdServiceTest {

    // TODO добавь @DisplayName с понятным описанием теста на русском по типу:
    // "поиск по id, позитивный сценарий","поиск по несуществующему id, негативный сценарий"
    // TODO также необходимо переименовать тесты следующим образом:
    // тестируемый метод + описание + Positive/NegativeTest.
    // Например, findByIdPositiveTest; findByNonExistIdNegativeNest
    private AccountDetailsIdRepository repository;
    private AccountDetailsIdMapper mapper;
    private AccountDetailsIdService service;

    private AccountDetailsIdDto dto;
    private AccountDetailsIdEntity entity;

    @BeforeEach
    public void setUp() {
        repository = mock(AccountDetailsIdRepository.class);
        mapper = mock(AccountDetailsIdMapper.class);
        service = new AccountDetailsIdServiceImp(repository, mapper);

        dto = new AccountDetailsIdDto();
        dto.setId(1L);
        dto.setAccountId(123L);

        entity = new AccountDetailsIdEntity();
        entity.setId(1L);
        entity.setAccountId(123L);
    }

    @Test
    public void testFindById_ValidId() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        AccountDetailsIdDto result = service.findById(1L);

        // TODO можно объединить в AssertAll, чтобы видеть какие ассерты падают и какие отрабатывают
        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getAccountId(), result.getAccountId());

        // TODO лучше вынеси перед ассертами
        verify(repository, times(1)).findById(1L);
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    public void testFindById_InvalidId() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(1L));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        when(repository.save(any())).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        AccountDetailsIdDto result = service.save(dto);

        // TODO можно объединить в AssertAll, чтобы видеть какие ассерты падают и какие отрабатывают
        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getAccountId(), result.getAccountId());

        // TODO лучше вынеси перед ассертами
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    public void testUpdate() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(any(AccountDetailsIdEntity.class))).thenReturn(entity);
        when(mapper.mergeToEntity(any(AccountDetailsIdDto.class), any(AccountDetailsIdEntity.class)))
                .thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        AccountDetailsIdDto result = service.update(1L, dto);

        // TODO можно объединить в AssertAll, чтобы видеть какие ассерты падают и какие отрабатывают
        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getAccountId(), result.getAccountId());

        // TODO лучше вынеси перед ассертами
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(entity);
        verify(mapper, times(1)).mergeToEntity(dto, entity);
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    public void testFindAllById() {
        List<AccountDetailsIdEntity> entities = Collections.singletonList(entity);
        when(repository.findAllById(anyList())).thenReturn(entities);
        when(mapper.toDtoList(entities)).thenReturn(Collections.singletonList(dto));

        List<AccountDetailsIdDto> result = service.findAllById(Collections.singletonList(1L));

        // TODO можно объединить в AssertAll, чтобы видеть какие ассерты падают и какие отрабатывают
        assertNotNull(result);
        assertEquals(1, result.size());

        // TODO лучше вынеси перед ассертами
        AccountDetailsIdDto actualDto = result.get(0);
        assertEquals(dto.getId(), actualDto.getId());
        assertEquals(dto.getAccountId(), actualDto.getAccountId());

        // TODO лучше вынеси перед ассертами
        verify(repository, times(1)).findAllById(Collections.singletonList(1L));
        verify(mapper, times(1)).toDtoList(entities);
    }
}

