package com.pichincha.infra.api.router.controller;

import com.pichincha.infra.api.router.RouterConsts;
import com.pichincha.infra.api.router.controller.dto.GenericResponseDTO;
import com.pichincha.infra.api.router.controller.dto.request.CreateAccountDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountDto;
import com.pichincha.app.exception.AccountException;
import com.pichincha.infra.api.router.controller.mapper.AccountsDtoMapper;
import com.pichincha.infra.api.router.facade.AccountsFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static com.pichincha.infra.api.router.RouterConsts.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@CrossOrigin(RouterConsts.CROSS_ORIGIN)
@RestController
@RequestMapping(path = RouterConsts.CONTROLLER_ACCOUNTS_PATH)
@Tag(name = RouterConsts.API_ACCOUNTS)
public class AccountsController {

    @Autowired
    private AccountsDtoMapper accountsDtoMapper;

    @Autowired
    private AccountsFacade accountsFacade;

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_CREATE_ACCOUNT, description = RouterConsts.NOTE_API_OPERATION_CREATE_ACCOUNT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
                    content =  { @Content( schema = @Schema(implementation =  AccountDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<AccountDto> createAccount(
            @Parameter(description = API_PARAM_REQUEST_CREATE_ACCOUNT, required = true) @Validated @RequestBody(required = true) CreateAccountDto accountDto) throws AccountException {
        log.info(String.format(MSG_PROCESS_ACCOUNT, "init", "create",  accountDto.getAccountNumber()));

        AccountDto response = accountsFacade.createAccount(AccountsDtoMapper.toEntity(accountDto, null));

        log.info(String.format(MSG_PROCESS_ACCOUNT, "end", "create", accountDto.getAccountNumber()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{account_id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_GET_ACCOUNT_BY_ID, description = RouterConsts.NOTE_API_OPERATION_GET_BY_ID_ACCOUNT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
            content =  { @Content( schema = @Schema(implementation =  AccountDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<AccountDto> getAccountById(
            @Parameter(description = RouterConsts.API_PARAM_REQUEST_GET_ACCOUNT, required = true) @PathVariable(name = PARAM_ACCOUNT_ID ) Long accountId) throws AccountException {
        log.info(String.format(MSG_PROCESS_ACCOUNT, "init", "get",  accountId));

        AccountDto response = accountsFacade.getAccountById(accountId);

        log.info(String.format(MSG_PROCESS_ACCOUNT, "end", "get",  accountId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{account_id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_DELETE_ACCOUNT, description = RouterConsts.NOTE_API_OPERATION_DELETE_ACCOUNT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
            content =  { @Content( schema = @Schema(implementation =  GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<GenericResponseDTO> deleteAccount(
            @Parameter(description = RouterConsts.API_PARAM_REQUEST_GET_ACCOUNT, required = true) @PathVariable(name = PARAM_ACCOUNT_ID) Long accountId) throws AccountException {
        log.info(String.format(MSG_PROCESS_ACCOUNT, "init", "delete",  accountId));

        accountsFacade.deleteAccount(accountId);

        log.info(String.format(MSG_PROCESS_ACCOUNT, "end", "delete",  accountId));
        return ResponseEntity.ok(GenericResponseDTO.builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(MSG_CONFIRMATION_DELETE).build());
    }

    @PutMapping(value = "/{account_id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_UPDATE_ACCOUNT, description = RouterConsts.NOTE_API_OPERATION_UPDATE_ACCOUNT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
            content =  { @Content( schema = @Schema(implementation =  AccountDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<AccountDto> updateAccount(
            @Parameter(description = API_PARAM_REQUEST_GET_ACCOUNT, required = true) @PathVariable(name = PARAM_ACCOUNT_ID) Long accountId,
            @Parameter(description = RouterConsts.API_PARAM_REQUEST_UPDATE_ACCOUNT, required = true) @Valid @RequestBody(required = true) CreateAccountDto accountDto) throws AccountException {
        log.info(String.format(MSG_PROCESS_ACCOUNT, "init", "update",  accountId));

        AccountDto response = accountsFacade.updateAccount(AccountsDtoMapper.toEntity(accountDto, accountId));

        log.info(String.format(MSG_PROCESS_ACCOUNT, "init", "update",  accountId));
        return ResponseEntity.ok(response);
    }
}
