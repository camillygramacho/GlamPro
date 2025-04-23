package com.glampro.registerclient.service.impl;

import com.glampro.registerclient.dto.ResponseServiceSalonDTO;
import com.glampro.registerclient.dto.ServiceSalonRequestDTO;
import com.glampro.registerclient.dto.ServiceSalonRequestUpdateDTO;
import com.glampro.registerclient.model.ServiceSalon;
import com.glampro.registerclient.model.User;
import com.glampro.registerclient.repository.ServiceSalonRepository;
import com.glampro.registerclient.repository.UserRepository;
import com.glampro.registerclient.service.ServiceSalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ServiceSalonServiceImpl implements ServiceSalonService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ServiceSalonRepository serviceSalonRepository;


    @Override
    public void createServiceSalon(ServiceSalonRequestDTO requestHall, String userId) {
        User user = validateUser(userId, requestHall.getIdProfessional());
        ServiceSalon serviceSalon = new ServiceSalon();
        serviceSalon.setNameService(requestHall.getNameService());
        serviceSalon.setProfessional(user);
        serviceSalon.setValueService(requestHall.getValueService());
        serviceSalon.setActive(true);
        serviceSalon.setDateUpdate(LocalDateTime.now());
        serviceSalonRepository.save(serviceSalon);
    }


    @Override
    public void updateServiceHall(ServiceSalonRequestUpdateDTO updateServiceHall, String userId) {
        Optional<ServiceSalon> optionalUser = serviceSalonRepository.findById(UUID.fromString(updateServiceHall.getId()));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Serviço para o salão não existe.");
        }
        ServiceSalon serviceSalon = optionalUser.get();
        validateUser(userId, serviceSalon.getProfessional().getId().toString());
        serviceSalon.setNameService(updateServiceHall.getNameService());
        serviceSalon.setValueService(updateServiceHall.getValueService());
        serviceSalon.setActive(updateServiceHall.isActive());
        serviceSalon.setDateUpdate(LocalDateTime.now());
        serviceSalonRepository.save(serviceSalon);
    }

    @Override
    public List<ResponseServiceSalonDTO> getListServiceHall(String idProfessional, String nameService) {
        List<ServiceSalon> listServiceSalon;

        if(idProfessional != null) {
            Optional<User> optionalUser = userRepository.findById(UUID.fromString(idProfessional));
            if (optionalUser.isEmpty()) {
                throw new RuntimeException("usuário não existe e por isso não pode se atualizado");
            }
            listServiceSalon = serviceSalonRepository.findByProfessional(optionalUser.get());
        }
        else if (nameService!=null){
            listServiceSalon = serviceSalonRepository.findByNameServiceContainingIgnoreCase(nameService);
        }else{
            listServiceSalon = serviceSalonRepository.findAll();
        }

        if (listServiceSalon.isEmpty()) {
            throw new RuntimeException("usuário não existe e por isso não pode se atualizado");
        }

        List<ResponseServiceSalonDTO> listResponse = new ArrayList<>();
        listServiceSalon.forEach(serviceSalon -> {
            ResponseServiceSalonDTO response = new ResponseServiceSalonDTO();
            response.setId(serviceSalon.getId().toString());
            response.setNameService(serviceSalon.getNameService());
            response.setValueService(serviceSalon.getValueService());
            response.setIdProfessional(serviceSalon.getProfessional().getId().toString());
            listResponse.add(response);
        });
        return listResponse;
    }

    @Override
    public void deleteServiceAll() {

    }

    private User validateUser(String idUserRegister, String idUserCreate){

        if(!idUserRegister.equalsIgnoreCase(idUserCreate)){
            throw new RuntimeException("usuário não tem permissão para cadastrar serviço para outro profissional!");
        }

        Optional<User> optionalUser = userRepository.findById(UUID.fromString(idUserRegister));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("usuário não existe.");
        }
        User user = optionalUser.get();
        if (!user.isProfessional()){
            throw new RuntimeException("usuário não é um profissional.");
        }
        return user;
    }
}
