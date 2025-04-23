package com.glampro.registerclient.controller;


import com.glampro.registerclient.dto.*;
import com.glampro.registerclient.service.ServiceSalonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceSalon")
public class ServiceSalonController {

    @Autowired
    protected ServiceSalonService serviceSalonService;

    @Operation(summary = "Criar novo serviço do salão")
    @PostMapping()
    public ResponseEntity<?> createServiceSalon(@RequestBody ServiceSalonRequestDTO requestSalon,
                                                @RequestHeader String userId){
        try {
            serviceSalonService.createServiceSalon(requestSalon, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Serviço criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "atuliza serviço do salão")
    @PutMapping()
    public ResponseEntity<?> ServiceSalonRequestUpdateDTO(
            @RequestBody ServiceSalonRequestUpdateDTO request, @RequestHeader String userId){
        try {
            serviceSalonService.updateServiceHall(request, userId);
            return ResponseEntity.ok("Serviço atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "busca serviço do salão por id do profissional ou nome do serviço")
    @GetMapping
    public ResponseEntity<?> listService(@Param("idProfessional") String idProfessional,
                                         @Param("nameService") String nameService){
        try {
            return ResponseEntity.ok(serviceSalonService.getListServiceHall(idProfessional,nameService));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
