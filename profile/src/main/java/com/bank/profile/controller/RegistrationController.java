package com.bank.profile.controller;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.RegistrationEntity;
import com.bank.profile.service.RegistrationService;
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
 * Контроллер для {@link RegistrationEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
@Tag(name = "Registration", description = "Контроллер для управления данными адреса регистрации")
public class RegistrationController {

    private final RegistrationService service;

    /**
     * @param id технический идентификатор {@link RegistrationEntity}
     * @return {@link ResponseEntity<RegistrationDto>}
     */
    @Operation(summary = "Получение информации об адресе регистрации по id")
    @ApiResponse(responseCode = "200", description = "Успешное получение информации об адресе регистрации", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<RegistrationDto> read(
            @Parameter(description = "id адреса регистрации", in = ParameterIn.PATH) @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * @param registration {@link RegistrationDto}
     * @return {@link ResponseEntity<RegistrationDto>}
     */
    @Operation(summary = "Создание адреса регистрации")
    @ApiResponse(responseCode = "200", description = "Успешное создание адреса регистрации", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))
    })
    @PostMapping("/create")
    public ResponseEntity<RegistrationDto> create(
            @Parameter(description = "Данные для регистрации", required = true) @RequestBody RegistrationDto registration) {
        return ResponseEntity.ok(service.save(registration));
    }

    /**
     * @param registration {@link RegistrationDto}
     * @param id           технический идентификатор {@link RegistrationEntity}
     * @return {@link ResponseEntity<RegistrationDto>}
     */
    @Operation(summary = "Обновление данных регистрации по id")
    @ApiResponse(responseCode = "200", description = "Успешное обновление данных регистрации", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<RegistrationDto> update(
            @Parameter(description = "id адреса регистрации", in = ParameterIn.PATH)
            @PathVariable Long id,
            @Parameter(description = "Данные для обновления", required = true)
            @RequestBody RegistrationDto registration) {
        return ResponseEntity.ok(service.update(id, registration));
    }

    /**
     * @param ids лист технических идентификаторов {@link RegistrationEntity}
     * @return {@link ResponseEntity} {@link List<RegistrationDto>}
     */
    @Operation(summary = "Получение всех адресов регистраций в виде списка по ids")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка адресов регистраций", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))
    })
    @GetMapping("read/all")
    public ResponseEntity<List<RegistrationDto>> readAllById(
            @Parameter(description = "Список ids адресов регистраций", required = true)
            @RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }
}
