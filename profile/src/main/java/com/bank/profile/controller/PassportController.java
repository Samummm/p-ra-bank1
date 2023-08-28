package com.bank.profile.controller;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.PassportEntity;
import com.bank.profile.service.PassportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
 * Контроллер для {@link PassportEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/passport")
@Tag(name = "Passport", description = "Контроллер для управления данными о паспортах")
public class PassportController {

    private final PassportService service;

    /**
     * @param id технический идентификатор {@link PassportEntity}
     * @return {@link ResponseEntity<PassportDto>}
     */
    @Operation(summary = "Получение информации о паспорте по id")
    @ApiResponse(responseCode = "200", description = "Успешное получение информации о паспорте", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PassportDto.class))
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<PassportDto> read(
            @Parameter(description = "id паспорта", in = ParameterIn.PATH) @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * @param passport {@link PassportDto}
     * @return {@link ResponseEntity<PassportDto>}
     */
    @Operation(summary = "Заведение нового паспорта")
    @ApiResponse(responseCode = "200", description = "Успешное заведение паспорта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PassportDto.class))
    })
    @PostMapping("/create")
    public ResponseEntity<PassportDto> create(
            @Parameter(description = "Данные паспорта", required = true)
            @Validated @RequestBody PassportDto passport) {
        return ResponseEntity.ok(service.save(passport));
    }

    /**
     * @param passport {@link PassportDto}
     * @param id       технический идентификатор {@link PassportEntity}
     * @return {@link ResponseEntity<PassportDto>}
     */
    @Operation(summary = "Обновление данных паспорта по id")
    @ApiResponse(responseCode = "200", description = "Успешное обновление данных паспорта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PassportDto.class))
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<PassportDto> update(
            @Parameter(description = "id паспорта", in = ParameterIn.PATH)
            @PathVariable Long id,
            @Parameter(description = "Данные для обновления", required = true)
            @Validated @RequestBody PassportDto passport) {
        return ResponseEntity.ok(service.update(id, passport));
    }

    /**
     * @param ids лист технических идентификаторов {@link PassportDto}
     * @return {@link ResponseEntity} {@link List<PassportDto>}
     */
    @Operation(summary = "Получение всех паспортов по ids в виде списка")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка паспортов", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PassportDto.class))
    })
    @GetMapping("read/all")
    public ResponseEntity<List<PassportDto>> readAllById(
            @Parameter(description = "Список ids паспортов", required = true)
            @RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }
}
