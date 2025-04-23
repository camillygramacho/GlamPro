package com.glampro.registerclient.service;


import com.glampro.registerclient.dto.SchedulingRequestDTO;
import com.glampro.registerclient.model.Scheduling;

import java.util.List;

public interface SchedulingService {

    void createScheduling(String userId, List<SchedulingRequestDTO> schedulingRequestDTO);

    List<Scheduling> getListScheduling(); //mudar de void para um retorno

    void getListSchedulingByClient(); //mudar de void para um retorno

    void deleteScheduling();

}
