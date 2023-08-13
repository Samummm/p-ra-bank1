package mapper;


import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.ProfileEntity;
import com.bank.profile.mapper.ProfileMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestProfileMapper {

    private ProfileMapper mapper = Mappers.getMapper(ProfileMapper.class);

    private ProfileEntity entity;
    private ProfileDto dto;

    @BeforeEach
    public void setup() {
        entity = new ProfileEntity();
        entity.setId(1L);
        entity.setPhoneNumber(1234567890L);
        entity.setEmail("test@example.com");
        entity.setNameOnCard("John Wick");
        entity.setInn(123456789012L);
        entity.setSnils(98765432109L);


        dto = new ProfileDto();
        dto.setId(2L);
        dto.setPhoneNumber(9876543210L);
        dto.setEmail("newtest@example.com");
        dto.setNameOnCard("Sofia Al-Azwar");
        dto.setInn(5678912345678L);
        dto.setSnils(78945612378L);
    }

    @Test
    public void testToEntity() {
        ProfileEntity resultEntity = mapper.toEntity(dto);

        assertEquals(dto.getPhoneNumber(), resultEntity.getPhoneNumber());
        assertEquals(dto.getEmail(), resultEntity.getEmail());
        assertEquals(dto.getNameOnCard(), resultEntity.getNameOnCard());
        assertEquals(dto.getInn(), resultEntity.getInn());
        assertEquals(dto.getSnils(), resultEntity.getSnils());
    }

    @Test
    public void testToDto() {
        ProfileDto resultDto = mapper.toDto(entity);

        assertEquals(entity.getId(), resultDto.getId());
        assertEquals(entity.getPhoneNumber(), resultDto.getPhoneNumber());
        assertEquals(entity.getEmail(), resultDto.getEmail());
        assertEquals(entity.getNameOnCard(), resultDto.getNameOnCard());
        assertEquals(entity.getInn(), resultDto.getInn());
        assertEquals(entity.getSnils(), resultDto.getSnils());
    }

    @Test
    public void testMergeToEntity() {
        ProfileEntity mergedEntity = mapper.mergeToEntity(dto, entity);

        assertEquals(dto.getPhoneNumber(), mergedEntity.getPhoneNumber());
        assertEquals(dto.getEmail(), mergedEntity.getEmail());
        assertEquals(dto.getNameOnCard(), mergedEntity.getNameOnCard());
        assertEquals(dto.getInn(), mergedEntity.getInn());
        assertEquals(dto.getSnils(), mergedEntity.getSnils());
    }

    @Test
    public void testToDtoList() {
        List<ProfileEntity> entityList = Collections.singletonList(entity);

        List<ProfileDto> dtoList = mapper.toDtoList(entityList);

        assertEquals(1, dtoList.size());
        ProfileDto resultDto = dtoList.get(0);

        assertEquals(entity.getId(), resultDto.getId());
        assertEquals(entity.getPhoneNumber(), resultDto.getPhoneNumber());
        assertEquals(entity.getEmail(), resultDto.getEmail());
        assertEquals(entity.getNameOnCard(), resultDto.getNameOnCard());
        assertEquals(entity.getInn(), resultDto.getInn());
        assertEquals(entity.getSnils(), resultDto.getSnils());
    }

    @Test
    public void testToDto_NullInput() {
        entity = null;

        ProfileDto dto = mapper.toDto(entity);

        assertNull(dto);
    }
}
