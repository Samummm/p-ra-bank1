package mapper;


import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.PassportEntity;
import com.bank.profile.mapper.PassportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestPassportMapper {

    private PassportMapper mapper = Mappers.getMapper(PassportMapper.class);

    private PassportEntity entity;
    private PassportDto dto;

    @BeforeEach
    public void setup() {
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

        dto = new PassportDto();
        dto.setId(2L);
        dto.setSeries(4321);
        dto.setNumber(987654L);
        dto.setLastName("NewLast");
        dto.setFirstName("NewFirst");
        dto.setMiddleName("NewMiddle");
        dto.setGender("Female");
        dto.setBirthDate(LocalDate.of(1995, 3, 20));
        dto.setBirthPlace("NewBirthPlace");
        dto.setIssuedBy("NewIssuer");
        dto.setDateOfIssue(LocalDate.of(2022, 6, 1));
        dto.setDivisionCode(654321);
        dto.setExpirationDate(LocalDate.of(2035, 10, 15));
    }

    @Test
    public void testToEntity() {
        PassportEntity resultEntity = mapper.toEntity(dto);

        assertEquals(dto.getSeries(), resultEntity.getSeries());
        assertEquals(dto.getNumber(), resultEntity.getNumber());
        assertEquals(dto.getLastName(), resultEntity.getLastName());
        assertEquals(dto.getFirstName(), resultEntity.getFirstName());
        assertEquals(dto.getMiddleName(), resultEntity.getMiddleName());
        assertEquals(dto.getGender(), resultEntity.getGender());
        assertEquals(dto.getBirthDate(), resultEntity.getBirthDate());
        assertEquals(dto.getBirthPlace(), resultEntity.getBirthPlace());
        assertEquals(dto.getIssuedBy(), resultEntity.getIssuedBy());
        assertEquals(dto.getDateOfIssue(), resultEntity.getDateOfIssue());
        assertEquals(dto.getDivisionCode(), resultEntity.getDivisionCode());
        assertEquals(dto.getExpirationDate(), resultEntity.getExpirationDate());
    }

    @Test
    public void testToDto() {
        PassportDto resultDto = mapper.toDto(entity);

        assertEquals(entity.getId(), resultDto.getId());
        assertEquals(entity.getSeries(), resultDto.getSeries());
        assertEquals(entity.getNumber(), resultDto.getNumber());
        assertEquals(entity.getLastName(), resultDto.getLastName());
        assertEquals(entity.getFirstName(), resultDto.getFirstName());
        assertEquals(entity.getMiddleName(), resultDto.getMiddleName());
        assertEquals(entity.getGender(), resultDto.getGender());
        assertEquals(entity.getBirthDate(), resultDto.getBirthDate());
        assertEquals(entity.getBirthPlace(), resultDto.getBirthPlace());
        assertEquals(entity.getIssuedBy(), resultDto.getIssuedBy());
        assertEquals(entity.getDateOfIssue(), resultDto.getDateOfIssue());
        assertEquals(entity.getDivisionCode(), resultDto.getDivisionCode());
        assertEquals(entity.getExpirationDate(), resultDto.getExpirationDate());
    }

    @Test
    public void testMergeToEntity() {
        PassportEntity mergedEntity = mapper.mergeToEntity(dto, entity);

        assertEquals(dto.getSeries(), mergedEntity.getSeries());
        assertEquals(dto.getNumber(), mergedEntity.getNumber());
        assertEquals(dto.getLastName(), mergedEntity.getLastName());
        assertEquals(dto.getFirstName(), mergedEntity.getFirstName());
        assertEquals(dto.getMiddleName(), mergedEntity.getMiddleName());
        assertEquals(dto.getGender(), mergedEntity.getGender());
        assertEquals(dto.getBirthDate(), mergedEntity.getBirthDate());
        assertEquals(dto.getBirthPlace(), mergedEntity.getBirthPlace());
        assertEquals(dto.getIssuedBy(), mergedEntity.getIssuedBy());
        assertEquals(dto.getDateOfIssue(), mergedEntity.getDateOfIssue());
        assertEquals(dto.getDivisionCode(), mergedEntity.getDivisionCode());
        assertEquals(dto.getExpirationDate(), mergedEntity.getExpirationDate());
    }

    @Test
    public void testToDtoList() {
        List<PassportEntity> entityList = Collections.singletonList(entity);

        List<PassportDto> dtoList = mapper.toDtoList(entityList);

        assertEquals(1, dtoList.size());
        PassportDto resultDto = dtoList.get(0);

        assertEquals(entity.getId(), resultDto.getId());
        assertEquals(entity.getSeries(), resultDto.getSeries());
        assertEquals(entity.getNumber(), resultDto.getNumber());
        assertEquals(entity.getLastName(), resultDto.getLastName());
        assertEquals(entity.getFirstName(), resultDto.getFirstName());
        assertEquals(entity.getMiddleName(), resultDto.getMiddleName());
        assertEquals(entity.getGender(), resultDto.getGender());
        assertEquals(entity.getBirthDate(), resultDto.getBirthDate());
        assertEquals(entity.getBirthPlace(), resultDto.getBirthPlace());
        assertEquals(entity.getIssuedBy(), resultDto.getIssuedBy());
        assertEquals(entity.getDateOfIssue(), resultDto.getDateOfIssue());
        assertEquals(entity.getDivisionCode(), resultDto.getDivisionCode());
        assertEquals(entity.getExpirationDate(), resultDto.getExpirationDate());
    }

    @Test
    public void testToDto_NullInput() {
        entity = null;

        PassportDto dto = mapper.toDto(entity);

        assertNull(dto);
    }
}
