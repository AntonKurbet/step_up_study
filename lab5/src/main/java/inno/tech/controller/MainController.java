package inno.tech.controller;

import inno.tech.dto.AccountCreateRequestDto;
import inno.tech.dto.AccountCreateResponseDataDto;
import inno.tech.dto.AccountCreateResponseDto;
import inno.tech.dto.InstanceCreateRequestDto;
import inno.tech.dto.InstanceCreateResponseDto;
import inno.tech.exceptions.ContractNumberExistException;
import inno.tech.exceptions.ProductCodeNotFountException;
import inno.tech.services.ProductInstanceService;
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
public class MainController {

    private final ProductRegisterService productRegisterService;
    private final ProductInstanceService productInstanceService;

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
    ResponseEntity<AccountCreateResponseDto> createRegister(@RequestBody @Valid AccountCreateRequestDto request) {
        var result = productRegisterService.create(request);
        if (result == null)
            return ResponseEntity.badRequest().body(new AccountCreateResponseDto(new AccountCreateResponseDataDto()));
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "corporate-settlement-instance/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Создание экземпляра продукта",
            description = "Метод создание экземпляра продукта",
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
    ResponseEntity<InstanceCreateResponseDto> createInstance(@RequestBody @Valid InstanceCreateRequestDto request)
            throws ContractNumberExistException, ProductCodeNotFountException {
            var result = productInstanceService.create(request);
            return ResponseEntity.ok(result);
    }
}
