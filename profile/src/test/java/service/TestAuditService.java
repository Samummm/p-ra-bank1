package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entity.AuditEntity;
import com.bank.profile.mapper.AuditMapper;
import com.bank.profile.repository.AuditRepository;
import com.bank.profile.service.AuditService;
import com.bank.profile.service.impl.AuditServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

public class TestAuditService {

    private AuditRepository repository;
    private AuditMapper mapper;
    private AuditService service;

    private AuditDto dto;
    private AuditEntity entity;

    @BeforeEach
    public void setUp() {
        repository = mock(AuditRepository.class);
        mapper = mock(AuditMapper.class);
        service = new AuditServiceImpl(repository, mapper);

        dto = new AuditDto();
        dto.setId(1L);
        dto.setEntityType("Dto");
        dto.setOperationType("Create");
        dto.setCreatedBy("User");
        dto.setModifiedBy("User");
        dto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        dto.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        dto.setNewEntityJson("New JSON");
        dto.setEntityJson("Old JSON");

        entity = new AuditEntity();
        entity.setId(1L);
        entity.setEntityType("Entity");
        entity.setOperationType("Create");
        entity.setCreatedBy("User");
        entity.setModifiedBy("User");
        entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        entity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        entity.setNewEntityJson("New JSON");
        entity.setEntityJson("Old JSON");
    }

    @Test
    public void testFindById_ValidId() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        AuditDto result = service.findById(1L);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getEntityType(), result.getEntityType());
        assertEquals(dto.getOperationType(), result.getOperationType());
        assertEquals(dto.getCreatedBy(), result.getCreatedBy());
        assertEquals(dto.getModifiedBy(), result.getModifiedBy());
        assertEquals(dto.getCreatedAt(), result.getCreatedAt());
        assertEquals(dto.getModifiedAt(), result.getModifiedAt());
        assertEquals(dto.getNewEntityJson(), result.getNewEntityJson());
        assertEquals(dto.getEntityJson(), result.getEntityJson());

        verify(repository, times(1)).findById(1L);
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    public void testFindById_InvalidId() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(1L));

        verify(repository, times(1)).findById(1L);
    }
}

