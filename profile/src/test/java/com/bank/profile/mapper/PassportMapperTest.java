package com.bank.profile.mapper;


import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.PassportEntity;
import com.bank.profile.entity.RegistrationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестируем методы Mapper-а PassportMapper")
public class PassportMapperTest {

    private PassportMapperImpl mapperImpl = new PassportMapperImpl();
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
    @DisplayName("тестируем преобразование Dto в Entity")
    public void toEntityTest() {
        PassportEntity resultEntity = mapper.toEntity(dto);

        assertAll(
                () -> assertEquals(dto.getSeries(), resultEntity.getSeries()),
                () -> assertEquals(dto.getNumber(), resultEntity.getNumber()),
                () -> assertEquals(dto.getLastName(), resultEntity.getLastName()),
                () -> assertEquals(dto.getFirstName(), resultEntity.getFirstName()),
                () -> assertEquals(dto.getMiddleName(), resultEntity.getMiddleName()),
                () -> assertEquals(dto.getGender(), resultEntity.getGender()),
                () -> assertEquals(dto.getBirthDate(), resultEntity.getBirthDate()),
                () -> assertEquals(dto.getBirthPlace(), resultEntity.getBirthPlace()),
                () -> assertEquals(dto.getIssuedBy(), resultEntity.getIssuedBy()),
                () -> assertEquals(dto.getDateOfIssue(), resultEntity.getDateOfIssue()),
                () -> assertEquals(dto.getDivisionCode(), resultEntity.getDivisionCode()),
                () -> assertEquals(dto.getExpirationDate(), resultEntity.getExpirationDate())
        );
    }

    @Test
    @DisplayName("тестируем преобразование Entity в Dto")
    public void toDtoTest() {
        PassportDto resultDto = mapper.toDto(entity);

        assertAll(
                () -> assertEquals(entity.getId(), resultDto.getId()),
                () -> assertEquals(entity.getSeries(), resultDto.getSeries()),
                () -> assertEquals(entity.getNumber(), resultDto.getNumber()),
                () -> assertEquals(entity.getLastName(), resultDto.getLastName()),
                () -> assertEquals(entity.getFirstName(), resultDto.getFirstName()),
                () -> assertEquals(entity.getMiddleName(), resultDto.getMiddleName()),
                () -> assertEquals(entity.getGender(), resultDto.getGender()),
                () -> assertEquals(entity.getBirthDate(), resultDto.getBirthDate()),
                () -> assertEquals(entity.getBirthPlace(), resultDto.getBirthPlace()),
                () -> assertEquals(entity.getIssuedBy(), resultDto.getIssuedBy()),
                () -> assertEquals(entity.getDateOfIssue(), resultDto.getDateOfIssue()),
                () -> assertEquals(entity.getDivisionCode(), resultDto.getDivisionCode()),
                () -> assertEquals(entity.getExpirationDate(), resultDto.getExpirationDate())
        );
    }

    @Test
    @DisplayName("тестируем обновление Entity на основе Dto")
    public void mergeToEntityTest() {
        PassportEntity mergedEntity = mapper.mergeToEntity(dto, entity);

        assertAll(
                () -> assertEquals(dto.getSeries(), mergedEntity.getSeries()),
                () -> assertEquals(dto.getNumber(), mergedEntity.getNumber()),
                () -> assertEquals(dto.getLastName(), mergedEntity.getLastName()),
                () -> assertEquals(dto.getFirstName(), mergedEntity.getFirstName()),
                () -> assertEquals(dto.getMiddleName(), mergedEntity.getMiddleName()),
                () -> assertEquals(dto.getGender(), mergedEntity.getGender()),
                () -> assertEquals(dto.getBirthDate(), mergedEntity.getBirthDate()),
                () -> assertEquals(dto.getBirthPlace(), mergedEntity.getBirthPlace()),
                () -> assertEquals(dto.getIssuedBy(), mergedEntity.getIssuedBy()),
                () -> assertEquals(dto.getDateOfIssue(), mergedEntity.getDateOfIssue()),
                () -> assertEquals(dto.getDivisionCode(), mergedEntity.getDivisionCode()),
                () -> assertEquals(dto.getExpirationDate(), mergedEntity.getExpirationDate())
        );
    }

