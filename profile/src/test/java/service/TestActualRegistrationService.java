package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistrationEntity;
import com.bank.profile.mapper.ActualRegistrationMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.service.ActualRegistrationService;
import com.bank.profile.service.impl.ActualRegistrationServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TestActualRegistrationService {

    private ActualRegistrationRepository repository;
    private ActualRegistrationMapper mapper;
    private ActualRegistrationService service;

    private ActualRegistrationDto dto;
    private ActualRegistrationEntity entity;

    @BeforeEach
    public void setUp() {
        repository = mock(ActualRegistrationRepository.class);
        mapper = mock(ActualRegistrationMapper.class);
        service = new ActualRegistrationServiceImp(repository, mapper);

        dto = new ActualRegistrationDto();
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

        entity = new ActualRegistrationEntity();
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
    public void testFindById_ValidId() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        ActualRegistrationDto result = service.findById(1L);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getCountry(), result.getCountry());
        assertEquals(dto.getRegion(), result.getRegion());
        assertEquals(dto.getCity(), result.getCity());
        assertEquals(dto.getDistrict(), result.getDistrict());
        assertEquals(dto.getLocality(), result.getLocality());
        assertEquals(dto.getStreet(), result.getStreet());
        assertEquals(dto.getHouseNumber(), result.getHouseNumber());
        assertEquals(dto.getHouseBlock(), result.getHouseBlock());
        assertEquals(dto.getFlatNumber(), result.getFlatNumber());
        assertEquals(dto.getIndex(), result.getIndex());

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

        ActualRegistrationDto result = service.save(dto);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getCountry(), result.getCountry());
        assertEquals(dto.getRegion(), result.getRegion());
        assertEquals(dto.getCity(), result.getCity());
        assertEquals(dto.getDistrict(), result.getDistrict());
        assertEquals(dto.getLocality(), result.getLocality());
        assertEquals(dto.getStreet(), result.getStreet());
        assertEquals(dto.getHouseNumber(), result.getHouseNumber());
        assertEquals(dto.getHouseBlock(), result.getHouseBlock());
        assertEquals(dto.getFlatNumber(), result.getFlatNumber());
        assertEquals(dto.getIndex(), result.getIndex());

        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    public void testUpdate() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(any(ActualRegistrationEntity.class))).thenReturn(entity);
        when(mapper.mergeToEntity(any(ActualRegistrationDto.class), any(ActualRegistrationEntity.class)))
                .thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        ActualRegistrationDto result = service.update(1L, dto);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getCountry(), result.getCountry());
        assertEquals(dto.getRegion(), result.getRegion());
        assertEquals(dto.getCity(), result.getCity());
        assertEquals(dto.getDistrict(), result.getDistrict());
        assertEquals(dto.getLocality(), result.getLocality());
        assertEquals(dto.getStreet(), result.getStreet());
        assertEquals(dto.getHouseNumber(), result.getHouseNumber());
        assertEquals(dto.getHouseBlock(), result.getHouseBlock());
        assertEquals(dto.getFlatNumber(), result.getFlatNumber());
        assertEquals(dto.getIndex(), result.getIndex());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(entity);
        verify(mapper, times(1)).mergeToEntity(dto, entity);
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    public void testFindAllById() {
        List<ActualRegistrationEntity> entities = Collections.singletonList(entity);
        when(repository.findAllById(anyList())).thenReturn(entities);
        when(mapper.toDtoList(entities)).thenReturn(Collections.singletonList(dto));

        List<ActualRegistrationDto> result = service.findAllById(Collections.singletonList(1L));

        assertNotNull(result);
        assertEquals(1, result.size());

        ActualRegistrationDto actualRegistrationDto = result.get(0);
        assertEquals(dto.getId(), actualRegistrationDto.getId());
        assertEquals(dto.getCountry(), actualRegistrationDto.getCountry());
        assertEquals(dto.getRegion(), actualRegistrationDto.getRegion());
        assertEquals(dto.getCity(), actualRegistrationDto.getCity());
        assertEquals(dto.getDistrict(), actualRegistrationDto.getDistrict());
        assertEquals(dto.getLocality(), actualRegistrationDto.getLocality());
        assertEquals(dto.getStreet(), actualRegistrationDto.getStreet());
        assertEquals(dto.getHouseNumber(), actualRegistrationDto.getHouseNumber());
        assertEquals(dto.getHouseBlock(), actualRegistrationDto.getHouseBlock());
        assertEquals(dto.getFlatNumber(), actualRegistrationDto.getFlatNumber());
        assertEquals(dto.getIndex(), actualRegistrationDto.getIndex());

        verify(repository, times(1)).findAllById(Collections.singletonList(1L));
        verify(mapper, times(1)).toDtoList(entities);
    }
}