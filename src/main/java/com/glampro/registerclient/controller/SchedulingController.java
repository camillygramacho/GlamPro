package com.glampro.registerclient.controller;


import com.glampro.registerclient.dto.SchedulingRequestDTO;
import com.glampro.registerclient.service.impl.SchedulingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @Autowired
    protected SchedulingServiceImpl schedulingServiceImpl;

    @Operation(summary = "cadastra agendamento")
    @PostMapping("/create_available")
    public ResponseEntity<?> createScheduling(@RequestBody List<SchedulingRequestDTO> listScheduling,
                                              @RequestHeader String userId){
        try {
            schedulingServiceImpl.createScheduling(userId, listScheduling);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro de agendamento disponível, cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "busca agendamento")
    @GetMapping
    public ResponseEntity<?> listScheduling(){
        try {
            return ResponseEntity.ok(schedulingServiceImpl.getListScheduling());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
