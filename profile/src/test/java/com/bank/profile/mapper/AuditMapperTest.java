package com.bank.profile.mapper;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entity.AuditEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестируем методы Mapper-а AuditMapper")
public class AuditMapperTest {

    private AuditMapper mapper = Mappers.getMapper(AuditMapper.class);
    private AuditEntity entity;

    @BeforeEach
    public void setup() {
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
    @DisplayName("тестируем преобразование Entity в Dto")
    public void toDtoTest() {
        AuditDto dto = mapper.toDto(entity);

        assertAll(
                () -> assertEquals(entity.getId(), dto.getId()),
                () -> assertEquals(entity.getEntityType(), dto.getEntityType()),
                () -> assertEquals(entity.getOperationType(), dto.getOperationType()),
                () -> assertEquals(entity.getCreatedBy(), dto.getCreatedBy()),
                () -> assertEquals(entity.getModifiedBy(), dto.getModifiedBy()),
                () -> assertEquals(entity.getCreatedAt(), dto.getCreatedAt()),
                () -> assertEquals(entity.getModifiedAt(), dto.getModifiedAt()),
                () -> assertEquals(entity.getNewEntityJson(), dto.getNewEntityJson()),
                () -> assertEquals(entity.getEntityJson(), dto.getEntityJson())
        );
    }

    @Test
    @DisplayName("тестируем преобразование Entity в Dto, на вход подан Null")
    public void toDtoNullTest() {
        AuditDto dto = mapper.toDto(null);

        assertNull(dto);
    }
}

