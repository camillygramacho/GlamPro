package com.glampro.registerclient.service;


import com.glampro.registerclient.dto.ResponseServiceSalonDTO;
import com.glampro.registerclient.dto.SchedulingClientResponseDTO;
import com.glampro.registerclient.dto.ServiceSalonRequestDTO;
import com.glampro.registerclient.dto.ServiceSalonRequestUpdateDTO;

import java.util.List;

public interface ServiceSalonService {

    void createServiceSalon(ServiceSalonRequestDTO requestHall, String userId);

    void updateServiceHall(ServiceSalonRequestUpdateDTO updateServiceHall, String userId);

    //List<ResponseServiceSalonDTO> getListServiceSalon();

    List<ResponseServiceSalonDTO> getListServiceHall(String idProfessional, String nameService);

    void deleteServiceAll();

}
