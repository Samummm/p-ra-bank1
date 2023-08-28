package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.ProfileEntity;
import com.bank.profile.service.ProfileService;
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
 * Контроллер для {@link ProfileEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@Tag(name = "Profile", description = "Контроллер для управления данными профилей")
public class ProfileController {

    private final ProfileService service;

    /**
     * @param id технический идентификатор {@link ProfileEntity}
     * @return {@link ResponseEntity<ProfileDto>}
     */
    @Operation(summary = "Получение информации о профиле по id")
    @ApiResponse(responseCode = "200", description = "Успешное получение информации о профиле", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileDto.class))
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<ProfileDto> read(
            @Parameter(description = "id профиля", in = ParameterIn.PATH) @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * @param profile {@link ProfileDto}
     * @return {@link ResponseEntity<ProfileDto>}
     */
    @Operation(summary = "Создание профиля")
    @ApiResponse(responseCode = "200", description = "Успешное создание профиля", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileDto.class))
    })
    @PostMapping("/create")
    public ResponseEntity<ProfileDto> create(
            @Parameter(description = "Данные профиля", required = true) @RequestBody ProfileDto profile) {
        return ResponseEntity.ok(service.save(profile));
    }

    /**
     * @param profile {@link ProfileDto}
     * @param id      технический идентификатор {@link ProfileEntity}
     * @return {@link ResponseEntity<ProfileDto>}
     */
    @Operation(summary = "Обновление профиля по id")
    @ApiResponse(responseCode = "200", description = "Успешное обновление профиля", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileDto.class))
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileDto> update(
            @Parameter(description = "id профиля", in = ParameterIn.PATH) @PathVariable Long id,
            @Parameter(description = "Данные для обновления", required = true) @RequestBody ProfileDto profile) {
        return ResponseEntity.ok(service.update(id, profile));
    }

    /**
     * @param ids лист технических идентификаторов {@link ProfileEntity}
     * @return {@link ResponseEntity} {@link List<ProfileDto>}
     */
    @Operation(summary = "Получение всех профилей в виде списка по ids")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка профилей", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileDto.class))
    })
    @GetMapping("read/all")
    public ResponseEntity<List<ProfileDto>> readAllById(
            @Parameter(description = "Список ids профилей", required = true) @RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }
}