    @Test
    @DisplayName("тестируем преобразование списка Entity в список Dto")
    public void toDtoListTest() {
        List<PassportEntity> entityList = Collections.singletonList(entity);

        List<PassportDto> dtoList = mapper.toDtoList(entityList);

        assertEquals(1, dtoList.size());
        PassportDto resultDto = dtoList.get(0);

        assertAll(
                () -> assertEquals(entity.getId(), resultDto.getId()),
                () -> assertEquals(entity.getSeries(), resultDto.getSeries()),
                () -> assertEquals(entity.getNumber(), resultDto.getNumber()),
                () -> assertEquals(entity.getLastName(), resultDto.getLastName()),
                () -> assertEquals(entity.getFirstName(), resultDto.getFirstName()),
                () -> assertEquals(entity.getMiddleName(), resultDto.getMiddleName()),
                () -> assertEquals(entity.getGender(), resultDto.getGender()),
                () -> assertEquals(entity.getBirthDate(), resultDto.getBirthDate()),
                () -> assertEquals(entity.getBirthPlace(), resultDto.getBirthPlace()),
                () -> assertEquals(entity.getIssuedBy(), resultDto.getIssuedBy()),
                () -> assertEquals(entity.getDateOfIssue(), resultDto.getDateOfIssue()),
                () -> assertEquals(entity.getDivisionCode(), resultDto.getDivisionCode()),
                () -> assertEquals(entity.getExpirationDate(), resultDto.getExpirationDate())
        );
    }

    @Test
    @DisplayName("тестируем преобразование Entity в Dto, на вход подан Null")
    public void toDtoNullTest() {
        PassportDto dto = mapper.toDto(null);

        assertNull(dto);
    }

    @Test
    @DisplayName("для Registration тестируем преобразование Dto в Entity, на вход подан Null")
    public void toEntityRegistrationNullTest() {
        RegistrationEntity result = mapperImpl.registrationDtoToRegistrationEntity(null);
        assertNull(result);
    }

    @Test
    @DisplayName("для Registration тестируем обновление Entity на основе Dto, на вход подан Null")
    public void mergeToEntityRegistrationNullTest() {
        RegistrationEntity entity = new RegistrationEntity();
        entity.setId(1L);
        entity.setCountry("Country");
        entity.setRegion("Region");
        entity.setCity("City");
        entity.setDistrict("District");
        entity.setLocality("Locality");
        entity.setStreet("Street");
        entity.setHouseNumber("321");
        entity.setHouseBlock("Block");
        entity.setFlatNumber("123");
        entity.setIndex(123456L);

        mapperImpl.registrationDtoToRegistrationEntity1(null, entity);

        assertAll(
                () -> assertEquals(1L, entity.getId()),
                () -> assertEquals("Country", entity.getCountry()),
                () -> assertEquals("Region", entity.getRegion()),
                () -> assertEquals("City", entity.getCity()),
                () -> assertEquals("District", entity.getDistrict()),
                () -> assertEquals("Locality", entity.getLocality()),
                () -> assertEquals("Street", entity.getStreet()),
                () -> assertEquals("321", entity.getHouseNumber()),
                () -> assertEquals("Block", entity.getHouseBlock()),
                () -> assertEquals("123", entity.getFlatNumber()),
                () -> assertEquals(123456L, entity.getIndex())
        );
    }

    @Test
    @DisplayName("для Registration тестируем преобразование Dto в Entity")
    public void toEntityRegistrationTest() {
        RegistrationDto dto = new RegistrationDto();
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
        dto.setIndex(789L);

        RegistrationEntity result = mapperImpl.registrationDtoToRegistrationEntity(dto);

        assertAll(
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
    @DisplayName("для Registration тестируем обновление Entity на основе Dto")
    public void mergeToEntityRegistrationTest() {
        RegistrationDto dto = new RegistrationDto();
        dto.setId(2L);
        dto.setCountry("Country2");
        dto.setRegion("Region2");
        dto.setCity("City2");
        dto.setDistrict("District2");
        dto.setLocality("Locality2");
        dto.setStreet("Street2");
        dto.setHouseNumber("123");
        dto.setHouseBlock("Block2");
        dto.setFlatNumber("321");
        dto.setIndex(654321L);

        RegistrationEntity entity = new RegistrationEntity();
        entity.setId(1L);
        entity.setCountry("Country");
        entity.setRegion("Region");
        entity.setCity("City");
        entity.setDistrict("District");
        entity.setLocality("Locality");
        entity.setStreet("Street");
        entity.setHouseNumber("321");
        entity.setHouseBlock("Block");
        entity.setFlatNumber("123");
        entity.setIndex(123456L);

        mapperImpl.registrationDtoToRegistrationEntity1(dto, entity);

        assertAll(
                () -> assertEquals(dto.getId(), entity.getId()),
                () -> assertEquals(dto.getCountry(), entity.getCountry()),
                () -> assertEquals(dto.getRegion(), entity.getRegion()),
                () -> assertEquals(dto.getCity(), entity.getCity()),
                () -> assertEquals(dto.getDistrict(), entity.getDistrict()),
                () -> assertEquals(dto.getLocality(), entity.getLocality()),
                () -> assertEquals(dto.getStreet(), entity.getStreet()),
                () -> assertEquals(dto.getHouseNumber(), entity.getHouseNumber()),
                () -> assertEquals(dto.getHouseBlock(), entity.getHouseBlock()),
                () -> assertEquals(dto.getFlatNumber(), entity.getFlatNumber()),
                () -> assertEquals(dto.getIndex(), entity.getIndex())
        );
    }
}