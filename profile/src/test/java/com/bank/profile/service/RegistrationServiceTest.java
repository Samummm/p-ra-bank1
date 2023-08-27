package com.bank.profile.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.RegistrationEntity;
import com.bank.profile.mapper.RegistrationMapper;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.service.impl.RegistrationServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DisplayName("Тестируем методы сервиса RegistrationService")
public class RegistrationServiceTest {

    private RegistrationRepository repository;
    private RegistrationMapper mapper;
    private RegistrationService service;
    private RegistrationDto dto;
    private RegistrationEntity entity;

    @BeforeEach
    public void setUp() {
        repository = mock(RegistrationRepository.class);
        mapper = mock(RegistrationMapper.class);
        service = new RegistrationServiceImp(repository, mapper);

        dto = new RegistrationDto();
        dto.setId(1L);
        dto.setCountry("Country");
        dto.setRegion("Region");
        dto.setCity("City");
        dto.setDistrict("District");
        dto.setLocality("Locality");
        dto.setStreet("Street");
        dto.setHouseNumber("123");
        dto.setHouseBlock("Block A");
        dto.setFlatNumber("456");
        dto.setIndex(123456L);

        entity = new RegistrationEntity();
        entity.setId(1L);
        entity.setCountry("Country");
        entity.setRegion("Region");
        entity.setCity("City");
        entity.setDistrict("District");
        entity.setLocality("Locality");
        entity.setStreet("Street");
        entity.setHouseNumber("123");
        entity.setHouseBlock("Block A");
        entity.setFlatNumber("456");
        entity.setIndex(123456L);
    }

    @Test
    @DisplayName("поиск по id, позитивный сценарий")
    public void findByIdPositiveTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        RegistrationDto result = service.findById(1L);

        verify(repository, times(1)).findById(1L);
        verify(mapper, times(1)).toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getCountry(), result.getCountry()),
                () -> assertEquals(dto.getRegion(), result.getRegion()),
                () -> assertEquals(dto.getCity(), result.getCity()),
                () -> assertEquals(dto.getDistrict(), result.getDistrict()),
                () -> assertEquals(dto.getLocality(), result.getLocality()),
                () -> assertEquals(dto.getStreet(), result.getStreet()),
                () -> assertEquals(dto.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(dto.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(dto.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(dto.getIndex(), result.getIndex())
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

        RegistrationDto result = service.save(dto);

        verify(mapper, times(1)).toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getCountry(), result.getCountry()),
                () -> assertEquals(dto.getRegion(), result.getRegion()),
                () -> assertEquals(dto.getCity(), result.getCity()),
                () -> assertEquals(dto.getDistrict(), result.getDistrict()),
                () -> assertEquals(dto.getLocality(), result.getLocality()),
                () -> assertEquals(dto.getStreet(), result.getStreet()),
                () -> assertEquals(dto.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(dto.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(dto.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(dto.getIndex(), result.getIndex())
        );
    }

    @Test
    @DisplayName("обновление, позитивный сценарий")
    public void updatePositiveTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(any(RegistrationEntity.class))).thenReturn(entity);
        when(mapper.mergeToEntity(any(RegistrationDto.class), any(RegistrationEntity.class)))
                .thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        RegistrationDto result = service.update(1L, dto);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(entity);
        verify(mapper, times(1)).mergeToEntity(dto, entity);
        verify(mapper, times(1)).toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getCountry(), result.getCountry()),
                () -> assertEquals(dto.getRegion(), result.getRegion()),
                () -> assertEquals(dto.getCity(), result.getCity()),
                () -> assertEquals(dto.getDistrict(), result.getDistrict()),
                () -> assertEquals(dto.getLocality(), result.getLocality()),
                () -> assertEquals(dto.getStreet(), result.getStreet()),
                () -> assertEquals(dto.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(dto.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(dto.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(dto.getIndex(), result.getIndex())
        );
    }

    @Test
    @DisplayName("поиск по нескольким id, позитивный сценарий")
    public void findAllByIdPositiveTest() {
        List<RegistrationEntity> entities = Collections.singletonList(entity);
        when(repository.findAllById(anyList())).thenReturn(entities);
        when(mapper.toDtoList(entities)).thenReturn(Collections.singletonList(dto));

        List<RegistrationDto> result = service.findAllById(Collections.singletonList(1L));

        verify(repository, times(1)).findAllById(Collections.singletonList(1L));
        verify(mapper, times(1)).toDtoList(entities);

        assertNotNull(result);
        assertEquals(1, result.size());

        RegistrationDto actualDto = result.get(0);
        assertAll(
                () -> assertEquals(dto.getId(), actualDto.getId()),
                () -> assertEquals(dto.getCountry(), actualDto.getCountry()),
                () -> assertEquals(dto.getRegion(), actualDto.getRegion()),
                () -> assertEquals(dto.getCity(), actualDto.getCity()),
                () -> assertEquals(dto.getDistrict(), actualDto.getDistrict()),
                () -> assertEquals(dto.getLocality(), actualDto.getLocality()),
                () -> assertEquals(dto.getStreet(), actualDto.getStreet()),
                () -> assertEquals(dto.getHouseNumber(), actualDto.getHouseNumber()),
                () -> assertEquals(dto.getHouseBlock(), actualDto.getHouseBlock()),
                () -> assertEquals(dto.getFlatNumber(), actualDto.getFlatNumber()),
                () -> assertEquals(dto.getIndex(), actualDto.getIndex())
        );
    }
}
