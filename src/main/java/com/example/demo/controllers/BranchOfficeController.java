package com.example.demo.controllers;


import com.example.demo.DTO.BranchOfficeDTO;
import com.example.demo.controllers.apidocs.BranchOfficeDoc;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.BranchOffice;
import com.example.demo.responseHandler.CustomRestResponse;
import com.example.demo.responseHandler.GlobalResponse;
import com.example.demo.services.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.OperationNotSupportedException;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/branchoffice")
public class BranchOfficeController implements BranchOfficeDoc {

    @Autowired
    private BranchOfficeService branchOfficeService;

    @Override
    public ResponseEntity<CustomRestResponse> index() {
        Page<BranchOffice> offices;
        offices = branchOfficeService.getAllBranchOffice();
        if (offices.isEmpty()){
            return  GlobalResponse.withMessage("offices list is empty", HttpStatus.OK);
        }

        return GlobalResponse.pageable("branch office list", offices, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomRestResponse> show(@PathVariable(value = "id") UUID id) {

        return GlobalResponse.withData("Branch Office", null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomRestResponse> getByPoint(@Valid double latitude, @Valid double longitude) {
        BranchOffice office = branchOfficeService.findByPoints(latitude, longitude).orElseThrow(() ->
                new DataNotFoundException("No existen registros coincidentes con los criterios")
        );
        return GlobalResponse.withData("Sucursal encontrada", office, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomRestResponse> create(@Valid BranchOfficeDTO branchOfficeDTO, BindingResult result) {

        if(branchOfficeService.getByName(branchOfficeDTO.getName()).isPresent()){
            return GlobalResponse.withMessage("Office al ready exist", HttpStatus.CONFLICT);
        }

        BranchOffice office = branchOfficeService.createOffice(branchOfficeDTO);
        return GlobalResponse.withData("Office created", office, HttpStatus.OK);
    }
}
