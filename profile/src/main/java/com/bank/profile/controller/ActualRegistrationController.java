package com.bank.profile.controller;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistrationEntity;
import com.bank.profile.service.ActualRegistrationService;
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
 * Контроллер для {@link ActualRegistrationEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/actual/registration")
@Tag(name = "ActualRegistration", description = "Контроллер для управления данными об адресах проживания")
public class ActualRegistrationController {

    private final ActualRegistrationService service;

    /**
     * @param id технический идентификатор {@link ActualRegistrationEntity}
     * @return {@link ResponseEntity<ActualRegistrationDto>}
     */
    @Operation(summary = "Получение информации об адресе проживания по id")
    @ApiResponse(responseCode = "200", description = "Успешное получение информации об адресе проживания", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ActualRegistrationDto.class))
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<ActualRegistrationDto> read(
            @Parameter(description = "id адреса проживания", in = ParameterIn.PATH) @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * @param actualRegistration {@link ActualRegistrationDto}
     * @return {@link ResponseEntity<ActualRegistrationDto>}
     */
    @Operation(summary = "Создание адреса проживания")
    @ApiResponse(responseCode = "200", description = "Успешное создание адреса проживания", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ActualRegistrationDto.class))
    })
    @PostMapping("/create")
    public ResponseEntity<ActualRegistrationDto> create(
            @Parameter(description = "Данные адреса проживания", required = true)
            @RequestBody ActualRegistrationDto actualRegistration) {
        return ResponseEntity.ok(service.save(actualRegistration));
    }

    /**
     * @param actualRegistration {@link ActualRegistrationDto}
     * @param id                 технический идентификатор {@link ActualRegistrationEntity}
     * @return {@link ResponseEntity<ActualRegistrationDto>}
     */
    @Operation(summary = "Обновление данных адреса проживания по id")
    @ApiResponse(responseCode = "200", description = "Успешное обновление данных адреса проживания", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ActualRegistrationDto.class))
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ActualRegistrationDto> update(
            @Parameter(description = "id адреса", in = ParameterIn.PATH) @PathVariable Long id,
            @Parameter(description = "Данные для обновления", required = true)
            @RequestBody ActualRegistrationDto actualRegistration) {
        return ResponseEntity.ok(service.update(id, actualRegistration));
    }

    /**
     * @param ids лист технических идентификаторов {@link ActualRegistrationEntity}
     * @return {@link ResponseEntity} {@link List<ActualRegistrationDto>}
     */
    @Operation(summary = "Получение всех адресов проживания в виде списка по ids")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка с адресами", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ActualRegistrationDto.class))
    })
    @GetMapping("read/all")
    public ResponseEntity<List<ActualRegistrationDto>> readAllById(
            @Parameter(description = "Список ids для адресов", required = true) @RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }
}
