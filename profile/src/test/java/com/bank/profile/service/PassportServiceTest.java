package com.bank.profile.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.PassportEntity;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.service.impl.PassportServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DisplayName("Тестируем методы сервиса PassportService")
public class PassportServiceTest {

    private PassportRepository repository;
    private PassportMapper mapper;
    private PassportService service;
    private PassportDto dto;
    private PassportEntity entity;

    @BeforeEach
    public void setUp() {
        repository = mock(PassportRepository.class);
        mapper = mock(PassportMapper.class);
        service = new PassportServiceImp(repository, mapper);

        dto = new PassportDto();
        dto.setId(1L);
        dto.setSeries(1234);
        dto.setNumber(567890L);

        dto.setLastName("Last");
        dto.setFirstName("First");
        dto.setMiddleName("Middle");
        dto.setGender("Male");
        dto.setBirthDate(LocalDate.of(1990, 1, 15));
        dto.setBirthPlace("Birth Place");
        dto.setIssuedBy("Issuer");
        dto.setDateOfIssue(LocalDate.of(2020, 5, 10));
        dto.setDivisionCode(123456);
        dto.setExpirationDate(LocalDate.of(2030, 12, 31));

        entity = new PassportEntity();
        entity.setId(1L);
        entity.setSeries(1234);
        entity.setNumber(567890L);
        entity.setLastName("Last");
        entity.setFirstName("First");
        entity.setMiddleName("Middle");
        entity.setGender("Male");
        entity.setBirthDate(LocalDate.of(1990, 1, 15));
        entity.setBirthPlace("Birth Place");
        entity.setIssuedBy("Issuer");
        entity.setDateOfIssue(LocalDate.of(2020, 5, 10));
        entity.setDivisionCode(123456);
        entity.setExpirationDate(LocalDate.of(2030, 12, 31));
    }

    @Test
    @DisplayName("поиск по id, позитивный сценарий")
    public void findByIdPositiveTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        PassportDto result = service.findById(1L);

        verify(repository, times(1)).findById(1L);
        verify(mapper, times(1)).toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getSeries(), result.getSeries()),
                () -> assertEquals(dto.getNumber(), result.getNumber()),
                () -> assertEquals(dto.getLastName(), result.getLastName()),
                () -> assertEquals(dto.getFirstName(), result.getFirstName()),
                () -> assertEquals(dto.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(dto.getGender(), result.getGender()),
                () -> assertEquals(dto.getBirthDate(), result.getBirthDate()),
                () -> assertEquals(dto.getBirthPlace(), result.getBirthPlace()),
                () -> assertEquals(dto.getIssuedBy(), result.getIssuedBy()),
                () -> assertEquals(dto.getDateOfIssue(), result.getDateOfIssue()),
                () -> assertEquals(dto.getDivisionCode(), result.getDivisionCode()),
                () -> assertEquals(dto.getExpirationDate(), result.getExpirationDate())
        );
    }

    @Test
    @DisplayName("поиск по несуществующему id, негативный сценарий")
    public void findByIdNegativeTest() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(1L));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("сохранение, позитивный сценарий")
    public void savePositiveTest() {
        when(repository.save(any())).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        PassportDto result = service.save(dto);

        verify(mapper, times(1)).toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getSeries(), result.getSeries()),
                () -> assertEquals(dto.getNumber(), result.getNumber()),
                () -> assertEquals(dto.getLastName(), result.getLastName()),
                () -> assertEquals(dto.getFirstName(), result.getFirstName()),
                () -> assertEquals(dto.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(dto.getGender(), result.getGender()),
                () -> assertEquals(dto.getBirthDate(), result.getBirthDate()),
                () -> assertEquals(dto.getBirthPlace(), result.getBirthPlace()),
                () -> assertEquals(dto.getIssuedBy(), result.getIssuedBy()),
                () -> assertEquals(dto.getDateOfIssue(), result.getDateOfIssue()),
                () -> assertEquals(dto.getDivisionCode(), result.getDivisionCode()),
                () -> assertEquals(dto.getExpirationDate(), result.getExpirationDate())
        );
    }

    @Test
    @DisplayName("обновление, позитивный сценарий")
    public void updatePositiveTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(any(PassportEntity.class))).thenReturn(entity);
        when(mapper.mergeToEntity(any(PassportDto.class), any(PassportEntity.class)))
                .thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        PassportDto result = service.update(1L, dto);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(entity);
        verify(mapper, times(1)).mergeToEntity(dto, entity);
        verify(mapper, times(1)).toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getSeries(), result.getSeries()),
                () -> assertEquals(dto.getNumber(), result.getNumber()),
                () -> assertEquals(dto.getLastName(), result.getLastName()),
                () -> assertEquals(dto.getFirstName(), result.getFirstName()),
                () -> assertEquals(dto.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(dto.getGender(), result.getGender()),
                () -> assertEquals(dto.getBirthDate(), result.getBirthDate()),
                () -> assertEquals(dto.getBirthPlace(), result.getBirthPlace()),
                () -> assertEquals(dto.getIssuedBy(), result.getIssuedBy()),
                () -> assertEquals(dto.getDateOfIssue(), result.getDateOfIssue()),
                () -> assertEquals(dto.getDivisionCode(), result.getDivisionCode()),
                () -> assertEquals(dto.getExpirationDate(), result.getExpirationDate())
        );
    }

    @Test
    @DisplayName("поиск по нескольким id, позитивный сценарий")
    public void findAllByIdPositiveTest() {
        List<PassportEntity> entities = Collections.singletonList(entity);
        when(repository.findAllById(anyList())).thenReturn(entities);
        when(mapper.toDtoList(entities)).thenReturn(Collections.singletonList(dto));

        List<PassportDto> result = service.findAllById(Collections.singletonList(1L));

        verify(repository, times(1)).findAllById(Collections.singletonList(1L));
        verify(mapper, times(1)).toDtoList(entities);

        assertNotNull(result);
        assertEquals(1, result.size());

        PassportDto actualDto = result.get(0);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), actualDto.getId()),
                () -> assertEquals(dto.getSeries(), actualDto.getSeries()),
                () -> assertEquals(dto.getNumber(), actualDto.getNumber()),
                () -> assertEquals(dto.getLastName(), actualDto.getLastName()),
                () -> assertEquals(dto.getFirstName(), actualDto.getFirstName()),
                () -> assertEquals(dto.getMiddleName(), actualDto.getMiddleName()),
                () -> assertEquals(dto.getGender(), actualDto.getGender()),
                () -> assertEquals(dto.getBirthDate(), actualDto.getBirthDate()),
                () -> assertEquals(dto.getBirthPlace(), actualDto.getBirthPlace()),
                () -> assertEquals(dto.getIssuedBy(), actualDto.getIssuedBy()),
                () -> assertEquals(dto.getDateOfIssue(), actualDto.getDateOfIssue()),
                () -> assertEquals(dto.getDivisionCode(), actualDto.getDivisionCode()),
                () -> assertEquals(dto.getExpirationDate(), actualDto.getExpirationDate())
        );
    }
}

