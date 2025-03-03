package com.pichincha.infra.api.router.controller;

import com.pichincha.infra.api.router.RouterConsts;
import com.pichincha.infra.api.router.controller.dto.GenericResponseDTO;
import com.pichincha.infra.api.router.controller.dto.request.CreateMovementDto;
import com.pichincha.infra.api.router.controller.dto.request.UpdateMovementDto;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDto;
import com.pichincha.app.exception.MovementException;
import com.pichincha.infra.api.router.controller.mapper.MovementsDtoMapper;
import com.pichincha.infra.api.router.facade.MovementsFacade;
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
@RequestMapping(path = RouterConsts.CONTROLLER_MOVEMENTS_PATH)
@Tag(name = RouterConsts.API_MOVEMENTS)
public class MovementsController {

    @Autowired
    private MovementsDtoMapper movementsDtoMapper;

    @Autowired
    private MovementsFacade movementsFacade;

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_CREATE_MOVEMENT, description = RouterConsts.NOTE_API_OPERATION_CREATE_MOVEMENT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
                    content =  { @Content( schema = @Schema(implementation =  MovementDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<MovementDto> createMovement(
            @Parameter(description = API_PARAM_REQUEST_CREATE_MOVEMENT, required = true) @Validated @RequestBody(required = true) CreateMovementDto movementDto) throws MovementException {
        log.info(String.format(MSG_PROCESS_MOVEMENT, "init", "create",  movementDto.getAccountId()));

        MovementDto response = movementsFacade.createMovement(MovementsDtoMapper.toEntity(movementDto, null));

        log.info(String.format(MSG_PROCESS_MOVEMENT, "end", "create", movementDto.getAccountId()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{movement_id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_GET_MOVEMENT_BY_ID, description = RouterConsts.NOTE_API_OPERATION_GET_BY_ID_MOVEMENT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
            content =  { @Content( schema = @Schema(implementation =  MovementDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<MovementDto> getMovementById(
            @Parameter(description = API_PARAM_REQUEST_GET_MOVEMENT, required = true) @PathVariable(name = PARAM_MOVEMENT_ID ) Long movementId) throws MovementException {
        log.info(String.format(MSG_PROCESS_MOVEMENT, "init", "get",  movementId));

        MovementDto response = movementsFacade.getMovementById(movementId);

        log.info(String.format(MSG_PROCESS_MOVEMENT, "end", "get",  movementId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{movement_id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_DELETE_MOVEMENT, description = RouterConsts.NOTE_API_OPERATION_DELETE_MOVEMENT)
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
    public ResponseEntity<GenericResponseDTO> deleteMovement(
            @Parameter(description = API_PARAM_REQUEST_GET_MOVEMENT, required = true) @PathVariable(name = PARAM_MOVEMENT_ID) Long movementId) throws MovementException {
        log.info(String.format(MSG_PROCESS_MOVEMENT, "init", "delete",  movementId));

        movementsFacade.deleteMovement(movementId);

        log.info(String.format(MSG_PROCESS_MOVEMENT, "end", "delete",  movementId));
        return ResponseEntity.ok(GenericResponseDTO.builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(MSG_CONFIRMATION_DELETE).build());
    }

    @PutMapping(value = "/{movement_id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_UPDATE_MOVEMENT, description = RouterConsts.NOTE_API_OPERATION_UPDATE_MOVEMENT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
            content =  { @Content( schema = @Schema(implementation =  MovementDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<MovementDto> updateMovement(
            @Parameter(description = API_PARAM_REQUEST_GET_MOVEMENT, required = true) @PathVariable(name = PARAM_MOVEMENT_ID) Long movementId,
            @Parameter(description = API_PARAM_REQUEST_UPDATE_MOVEMENT, required = true) @Valid @RequestBody(required = true) UpdateMovementDto movementDto) throws MovementException {
        log.info(String.format(MSG_PROCESS_MOVEMENT, "init", "update",  movementId));

        MovementDto response = movementsFacade.updateMovement(MovementsDtoMapper.updateToEntity(movementDto, movementId));

        log.info(String.format(MSG_PROCESS_MOVEMENT, "init", "update",  movementId));
        return ResponseEntity.ok(response);
    }
}
