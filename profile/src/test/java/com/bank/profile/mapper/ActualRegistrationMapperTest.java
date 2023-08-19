package com.bank.profile.mapper;


import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistrationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ActualRegistrationMapperTest {
    // TODO отрефакторить также, кака в AccountDetailsIdMapperTest
    private ActualRegistrationMapper mapper = Mappers.getMapper(ActualRegistrationMapper.class);

    private ActualRegistrationEntity entity;
    private ActualRegistrationDto dto;

    @BeforeEach
    public void setup() {
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

        dto = new ActualRegistrationDto();
        dto.setId(2L);
        dto.setCountry("Country2");
        dto.setRegion("Region2");
        dto.setCity("City2");
        dto.setDistrict("District2");
        dto.setLocality("Locality2");
        dto.setStreet("Street2");
        dto.setHouseNumber("987");
        dto.setHouseBlock("Block B");
        dto.setFlatNumber("789");
        dto.setIndex(987654L);
    }

    @Test
    public void testToEntity() {
        ActualRegistrationEntity resultEntity = mapper.toEntity(dto);

        assertEquals(dto.getCountry(), resultEntity.getCountry());
        assertEquals(dto.getRegion(), resultEntity.getRegion());
        assertEquals(dto.getCity(), resultEntity.getCity());
        assertEquals(dto.getDistrict(), resultEntity.getDistrict());
        assertEquals(dto.getLocality(), resultEntity.getLocality());
        assertEquals(dto.getStreet(), resultEntity.getStreet());
        assertEquals(dto.getHouseNumber(), resultEntity.getHouseNumber());
        assertEquals(dto.getHouseBlock(), resultEntity.getHouseBlock());
        assertEquals(dto.getFlatNumber(), resultEntity.getFlatNumber());
        assertEquals(dto.getIndex(), resultEntity.getIndex());
    }

    @Test
    public void testToDto() {
        ActualRegistrationDto resultDto = mapper.toDto(entity);

        assertEquals(entity.getId(), resultDto.getId());
        assertEquals(entity.getCountry(), resultDto.getCountry());
        assertEquals(entity.getRegion(), resultDto.getRegion());
        assertEquals(entity.getCity(), resultDto.getCity());
        assertEquals(entity.getDistrict(), resultDto.getDistrict());
        assertEquals(entity.getLocality(), resultDto.getLocality());
        assertEquals(entity.getStreet(), resultDto.getStreet());
        assertEquals(entity.getHouseNumber(), resultDto.getHouseNumber());
        assertEquals(entity.getHouseBlock(), resultDto.getHouseBlock());
        assertEquals(entity.getFlatNumber(), resultDto.getFlatNumber());
        assertEquals(entity.getIndex(), resultDto.getIndex());
    }

    @Test
    public void testMergeToEntity() {
        ActualRegistrationEntity mergedEntity = mapper.mergeToEntity(dto, entity);

        assertEquals(dto.getCountry(), mergedEntity.getCountry());
        assertEquals(dto.getRegion(), mergedEntity.getRegion());
        assertEquals(dto.getCity(), mergedEntity.getCity());
        assertEquals(dto.getDistrict(), mergedEntity.getDistrict());
        assertEquals(dto.getLocality(), mergedEntity.getLocality());
        assertEquals(dto.getStreet(), mergedEntity.getStreet());
        assertEquals(dto.getHouseNumber(), mergedEntity.getHouseNumber());
        assertEquals(dto.getHouseBlock(), mergedEntity.getHouseBlock());
        assertEquals(dto.getFlatNumber(), mergedEntity.getFlatNumber());
        assertEquals(dto.getIndex(), mergedEntity.getIndex());
    }

    @Test
    public void testToDtoList() {
        List<ActualRegistrationEntity> entityList = Collections.singletonList(entity);

        List<ActualRegistrationDto> dtoList = mapper.toDtoList(entityList);

        assertEquals(1, dtoList.size());
        ActualRegistrationDto resultDto = dtoList.get(0);

        assertEquals(entity.getId(), resultDto.getId());
        assertEquals(entity.getCountry(), resultDto.getCountry());
        assertEquals(entity.getRegion(), resultDto.getRegion());
        assertEquals(entity.getCity(), resultDto.getCity());
        assertEquals(entity.getDistrict(), resultDto.getDistrict());
        assertEquals(entity.getLocality(), resultDto.getLocality());
        assertEquals(entity.getStreet(), resultDto.getStreet());
        assertEquals(entity.getHouseNumber(), resultDto.getHouseNumber());
        assertEquals(entity.getHouseBlock(), resultDto.getHouseBlock());
        assertEquals(entity.getFlatNumber(), resultDto.getFlatNumber());
        assertEquals(entity.getIndex(), resultDto.getIndex());
    }

    @Test
    public void testToDto_NullInput() {
        entity = null;

        ActualRegistrationDto dto = mapper.toDto(entity);

        assertNull(dto);
    }

}

