package com.bank.profile.mapper;

import com.bank.profile.dto.*;
import com.bank.profile.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестируем методы Mapper-а AccountDetailsIdMapper")
public class AccountDetailsIdMapperTest {

    private AccountDetailsIdMapperImpl mapperImpl = new AccountDetailsIdMapperImpl();
    private AccountDetailsIdMapper mapper = Mappers.getMapper(AccountDetailsIdMapper.class);
    private AccountDetailsIdEntity entity;
    private AccountDetailsIdDto dto;

    @BeforeEach
    public void setup() {
        entity = new AccountDetailsIdEntity();
        entity.setId(1L);
        entity.setAccountId(1L);

        dto = new AccountDetailsIdDto();
        dto.setId(2L);
        dto.setAccountId(2L);
    }

    @Test
    @DisplayName("тестируем преобразование Entity в Dto")
    public void toDtoTest() {
        AccountDetailsIdDto dto = mapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getAccountId());
    }

    @Test
    @DisplayName("тестируем преобразование Dto в Entity")
    public void toEntityTest() {
        AccountDetailsIdEntity entity = mapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(2L, entity.getAccountId());
    }

    @Test
    @DisplayName("тестируем обновление Entity на основе Dto")
    public void mergeToEntityTest() {
        AccountDetailsIdEntity mergedEntity = mapper.mergeToEntity(dto, entity);

        assertNotNull(mergedEntity);
        assertEquals(2L, mergedEntity.getAccountId());
    }

    @Test
    @DisplayName("тестируем преобразование списка Entity в список Dto")
    public void toDtoListTest() {
        List<AccountDetailsIdEntity> entityList = Collections.singletonList(entity);

        List<AccountDetailsIdDto> dtoList = mapper.toDtoList(entityList);

        assertNotNull(dtoList);
        assertAll(
                () -> assertEquals(1, dtoList.size()),
                () -> assertEquals(1L, dtoList.get(0).getId()),
                () -> assertEquals(1L, dtoList.get(0).getAccountId())
        );
    }

    @Test
    @DisplayName("тестируем преобразование Entity в Dto, на вход подан Null")
    public void toDtoNullTest() {
        AccountDetailsIdDto dto = mapper.toDto(null);

        assertNull(dto);
    }

    @Test
    @DisplayName("тестируем преобразование Dto в Entity, на вход подан Null")
    public void toEntityNullTest() {
        AccountDetailsIdEntity entity = mapper.toEntity(null);

        assertNull(entity);
    }

    @Test
    @DisplayName("тестируем обновление Entity на основе Dto, на вход подан Null")
    public void mergeToEntityNullTest() {
        AccountDetailsIdEntity mergedEntity = mapper.mergeToEntity(null, entity);

        assertNotNull(mergedEntity);
        assertEquals(1L, mergedEntity.getAccountId());
    }

    @Test
    @DisplayName("тестируем преобразование списка Entity в список Dto, на вход подан Null")
    public void toDtoListNullTest() {
        List<AccountDetailsIdDto> dtoList = mapper.toDtoList(null);

        assertNull(dtoList);
    }

    @Test
    @DisplayName("для Registration тестируем преобразование Dto в Entity, на вход подан Null")
    public void toEntityRegistrationNullTest() {
        assertNull(mapperImpl.registrationDtoToRegistrationEntity(null));
    }

    @Test
    @DisplayName("для Passport тестируем преобразование Dto в Entity, на вход подан Null")
    public void toEntityPassportNullTest() {
        assertNull(mapperImpl.passportDtoToPassportEntity(null));
    }

    @Test
    @DisplayName("для ActualRegistration тестируем преобразование Dto в Entity, на вход подан Null")
    public void toEntityActualRegistrationNullTest() {
        assertNull(mapperImpl.actualRegistrationDtoToActualRegistrationEntity(null));
    }

    @Test
    @DisplayName("для Profile тестируем преобразование Dto в Entity, на вход подан Null")
    public void toEntityProfileNullTest() {
        assertNull(mapperImpl.profileDtoToProfileEntity(null));
    }

    @Test
    @DisplayName("для Registration тестируем преобразование Entity в Dto, на вход подан Null")
    public void toDtoRegistrationNullTest() {
        assertNull(mapperImpl.registrationEntityToRegistrationDto(null));
    }

    @Test
    @DisplayName("для Passport тестируем преобразование Entity в Dto, на вход подан Null")
    public void toDtoPassportNullTest() {
        assertNull(mapperImpl.passportEntityToPassportDto(null));
    }

    @Test
    @DisplayName("для ActualRegistration тестируем преобразование Entity в Dto, на вход подан Null")
    public void toDtoActualRegistrationNullTest() {
        ActualRegistrationDto result = mapperImpl.actualRegistrationEntityToActualRegistrationDto(null);
        assertNull(result);
    }

    @Test
    @DisplayName("для Profile тестируем преобразование Entity в Dto, на вход подан Null")
    public void toDtoProfileNullTest() {
        ProfileDto result = mapperImpl.profileEntityToProfileDto(null);
        assertNull(result);
    }

    @Test
    @DisplayName("для Registration тестируем обновление Entity на основе Dto, на вход подан Null")
    public void mergeToEntityRegistrationNullTest() {
        RegistrationEntity target = new RegistrationEntity();
        mapperImpl.registrationDtoToRegistrationEntity1(null, target);

        assertAll(
                () -> assertNull(target.getId()),
                () -> assertNull(target.getCountry()),
                () -> assertNull(target.getRegion()),
                () -> assertNull(target.getCity()),
                () -> assertNull(target.getDistrict()),
                () -> assertNull(target.getLocality()),
                () -> assertNull(target.getStreet()),
                () -> assertNull(target.getHouseNumber()),
                () -> assertNull(target.getHouseBlock()),
                () -> assertNull(target.getFlatNumber()),
                () -> assertNull(target.getIndex())
        );
    }

    @Test
    @DisplayName("для Passport тестируем обновление Entity на основе Dto, на вход подан Null")
    public void mergeToEntityPassportNullTest() {
        PassportEntity mappingTarget = new PassportEntity();
        mapperImpl.passportDtoToPassportEntity1(null, mappingTarget);

        // TODO можно объединить в AssertAll, чтобы видеть какие ассерты падают и какие отрабатывают
        assertAll(
                () -> assertNull(mappingTarget.getId()),
                () -> assertNull(mappingTarget.getSeries()),
                () -> assertNull(mappingTarget.getNumber()),
                () -> assertNull(mappingTarget.getLastName()),
                () -> assertNull(mappingTarget.getFirstName()),
                () -> assertNull(mappingTarget.getMiddleName()),
                () -> assertNull(mappingTarget.getGender()),
                () -> assertNull(mappingTarget.getBirthDate()),
                () -> assertNull(mappingTarget.getBirthPlace()),
                () -> assertNull(mappingTarget.getIssuedBy()),
                () -> assertNull(mappingTarget.getDateOfIssue()),
                () -> assertNull(mappingTarget.getDivisionCode()),
                () -> assertNull(mappingTarget.getExpirationDate()),
                () -> assertNull(mappingTarget.getRegistration())
        );
    }

    @Test
    @DisplayName("для Registration тестируем преобразование Dto в Entity")
    public void toEntityRegistrationTest() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId(1L);
        registrationDto.setCountry("Country");
        registrationDto.setRegion("Region");
        registrationDto.setCity("City");
        registrationDto.setDistrict("District");
        registrationDto.setLocality("Locality");
        registrationDto.setStreet("Street");
        registrationDto.setHouseNumber("123");
        registrationDto.setHouseBlock("Block A");
        registrationDto.setFlatNumber("456");
        registrationDto.setIndex(789L);

        RegistrationEntity registrationEntity = mapperImpl.registrationDtoToRegistrationEntity(registrationDto);

        assertAll(
                () -> assertEquals(registrationDto.getId(), registrationEntity.getId()),
                () -> assertEquals(registrationDto.getCountry(), registrationEntity.getCountry()),
                () -> assertEquals(registrationDto.getRegion(), registrationEntity.getRegion()),
                () -> assertEquals(registrationDto.getCity(), registrationEntity.getCity()),
                () -> assertEquals(registrationDto.getDistrict(), registrationEntity.getDistrict()),
                () -> assertEquals(registrationDto.getLocality(), registrationEntity.getLocality()),
                () -> assertEquals(registrationDto.getStreet(), registrationEntity.getStreet()),
                () -> assertEquals(registrationDto.getHouseNumber(), registrationEntity.getHouseNumber()),
                () -> assertEquals(registrationDto.getHouseBlock(), registrationEntity.getHouseBlock()),
                () -> assertEquals(registrationDto.getFlatNumber(), registrationEntity.getFlatNumber()),
                () -> assertEquals(registrationDto.getIndex(), registrationEntity.getIndex())
        );
    }

    @Test
    @DisplayName("для Passport тестируем преобразование Dto в Entity")
    public void toEntityPassportTest() {
        PassportDto passportDto = new PassportDto();
        passportDto.setId(1L);
        passportDto.setSeries(123);
        passportDto.setNumber(456789L);
        passportDto.setLastName("LastName");
        passportDto.setFirstName("FirstName");
        passportDto.setMiddleName("MiddleName");
        passportDto.setGender("Male");
        passportDto.setBirthDate(LocalDate.of(1990, 1, 1));
        passportDto.setBirthPlace("BirthPlace");
        passportDto.setIssuedBy("IssuedBy");
        passportDto.setDateOfIssue(LocalDate.of(2010, 1, 1));
        passportDto.setDivisionCode(789);
        passportDto.setExpirationDate(LocalDate.of(2030, 1, 1));

        PassportEntity passportEntity = mapperImpl.passportDtoToPassportEntity(passportDto);

        assertAll(
                () -> assertEquals(passportDto.getId(), passportEntity.getId()),
                () -> assertEquals(passportDto.getSeries(), passportEntity.getSeries()),
                () -> assertEquals(passportDto.getNumber(), passportEntity.getNumber()),
                () -> assertEquals(passportDto.getLastName(), passportEntity.getLastName()),
                () -> assertEquals(passportDto.getFirstName(), passportEntity.getFirstName()),
                () -> assertEquals(passportDto.getMiddleName(), passportEntity.getMiddleName()),
                () -> assertEquals(passportDto.getGender(), passportEntity.getGender()),
                () -> assertEquals(passportDto.getBirthDate(), passportEntity.getBirthDate()),
                () -> assertEquals(passportDto.getBirthPlace(), passportEntity.getBirthPlace()),
                () -> assertEquals(passportDto.getIssuedBy(), passportEntity.getIssuedBy()),
                () -> assertEquals(passportDto.getDateOfIssue(), passportEntity.getDateOfIssue()),
                () -> assertEquals(passportDto.getDivisionCode(), passportEntity.getDivisionCode()),
                () -> assertEquals(passportDto.getExpirationDate(), passportEntity.getExpirationDate())
        );
    }

    @Test
    @DisplayName("для ActualRegistration тестируем преобразование Dto в Entity")
    public void toEntityActualRegistrationTest() {
        ActualRegistrationDto actualRegistrationDto = new ActualRegistrationDto();
        actualRegistrationDto.setId(1L);
        actualRegistrationDto.setCountry("Country");
        actualRegistrationDto.setRegion("Region");
        actualRegistrationDto.setCity("City");
        actualRegistrationDto.setDistrict("District");
        actualRegistrationDto.setLocality("Locality");
        actualRegistrationDto.setStreet("Street");
        actualRegistrationDto.setHouseNumber("123");
        actualRegistrationDto.setHouseBlock("Block");
        actualRegistrationDto.setFlatNumber("456");
        actualRegistrationDto.setIndex(789L);

        ActualRegistrationEntity actualRegistrationEntity =
                mapperImpl.actualRegistrationDtoToActualRegistrationEntity(actualRegistrationDto);

        assertAll(
                () -> assertEquals(actualRegistrationDto.getId(), actualRegistrationEntity.getId()),
                () -> assertEquals(actualRegistrationDto.getCountry(), actualRegistrationEntity.getCountry()),
                () -> assertEquals(actualRegistrationDto.getRegion(), actualRegistrationEntity.getRegion()),
                () -> assertEquals(actualRegistrationDto.getCity(), actualRegistrationEntity.getCity()),
                () -> assertEquals(actualRegistrationDto.getDistrict(), actualRegistrationEntity.getDistrict()),
                () -> assertEquals(actualRegistrationDto.getLocality(), actualRegistrationEntity.getLocality()),
                () -> assertEquals(actualRegistrationDto.getStreet(), actualRegistrationEntity.getStreet()),
                () -> assertEquals(actualRegistrationDto.getHouseNumber(), actualRegistrationEntity.getHouseNumber()),
                () -> assertEquals(actualRegistrationDto.getHouseBlock(), actualRegistrationEntity.getHouseBlock()),
                () -> assertEquals(actualRegistrationDto.getFlatNumber(), actualRegistrationEntity.getFlatNumber()),
                () -> assertEquals(actualRegistrationDto.getIndex(), actualRegistrationEntity.getIndex())
        );
    }

    @Test
    @DisplayName("для Profile тестируем преобразование Dto в Entity")
    public void toEntityProfileTest() {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(1L);
        profileDto.setPhoneNumber(1234567890L);
        profileDto.setEmail("test@example.com");
        profileDto.setNameOnCard("John Wick");
        profileDto.setInn(1234567890L);
        profileDto.setSnils(9876543210L);

        ProfileEntity profileEntity = mapperImpl.profileDtoToProfileEntity(profileDto);

        assertAll(
                () -> assertEquals(profileDto.getId(), profileEntity.getId()),
                () -> assertEquals(profileDto.getPhoneNumber(), profileEntity.getPhoneNumber()),
                () -> assertEquals(profileDto.getEmail(), profileEntity.getEmail()),
                () -> assertEquals(profileDto.getNameOnCard(), profileEntity.getNameOnCard()),
                () -> assertEquals(profileDto.getInn(), profileEntity.getInn()),
                () -> assertEquals(profileDto.getSnils(), profileEntity.getSnils())
        );
    }

    @Test
    @DisplayName("для Registration тестируем преобразование Entity в Dto")
    public void toDtoRegistrationTest() {
        RegistrationEntity registrationEntity = new RegistrationEntity();
        registrationEntity.setId(1L);
        registrationEntity.setCountry("Country");
        registrationEntity.setRegion("Region");
        registrationEntity.setCity("City");
        registrationEntity.setDistrict("District");
        registrationEntity.setLocality("Locality");
        registrationEntity.setStreet("Street");
        registrationEntity.setHouseNumber("123");
        registrationEntity.setHouseBlock("Block");
        registrationEntity.setFlatNumber("456");
        registrationEntity.setIndex(789L);

        RegistrationDto registrationDto = mapperImpl.registrationEntityToRegistrationDto(registrationEntity);

        assertAll(
                () -> assertEquals(registrationEntity.getId(), registrationDto.getId()),
                () -> assertEquals(registrationEntity.getCountry(), registrationDto.getCountry()),
                () -> assertEquals(registrationEntity.getRegion(), registrationDto.getRegion()),
                () -> assertEquals(registrationEntity.getCity(), registrationDto.getCity()),
                () -> assertEquals(registrationEntity.getDistrict(), registrationDto.getDistrict()),
                () -> assertEquals(registrationEntity.getLocality(), registrationDto.getLocality()),
                () -> assertEquals(registrationEntity.getStreet(), registrationDto.getStreet()),
                () -> assertEquals(registrationEntity.getHouseNumber(), registrationDto.getHouseNumber()),
                () -> assertEquals(registrationEntity.getHouseBlock(), registrationDto.getHouseBlock()),
                () -> assertEquals(registrationEntity.getFlatNumber(), registrationDto.getFlatNumber()),
                () -> assertEquals(registrationEntity.getIndex(), registrationDto.getIndex())
        );
    }

    @Test
    @DisplayName("для Passport тестируем преобразование Entity в Dto")
    public void toDtoPassportTest() {
        PassportEntity passportEntity = new PassportEntity();
        passportEntity.setId(1L);
        passportEntity.setSeries(123);
        passportEntity.setNumber(456L);
        passportEntity.setLastName("Last Name");
        passportEntity.setFirstName("First Name");
        passportEntity.setMiddleName("Middle Name");
        passportEntity.setGender("Male");
        passportEntity.setBirthDate(LocalDate.of(2000, 1, 1));
        passportEntity.setBirthPlace("Birth Place");
        passportEntity.setIssuedBy("Issued By");
        passportEntity.setDateOfIssue(LocalDate.of(2020, 1, 1));
        passportEntity.setDivisionCode(789);
        passportEntity.setExpirationDate(LocalDate.of(2030, 1, 1));

        PassportDto passportDto = mapperImpl.passportEntityToPassportDto(passportEntity);

        assertAll(
                () -> assertEquals(passportEntity.getId(), passportDto.getId()),
                () -> assertEquals(passportEntity.getSeries(), passportDto.getSeries()),
                () -> assertEquals(passportEntity.getNumber(), passportDto.getNumber()),
                () -> assertEquals(passportEntity.getLastName(), passportDto.getLastName()),
                () -> assertEquals(passportEntity.getFirstName(), passportDto.getFirstName()),
                () -> assertEquals(passportEntity.getMiddleName(), passportDto.getMiddleName()),
                () -> assertEquals(passportEntity.getGender(), passportDto.getGender()),
                () -> assertEquals(passportEntity.getBirthDate(), passportDto.getBirthDate()),
                () -> assertEquals(passportEntity.getBirthPlace(), passportDto.getBirthPlace()),
                () -> assertEquals(passportEntity.getIssuedBy(), passportDto.getIssuedBy()),
                () -> assertEquals(passportEntity.getDateOfIssue(), passportDto.getDateOfIssue()),
                () -> assertEquals(passportEntity.getDivisionCode(), passportDto.getDivisionCode()),
                () -> assertEquals(passportEntity.getExpirationDate(), passportDto.getExpirationDate())
        );
    }

    @Test
    @DisplayName("для ActualRegistration тестируем преобразование Entity в Dto")
    public void toDtoActualRegistrationTest() {
        ActualRegistrationEntity entity = new ActualRegistrationEntity();
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
        entity.setIndex(789L);

        ActualRegistrationDto dto = mapperImpl.actualRegistrationEntityToActualRegistrationDto(entity);

        assertAll(
                () -> assertNotNull(dto),
                () -> assertEquals(entity.getId(), dto.getId()),
                () -> assertEquals(entity.getCountry(), dto.getCountry()),
                () -> assertEquals(entity.getRegion(), dto.getRegion()),
                () -> assertEquals(entity.getCity(), dto.getCity()),
                () -> assertEquals(entity.getDistrict(), dto.getDistrict()),
                () -> assertEquals(entity.getLocality(), dto.getLocality()),
                () -> assertEquals(entity.getStreet(), dto.getStreet()),
                () -> assertEquals(entity.getHouseNumber(), dto.getHouseNumber()),
                () -> assertEquals(entity.getHouseBlock(), dto.getHouseBlock()),
                () -> assertEquals(entity.getFlatNumber(), dto.getFlatNumber()),
                () -> assertEquals(entity.getIndex(), dto.getIndex())
        );
    }

    @Test
    @DisplayName("для Profile тестируем преобразование Entity в Dto")
    public void toDtoProfileTest() {
        ProfileEntity entity = new ProfileEntity();
        entity.setId(1L);
        entity.setPhoneNumber(123456789L);
        entity.setEmail("test@example.com");
        entity.setNameOnCard("Test Name");
        entity.setInn(987654321L);
        entity.setSnils(12345678901234L);

        ProfileDto dto = mapperImpl.profileEntityToProfileDto(entity);

        assertAll(
                () -> assertNotNull(dto),
                () -> assertEquals(entity.getId(), dto.getId()),
                () -> assertEquals(entity.getPhoneNumber(), dto.getPhoneNumber()),
                () -> assertEquals(entity.getEmail(), dto.getEmail()),
                () -> assertEquals(entity.getNameOnCard(), dto.getNameOnCard()),
                () -> assertEquals(entity.getInn(), dto.getInn()),
                () -> assertEquals(entity.getSnils(), dto.getSnils())
        );
    }

    @Test
    @DisplayName("для Registration тестируем преобразование Dto в Entity")
    public void toEntityRegistrationTest2() {
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

        RegistrationEntity mappingTarget = new RegistrationEntity();
        mapperImpl.registrationDtoToRegistrationEntity1(dto, mappingTarget);

        assertAll(
                () -> assertEquals(dto.getId(), mappingTarget.getId()),
                () -> assertEquals(dto.getCountry(), mappingTarget.getCountry()),
                () -> assertEquals(dto.getRegion(), mappingTarget.getRegion()),
                () -> assertEquals(dto.getCity(), mappingTarget.getCity()),
                () -> assertEquals(dto.getDistrict(), mappingTarget.getDistrict()),
                () -> assertEquals(dto.getLocality(), mappingTarget.getLocality()),
                () -> assertEquals(dto.getStreet(), mappingTarget.getStreet()),
                () -> assertEquals(dto.getHouseNumber(), mappingTarget.getHouseNumber()),
                () -> assertEquals(dto.getHouseBlock(), mappingTarget.getHouseBlock()),
                () -> assertEquals(dto.getFlatNumber(), mappingTarget.getFlatNumber()),
                () -> assertEquals(dto.getIndex(), mappingTarget.getIndex())
        );
    }

    @Test
    @DisplayName("для Passport тестируем преобразование Dto в Entity")
    public void toEntityPassportTest2() {
        PassportDto dto = new PassportDto();
        dto.setId(1L);
        dto.setSeries(123);
        dto.setNumber(456L);
        dto.setLastName("Last");
        dto.setFirstName("First");
        dto.setMiddleName("Middle");
        dto.setGender("Male");
        dto.setBirthDate(LocalDate.of(2000, 1, 1));
        dto.setBirthPlace("Place");
        dto.setIssuedBy("Issue");
        dto.setDateOfIssue(LocalDate.of(2020, 1, 1));
        dto.setDivisionCode(789);
        dto.setExpirationDate(LocalDate.of(2030, 1, 1));

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId(2L);
        dto.setRegistration(registrationDto);

        PassportEntity mappingTarget = new PassportEntity();
        mapperImpl.passportDtoToPassportEntity1(dto, mappingTarget);

        assertAll(
                () -> assertEquals(dto.getId(), mappingTarget.getId()),
                () -> assertEquals(dto.getSeries(), mappingTarget.getSeries()),
                () -> assertEquals(dto.getNumber(), mappingTarget.getNumber()),
                () -> assertEquals(dto.getLastName(), mappingTarget.getLastName()),
                () -> assertEquals(dto.getFirstName(), mappingTarget.getFirstName()),
                () -> assertEquals(dto.getMiddleName(), mappingTarget.getMiddleName()),
                () -> assertEquals(dto.getGender(), mappingTarget.getGender()),
                () -> assertEquals(dto.getBirthDate(), mappingTarget.getBirthDate()),
                () -> assertEquals(dto.getBirthPlace(), mappingTarget.getBirthPlace()),
                () -> assertEquals(dto.getIssuedBy(), mappingTarget.getIssuedBy()),
                () -> assertEquals(dto.getDateOfIssue(), mappingTarget.getDateOfIssue()),
                () -> assertEquals(dto.getDivisionCode(), mappingTarget.getDivisionCode()),
                () -> assertEquals(dto.getExpirationDate(), mappingTarget.getExpirationDate()),
                () -> assertNotNull(mappingTarget.getRegistration()),
                () -> assertEquals(registrationDto.getId(), mappingTarget.getRegistration().getId())
        );
    }
}