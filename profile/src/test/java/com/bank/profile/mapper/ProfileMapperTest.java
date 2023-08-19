package com.bank.profile.mapper;


import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.ActualRegistrationEntity;
import com.bank.profile.entity.PassportEntity;
import com.bank.profile.entity.ProfileEntity;
import com.bank.profile.entity.RegistrationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileMapperTest {
    // TODO отрефакторить также, кака в AccountDetailsIdMapperTest
    private ProfileMapperImpl mapperImpl = new ProfileMapperImpl();

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
    public void testActualRegistrationDtoToActualRegistrationEntity1_NullDto() {
        ActualRegistrationEntity target = new ActualRegistrationEntity();
        mapperImpl.actualRegistrationDtoToActualRegistrationEntity1(null, target);
        assertNull(target.getId());
        assertNull(target.getCountry());
        assertNull(target.getRegion());
        assertNull(target.getCity());
        assertNull(target.getDistrict());
        assertNull(target.getLocality());
        assertNull(target.getStreet());
        assertNull(target.getHouseNumber());
        assertNull(target.getHouseBlock());
        assertNull(target.getFlatNumber());
        assertNull(target.getIndex());
    }

//   -----------------------------------------------------------------------
    @Test
    public void testToDto_NullInput() {
        entity = null;

        ProfileDto dto = mapper.toDto(entity);

        assertNull(dto);
    }

    @Test
    public void testRegistrationDtoToRegistrationEntity_NullInput() {
        RegistrationEntity result = mapperImpl.registrationDtoToRegistrationEntity(null);

        assertNull(result);
    }

    @Test
    public void testPassportDtoToPassportEntity_NullInput() {
        PassportEntity result = mapperImpl.passportDtoToPassportEntity(null);
        assertNull(result);
    }

    @Test
    public void testActualRegistrationDtoToActualRegistrationEntity_NullInput() {
        ActualRegistrationEntity result = mapperImpl.actualRegistrationDtoToActualRegistrationEntity(null);
        assertNull(result);
    }

    @Test
    public void testRegistrationEntityToRegistrationDto_NullInput() {
        RegistrationDto result = mapperImpl.registrationEntityToRegistrationDto(null);
        assertNull(result);
    }

    @Test
    public void testPassportEntityToPassportDto_NullInput() {
        PassportDto result = mapperImpl.passportEntityToPassportDto(null);
        assertNull(result);
    }

    @Test
    public void testActualRegistrationEntityToActualRegistrationDto_NullInput() {
        ActualRegistrationDto result = mapperImpl.actualRegistrationEntityToActualRegistrationDto(null);
        assertNull(result);
    }

    @Test
    public void testRegistrationDtoToRegistrationEntity1_NullInput() {
        RegistrationEntity target = new RegistrationEntity();
        mapperImpl.registrationDtoToRegistrationEntity1(null, target);
        assertNull(target.getId());
        assertNull(target.getCountry());
        assertNull(target.getRegion());
        assertNull(target.getCity());
        assertNull(target.getDistrict());
        assertNull(target.getLocality());
        assertNull(target.getStreet());
        assertNull(target.getHouseNumber());
        assertNull(target.getHouseBlock());
        assertNull(target.getFlatNumber());
        assertNull(target.getIndex());
    }
//    ---------------------------------------------------------------------------

    @Test
    public void testRegistrationDtoToRegistrationEntity() {
        RegistrationDto dto = new RegistrationDto();
        dto.setId(1L);
        dto.setCountry("Country");
        dto.setRegion("Region");
        dto.setCity("City");
        dto.setDistrict("District");
        dto.setLocality("Locality");
        dto.setStreet("Street");
        dto.setHouseNumber("123");
        dto.setHouseBlock("Block");
        dto.setFlatNumber("456");
        dto.setIndex(789L);

        RegistrationEntity result = mapperImpl.registrationDtoToRegistrationEntity(dto);

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
    }

    @Test
    public void testPassportDtoToPassportEntity() {
        PassportDto dto = new PassportDto();
        dto.setId(1L);
        dto.setSeries(123);
        dto.setNumber(456L);
        dto.setLastName("Last");
        dto.setFirstName("First");
        dto.setMiddleName("Middle");
        dto.setGender("Male");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));
        dto.setBirthPlace("Place");
        dto.setIssuedBy("Authority");
        dto.setDateOfIssue(LocalDate.of(2020, 1, 1));
        dto.setDivisionCode(789);
        dto.setExpirationDate(LocalDate.of(2030, 1, 1));

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId(1L);
        dto.setRegistration(registrationDto);

        PassportEntity result = mapperImpl.passportDtoToPassportEntity(dto);

        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getSeries(), result.getSeries());
        assertEquals(dto.getNumber(), result.getNumber());
        assertEquals(dto.getLastName(), result.getLastName());
        assertEquals(dto.getFirstName(), result.getFirstName());
        assertEquals(dto.getMiddleName(), result.getMiddleName());
        assertEquals(dto.getGender(), result.getGender());
        assertEquals(dto.getBirthDate(), result.getBirthDate());
        assertEquals(dto.getBirthPlace(), result.getBirthPlace());
        assertEquals(dto.getIssuedBy(), result.getIssuedBy());
        assertEquals(dto.getDateOfIssue(), result.getDateOfIssue());
        assertEquals(dto.getDivisionCode(), result.getDivisionCode());
        assertEquals(dto.getExpirationDate(), result.getExpirationDate());
        assertNotNull(result.getRegistration());
    }

    @Test
    public void testActualRegistrationDtoToActualRegistrationEntity() {
        ActualRegistrationDto dto = new ActualRegistrationDto();
        dto.setId(1L);
        dto.setCountry("Country");
        dto.setRegion("Region");
        dto.setCity("City");
        dto.setDistrict("District");
        dto.setLocality("Locality");
        dto.setStreet("Street");
        dto.setHouseNumber("123");
        dto.setHouseBlock("Block");
        dto.setFlatNumber("456");
        dto.setIndex(789L);

        ActualRegistrationEntity result = mapperImpl.actualRegistrationDtoToActualRegistrationEntity(dto);

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
    }

    @Test
    public void testRegistrationEntityToRegistrationDto() {
        RegistrationEntity entity = new RegistrationEntity();
        entity.setId(1L);
        entity.setCountry("Country");
        entity.setRegion("Region");
        entity.setCity("City");
        entity.setDistrict("District");
        entity.setLocality("Locality");
        entity.setStreet("Street");
        entity.setHouseNumber("123");
        entity.setHouseBlock("Block");
        entity.setFlatNumber("456");
        entity.setIndex(789L);

        RegistrationDto result = mapperImpl.registrationEntityToRegistrationDto(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getCountry(), result.getCountry());
        assertEquals(entity.getRegion(), result.getRegion());
        assertEquals(entity.getCity(), result.getCity());
        assertEquals(entity.getDistrict(), result.getDistrict());
        assertEquals(entity.getLocality(), result.getLocality());
        assertEquals(entity.getStreet(), result.getStreet());
        assertEquals(entity.getHouseNumber(), result.getHouseNumber());
        assertEquals(entity.getHouseBlock(), result.getHouseBlock());
        assertEquals(entity.getFlatNumber(), result.getFlatNumber());
        assertEquals(entity.getIndex(), result.getIndex());
    }

    @Test
    public void testPassportEntityToPassportDto() {
        PassportEntity entity = new PassportEntity();
        entity.setId(1L);
        entity.setSeries(123);
        entity.setNumber(456L);
        entity.setLastName("Last");
        entity.setFirstName("First");
        entity.setMiddleName("Middle");
        entity.setGender("Male");
        entity.setBirthDate(LocalDate.of(1990, 1, 1));
        entity.setBirthPlace("Birthplace");
        entity.setIssuedBy("Issuer");
        entity.setDateOfIssue(LocalDate.of(2020, 1, 1));
        entity.setDivisionCode(789);
        entity.setExpirationDate(LocalDate.of(2030, 1, 1));

        RegistrationEntity registrationEntity = new RegistrationEntity();
        registrationEntity.setId(2L);
        entity.setRegistration(registrationEntity);

        PassportDto result = mapperImpl.passportEntityToPassportDto(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getSeries(), result.getSeries());
        assertEquals(entity.getNumber(), result.getNumber());
        assertEquals(entity.getLastName(), result.getLastName());
        assertEquals(entity.getFirstName(), result.getFirstName());
        assertEquals(entity.getMiddleName(), result.getMiddleName());
        assertEquals(entity.getGender(), result.getGender());
        assertEquals(entity.getBirthDate(), result.getBirthDate());
        assertEquals(entity.getBirthPlace(), result.getBirthPlace());
        assertEquals(entity.getIssuedBy(), result.getIssuedBy());
        assertEquals(entity.getDateOfIssue(), result.getDateOfIssue());
        assertEquals(entity.getDivisionCode(), result.getDivisionCode());
        assertEquals(entity.getExpirationDate(), result.getExpirationDate());
        assertNotNull(result.getRegistration());
        assertEquals(entity.getRegistration().getId(), result.getRegistration().getId());
    }

    @Test
    public void testActualRegistrationEntityToActualRegistrationDto() {
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

        ActualRegistrationDto result = mapperImpl.actualRegistrationEntityToActualRegistrationDto(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getCountry(), result.getCountry());
        assertEquals(entity.getRegion(), result.getRegion());
        assertEquals(entity.getCity(), result.getCity());
        assertEquals(entity.getDistrict(), result.getDistrict());
        assertEquals(entity.getLocality(), result.getLocality());
        assertEquals(entity.getStreet(), result.getStreet());
        assertEquals(entity.getHouseNumber(), result.getHouseNumber());
        assertEquals(entity.getHouseBlock(), result.getHouseBlock());
        assertEquals(entity.getFlatNumber(), result.getFlatNumber());
        assertEquals(entity.getIndex(), result.getIndex());
    }

    @Test
    public void testRegistrationDtoToRegistrationEntity1() {
        RegistrationDto dto = new RegistrationDto();
        dto.setId(1L);
        dto.setCountry("Country");
        dto.setRegion("Region");
        dto.setCity("City");
        dto.setDistrict("District");
        dto.setLocality("Locality");
        dto.setStreet("Street");
        dto.setHouseNumber("123");
        dto.setHouseBlock("A");
        dto.setFlatNumber("456");
        dto.setIndex(789L);

        RegistrationEntity target = new RegistrationEntity();
        mapperImpl.registrationDtoToRegistrationEntity1(dto, target);

        assertEquals(dto.getId(), target.getId());
        assertEquals(dto.getCountry(), target.getCountry());
        assertEquals(dto.getRegion(), target.getRegion());
        assertEquals(dto.getCity(), target.getCity());
        assertEquals(dto.getDistrict(), target.getDistrict());
        assertEquals(dto.getLocality(), target.getLocality());
        assertEquals(dto.getStreet(), target.getStreet());
        assertEquals(dto.getHouseNumber(), target.getHouseNumber());
        assertEquals(dto.getHouseBlock(), target.getHouseBlock());
        assertEquals(dto.getFlatNumber(), target.getFlatNumber());
        assertEquals(dto.getIndex(), target.getIndex());
    }

    @Test
    public void testActualRegistrationDtoToActualRegistrationEntity1() {
        ActualRegistrationDto dto = new ActualRegistrationDto();
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

        ActualRegistrationEntity target = new ActualRegistrationEntity();
        mapperImpl.actualRegistrationDtoToActualRegistrationEntity1(dto, target);

        assertEquals(dto.getId(), target.getId());
        assertEquals(dto.getCountry(), target.getCountry());
        assertEquals(dto.getRegion(), target.getRegion());
        assertEquals(dto.getCity(), target.getCity());
        assertEquals(dto.getDistrict(), target.getDistrict());
        assertEquals(dto.getLocality(), target.getLocality());
        assertEquals(dto.getStreet(), target.getStreet());
        assertEquals(dto.getHouseNumber(), target.getHouseNumber());
        assertEquals(dto.getHouseBlock(), target.getHouseBlock());
        assertEquals(dto.getFlatNumber(), target.getFlatNumber());
        assertEquals(dto.getIndex(), target.getIndex());
    }
}