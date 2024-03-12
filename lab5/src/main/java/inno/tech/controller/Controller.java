package inno.tech.controller;

import inno.tech.dto.AccountCreateRequestDto;
import inno.tech.dto.AccountCreateResponseDataDto;
import inno.tech.dto.AccountCreateResponseDto;
import inno.tech.services.ProductRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {

    private final ProductRegisterService productRegisterService;

    @PostMapping(value = "/corporate-settlement-account/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Создание продуктового регистра",
            description = "Метод создания продуктового регистра",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AccountCreateResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Server error")}
    )
    ResponseEntity<AccountCreateResponseDto> create(@RequestBody @Valid AccountCreateRequestDto request) {
        var result = productRegisterService.create(request);
        if (result == null)
            return ResponseEntity.badRequest().body(new AccountCreateResponseDto(new AccountCreateResponseDataDto()));
        return ResponseEntity.ok(result);
    }
}
