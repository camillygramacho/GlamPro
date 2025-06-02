package com.glampro.registerclient.service;


import com.glampro.registerclient.dto.SchedulingClientResponseDTO;
import com.glampro.registerclient.dto.SchedulingPatchDTO;
import com.glampro.registerclient.dto.SchedulingRequestDTO;
import com.glampro.registerclient.dto.SchedulingResponseDTO;
import com.glampro.registerclient.model.Scheduling;

import java.util.List;

public interface SchedulingService {

    void createScheduling(String userId, List<SchedulingRequestDTO> schedulingRequestDTO);

    List<Scheduling> getListScheduling(); //mudar de void para um retorno

    List<SchedulingResponseDTO> getListSchedulingParam(String email, String nameService);

    void patchScheduling(List<SchedulingPatchDTO> lisSchedulingPatchDTO);

    List<SchedulingClientResponseDTO> getListSchedulingByClient(String email, String nameService, String emailLogin);

    void deleteScheduling();

}
