package mapper;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsIdEntity;
import com.bank.profile.mapper.AccountDetailsIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestAccountDetailsIdMapper {

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
    public void testToDto() {
        AccountDetailsIdDto dto = mapper.toDto(entity);

        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getAccountId());
    }

    @Test
    public void testToEntity() {
        AccountDetailsIdEntity entity = mapper.toEntity(dto);

        assertEquals(2L, entity.getAccountId());
    }

    @Test
    public void testMergeToEntity() {
        AccountDetailsIdEntity mergedEntity = mapper.mergeToEntity(dto, entity);

        assertEquals(2L, mergedEntity.getAccountId());
    }

    @Test
    public void testToDtoList() {
        List<AccountDetailsIdEntity> entityList = Collections.singletonList(entity);

        List<AccountDetailsIdDto> dtoList = mapper.toDtoList(entityList);

        assertEquals(1, dtoList.size());
        assertEquals(1L, dtoList.get(0).getId());
        assertEquals(1L, dtoList.get(0).getAccountId());
    }

    @Test
    public void testToDto_NullInput() {
        entity = null;

        AccountDetailsIdDto dto = mapper.toDto(entity);

        assertNull(dto);
    }
}
