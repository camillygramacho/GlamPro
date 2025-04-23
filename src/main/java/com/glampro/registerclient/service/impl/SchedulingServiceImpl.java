package com.glampro.registerclient.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glampro.registerclient.dto.SchedulingRequestDTO;
import com.glampro.registerclient.exception.ExceptionHandler;
import com.glampro.registerclient.model.Scheduling;
import com.glampro.registerclient.model.ServiceSalon;
import com.glampro.registerclient.model.User;
import com.glampro.registerclient.repository.SchedulingRepository;
import com.glampro.registerclient.repository.ServiceSalonRepository;
import com.glampro.registerclient.repository.UserRepository;
import com.glampro.registerclient.service.SchedulingService;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ServiceSalonRepository serviceSalonRepository;

    @Autowired
    protected SchedulingRepository schedulingRepository;


    @Override
    public void createScheduling(String userId, List<SchedulingRequestDTO> schedulingRequestDTO) {

        for (SchedulingRequestDTO scheduling : schedulingRequestDTO){
            Scheduling schedulingNew = new Scheduling();
            ServiceSalon userServiceSalon = validateServiceProfessional(userId, scheduling.getIdServiceSalon());
            schedulingNew.setDateTimeAvailable(scheduling.getDateTimeAvailable());
            schedulingNew.setServiceSalon(userServiceSalon);
            schedulingNew.setActive(true);
            schedulingNew.setAvailable(true);
            schedulingRepository.save(schedulingNew);
        }
    }

    @Override
    public List<Scheduling> getListScheduling() {
        List<Scheduling> listActive =  new ArrayList<>(1);
        List<Scheduling> list = schedulingRepository.findAll();

        for (Scheduling available : list){
            if(available.isAvailable()){
                listActive.add(available);
            }
        }
        return listActive;
    }

    @Override
    public void getListSchedulingByClient() {

    }

    @Override
    public void deleteScheduling() {

    }

    private ServiceSalon validateServiceProfessional(String idUserRegister, String idServiceSalon){

        Optional<ServiceSalon> optionalServiceSalon = serviceSalonRepository.findById(UUID.fromString(idServiceSalon));
        if (optionalServiceSalon.isEmpty()) {
            throw new RuntimeException("Serviço não encontrado.");
        }

        ServiceSalon serviceSalon = optionalServiceSalon.get();
        if (!serviceSalon.getProfessional().getId().toString().equalsIgnoreCase(idUserRegister)){
            throw new RuntimeException("usuário não tem permissão para cadastrar agendamento para outro profissional!");
        }

        Optional<User> optionalUser = userRepository.findById(UUID.fromString(idUserRegister));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("usuário não existe.");
        }

        return serviceSalon;
    }

    private Date transformStringData(String data) throws ExceptionHandler {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formato.parse(data);
        } catch (ParseException e) {
            throw new ExceptionHandler("Formato de data inválido! Fomato correto = dd-MM-yyyy");
        }
    }
}
