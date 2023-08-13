package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.ProfileEntity;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.ProfileService;
import com.bank.profile.service.impl.ProfileServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TestProfileService {

    private ProfileRepository repository;
    private ProfileMapper mapper;
    private ProfileService service;

    private ProfileDto dto;
    private ProfileEntity entity;

    @BeforeEach
    public void setUp() {
        repository = mock(ProfileRepository.class);
        mapper = mock(ProfileMapper.class);
        service = new ProfileServiceImp(repository, mapper);

        dto = new ProfileDto();
        dto.setId(1L);
        dto.setPhoneNumber(1234567890L);
        dto.setEmail("test@example.com");
        dto.setNameOnCard("John Wick");
        dto.setInn(123456789012L);
        dto.setSnils(9876543210L);

        entity = new ProfileEntity();
        entity.setId(1L);
        entity.setPhoneNumber(1234567890L);
        entity.setEmail("test@example.com");
        entity.setNameOnCard("Sofia Al-Azwar");
        entity.setInn(123456789012L);
        entity.setSnils(9876543210L);
    }

    @Test
    public void testFindById_ValidId() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        ProfileDto result = service.findById(1L);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getNameOnCard(), result.getNameOnCard());
        assertEquals(dto.getInn(), result.getInn());
        assertEquals(dto.getSnils(), result.getSnils());

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

        ProfileDto result = service.save(dto);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getNameOnCard(), result.getNameOnCard());
        assertEquals(dto.getInn(), result.getInn());
        assertEquals(dto.getSnils(), result.getSnils());

        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    public void testUpdate() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(any(ProfileEntity.class))).thenReturn(entity);
        when(mapper.mergeToEntity(any(ProfileDto.class), any(ProfileEntity.class)))
                .thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        ProfileDto result = service.update(1L, dto);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getNameOnCard(), result.getNameOnCard());
        assertEquals(dto.getInn(), result.getInn());
        assertEquals(dto.getSnils(), result.getSnils());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(entity);
        verify(mapper, times(1)).mergeToEntity(dto, entity);
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    public void testFindAllById() {
        List<ProfileEntity> entities = Collections.singletonList(entity);
        when(repository.findAllById(anyList())).thenReturn(entities);
        when(mapper.toDtoList(entities)).thenReturn(Collections.singletonList(dto));

        List<ProfileDto> result = service.findAllById(Collections.singletonList(1L));

        assertNotNull(result);
        assertEquals(1, result.size());

        ProfileDto actualDto = result.get(0);
        assertEquals(dto.getId(), actualDto.getId());
        assertEquals(dto.getPhoneNumber(), actualDto.getPhoneNumber());
        assertEquals(dto.getEmail(), actualDto.getEmail());
        assertEquals(dto.getNameOnCard(), actualDto.getNameOnCard());
        assertEquals(dto.getInn(), actualDto.getInn());
        assertEquals(dto.getSnils(), actualDto.getSnils());

        verify(repository, times(1)).findAllById(Collections.singletonList(1L));
        verify(mapper, times(1)).toDtoList(entities);
    }
}
