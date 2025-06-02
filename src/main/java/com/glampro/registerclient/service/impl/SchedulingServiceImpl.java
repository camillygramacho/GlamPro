package com.glampro.registerclient.service.impl;

import com.glampro.registerclient.dto.SchedulingClientResponseDTO;
import com.glampro.registerclient.dto.SchedulingPatchDTO;
import com.glampro.registerclient.dto.SchedulingRequestDTO;
import com.glampro.registerclient.dto.SchedulingResponseDTO;
import com.glampro.registerclient.exception.ExceptionHandler;
import com.glampro.registerclient.model.Scheduling;
import com.glampro.registerclient.model.ServiceSalon;
import com.glampro.registerclient.model.User;
import com.glampro.registerclient.repository.SchedulingRepository;
import com.glampro.registerclient.repository.ServiceSalonRepository;
import com.glampro.registerclient.repository.UserRepository;
import com.glampro.registerclient.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createScheduling(String email, List<SchedulingRequestDTO> schedulingRequestDTO) {

        for (SchedulingRequestDTO scheduling : schedulingRequestDTO){
            Scheduling schedulingNew = new Scheduling();
            ServiceSalon userServiceSalon = validateServiceProfessional(email, scheduling.getIdServiceSalon());
            schedulingNew.setDateTimeAvailable(scheduling.getDateTimeAvailable());
            schedulingNew.setServiceSalon(userServiceSalon);
            schedulingNew.setActive(true);
            schedulingNew.setAvailable(true);
            schedulingRepository.save(schedulingNew);
        }
    }

    @Override
    public void patchScheduling(List<SchedulingPatchDTO> lisSchedulingPatchDTO){
        for (SchedulingPatchDTO scheduling : lisSchedulingPatchDTO){
            if (!scheduling.getEmailUserLogin().equalsIgnoreCase(scheduling.getEmailProfessional())){

                Optional<Scheduling> schedulingUpdate = schedulingRepository.findById(UUID.fromString(scheduling.getIdServiceSalon()));
                if (schedulingUpdate.isEmpty()) {
                    throw new RuntimeException("Serviço não encontrado.");
                }
                Scheduling update = schedulingUpdate.get();
                update.setAvailable(false);
                update.setDateTimeAvailable(LocalDateTime.now());

                Optional<User> optionalUser = userRepository.findByEmail(scheduling.getEmailUserLogin());
                if (optionalUser.isEmpty()) {
                    throw new RuntimeException("usuário não existe.");
                }
                update.setClient(optionalUser.get());
                schedulingRepository.save(update);
            }
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
    public List<SchedulingResponseDTO> getListSchedulingParam(String email, String nameService) {
        String paramEmail = email!=null && !email.isEmpty()? email: null;
        String paramNameService = nameService!=null && !nameService.isEmpty()? nameService: null;

        return schedulingRepository.listSchedulingFilter(paramEmail,paramNameService);
    }

    @Override
    public List<SchedulingClientResponseDTO> getListSchedulingByClient(String email, String nameService, String emailLogin) {
        Optional<User> optionalUser = userRepository.findByEmail(emailLogin);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("usuário não existe.");
        }
        String paramEmail = email!=null && !email.isEmpty()? email: null;
        String paramNameService = nameService!=null && !nameService.isEmpty()? nameService: null;

        User user = optionalUser.get();
        return schedulingRepository.listSchedulingClientFilter(paramEmail,paramNameService, user.getId());
    }

    @Override
    public void deleteScheduling() {

    }

    private ServiceSalon validateServiceProfessional(String email, String idServiceSalon){

        Optional<ServiceSalon> optionalServiceSalon = serviceSalonRepository.findById(UUID.fromString(idServiceSalon));
        if (optionalServiceSalon.isEmpty()) {
            throw new RuntimeException("Serviço não encontrado.");
        }

        ServiceSalon serviceSalon = optionalServiceSalon.get();
        if (!serviceSalon.getProfessional().getEmail().equalsIgnoreCase(email)){
            throw new RuntimeException("usuário não tem permissão para cadastrar agendamento para outro profissional!");
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);
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
