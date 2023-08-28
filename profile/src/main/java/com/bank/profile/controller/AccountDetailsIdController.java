package com.bank.profile.controller;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsIdEntity;
import com.bank.profile.service.AccountDetailsIdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Контроллер для {@link AccountDetailsIdEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/details")
@Tag(name = "AccountDetails", description = "Контроллер для управления данными о счетах" )
public class AccountDetailsIdController {

    private final AccountDetailsIdService service;

    /**
     * @param id технический идентификатор {@link AccountDetailsIdEntity}
     * @return {@link ResponseEntity<AccountDetailsIdDto>}
     */
    @Operation(summary = "Получение информации о счете по id")
    @ApiResponse(responseCode = "200", description = "Успешное получение информации по счету", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDetailsIdDto.class))
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<AccountDetailsIdDto> read(
            @Parameter(description = "id счета", in = ParameterIn.PATH) @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * @param accountDetailsId {@link AccountDetailsIdDto}
     * @return {@link ResponseEntity<AccountDetailsIdDto>}
     */
    @Operation(summary = "Создание счета")
    @ApiResponse(responseCode = "200", description = "Успешное создание счета", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDetailsIdDto.class))
    })
    @PostMapping("/create")
    public ResponseEntity<AccountDetailsIdDto> create(
            @Parameter(description = "Данные счета", required = true) @RequestBody AccountDetailsIdDto accountDetailsId) {
        return ResponseEntity.ok(service.save(accountDetailsId));
    }

    /**
     * @param accountDetailsId {@link AccountDetailsIdDto}
     * @param id               технический идентификатор {@link AccountDetailsIdEntity}
     * @return {@link ResponseEntity<AccountDetailsIdDto>}
     */
    @Operation(summary = "Обновление счета по id")
    @ApiResponse(responseCode = "200", description = "Успешное обновление счета", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDetailsIdDto.class))
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDetailsIdDto> update(
            @Parameter(description = "id счета", in = ParameterIn.PATH) @PathVariable Long id,
            @Parameter(description = "Данные для обновления", required = true) @RequestBody AccountDetailsIdDto accountDetailsId) {
        return ResponseEntity.ok(service.update(id, accountDetailsId));
    }

    /**
     * @param ids лист технических идентификаторов {@link AccountDetailsIdEntity}
     * @return {@link ResponseEntity} с листом {@link List<AccountDetailsIdDto>}
     */
    @Operation(summary = "Получение всех счетов в виде списка по ids")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка счетов", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDetailsIdDto.class))
    })
    @GetMapping("read/all")
    public ResponseEntity<List<AccountDetailsIdDto>> readAllById(
            @Parameter(description = "Список ids счетов", required = true) @RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }
}
