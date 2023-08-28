package com.bank.profile.controller;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entity.AuditEntity;
import com.bank.profile.service.AuditService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для {@link AuditEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
@Tag(name = "Audit", description = "Контроллер оаудита")
public class AuditController {

    private final AuditService service;

    /**
     * @param id технический идентификатор {@link AuditEntity}
     * @return {@link ResponseEntity<AuditDto>}
     */
    @Operation(summary = "Получение информации об аудите по id")
    @ApiResponse(responseCode = "200", description = "Успешное получение информации об аудите", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AuditDto.class))
    })
    @GetMapping("/{id}")
    public AuditDto read(
            @Parameter(description = "id для получения данных аудита", in = ParameterIn.PATH)
            @PathVariable("id") Long id) {
        return service.findById(id);
    }
}
