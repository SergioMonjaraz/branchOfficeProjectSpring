package com.example.demo.controllers.apidocs;

import com.example.demo.DTO.BranchOfficeDTO;
import com.example.demo.models.BranchOffice;
import com.example.demo.responseHandler.CustomRestResponse;
import com.example.demo.responseHandler.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Tag(name="Branch office", description = "Endpoint for Branch Office Operation")
public interface BranchOfficeDoc {

    @Operation(
            summary = "Get branch office list",
            description = "This service allows to obtain a branch office list",
            ignoreJsonView = true,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = BranchOffice.class
                                    )
                            )
                    )
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView(Views.BranchOfficeView.class)
    ResponseEntity<CustomRestResponse> index();

    @Operation(
            summary = "Get a branch office",
            description = "This service allows to obtain a branch office by id",
            ignoreJsonView = true,
            parameters = @Parameter(
                    in = ParameterIn.PATH,
                    required = true,
                    name = "id",
                    description = "id from office",
                    schema = @Schema(
                            type = "string",
                            format = "uuid",
                            description = "the generated UUID"
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = CustomRestResponse.class
                                    )
                            )
                    )
            }
    )
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView(Views.BranchOfficeView.class)
    ResponseEntity<CustomRestResponse> show(@PathVariable(value = "id") UUID id);

    @Operation(
            summary = "Get branch office near of a point",
            description = "This service allows to obtain a branch office based in longitude and latitude",
            ignoreJsonView = true,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = CustomRestResponse.class
                                    )
                            )
                    )
            }
    )
    @GetMapping(path = "get-by-point", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView(Views.BranchOfficeView.class)
    ResponseEntity<CustomRestResponse> getByPoint(@RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude);

    @Operation(
            summary = "Save a new branch office",
            description = "This service save a new branch office",
            ignoreJsonView = true,
            requestBody = @RequestBody(
                    required = true,
                    description = "Necessary data for register new applicant record",
                    content = @Content(
                            schema = @Schema(
                                    implementation = BranchOfficeDTO.class
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = CustomRestResponse.class
                                    )
                            )
                    )
            }
    )
    @PostMapping( path = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView(Views.BranchOfficeView.class)
    ResponseEntity<CustomRestResponse> create(@Valid @org.springframework.web.bind.annotation.RequestBody BranchOfficeDTO branchOfficeDTO,
                                                            BindingResult result);



}
