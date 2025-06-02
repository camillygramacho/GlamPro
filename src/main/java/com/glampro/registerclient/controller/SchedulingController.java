package com.glampro.registerclient.controller;


import com.glampro.registerclient.dto.SchedulingPatchDTO;
import com.glampro.registerclient.dto.SchedulingRequestDTO;
import com.glampro.registerclient.service.impl.SchedulingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scheduling")
@CrossOrigin(origins = "http://localhost:4200")
public class SchedulingController {

    @Autowired
    protected SchedulingServiceImpl schedulingServiceImpl;


    @Operation(summary = "cadastra agendamento")
    @PostMapping()
    public ResponseEntity<?> createScheduling(@RequestBody List<SchedulingRequestDTO> listScheduling,
                                              @RequestHeader String email){
        try {
            schedulingServiceImpl.createScheduling(email, listScheduling);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Cadastro de agendamento realziado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "busca agendamento")
    @GetMapping
    public ResponseEntity<?> listScheduling(@Param("email") String email,
                                            @Param("nameService") String nameService){
        try {
            return ResponseEntity.ok(schedulingServiceImpl.getListSchedulingParam(email, nameService));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @Operation(summary = "busca os agendamentos realizados pelo cliente")
    @GetMapping("/appointmentDone")
    public ResponseEntity<?> listSchedulingByClient(@Param("emailProfessional") String emailProfessional,
                                            @Param("nameService") String nameService,
                                            @RequestHeader String emailLogin){
        try {
            return ResponseEntity.ok(schedulingServiceImpl.getListSchedulingByClient(emailProfessional, nameService, emailLogin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @Operation(summary = "update de  agendamento")
    @PatchMapping()
    public ResponseEntity<?> patchScheduling(@RequestBody List<SchedulingPatchDTO> listSchedulingPatch){
        try {
            schedulingServiceImpl.patchScheduling(listSchedulingPatch);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Agendamento(s) realizado(s) com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
