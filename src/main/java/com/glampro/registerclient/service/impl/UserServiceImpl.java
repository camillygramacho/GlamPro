package com.glampro.registerclient.service.impl;

import com.glampro.registerclient.dto.*;
import com.glampro.registerclient.exception.ExceptionHandler;
import com.glampro.registerclient.model.Address;
import com.glampro.registerclient.model.User;
import com.glampro.registerclient.repository.AddressRepository;
import com.glampro.registerclient.repository.UserRepository;
import com.glampro.registerclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserRepository userRepository;

    @Override
    public void createUser(UserRequestDTO userRequestDTO) throws ExceptionHandler {
        try{
            //criando usuário
            User user = new User();
            validateEmail(userRequestDTO.getEmail());
            user.setName(userRequestDTO.getName());
            user.setEmail(userRequestDTO.getEmail());
            user.setCpf(userRequestDTO.getCpf());
            user.setPassword(userRequestDTO.getPassword());
            user.setBirthDate(transformStringData(userRequestDTO.getBirthDate()));
            user.setPhone(userRequestDTO.getPhone());
            user.setClient(userRequestDTO.isClient());
            user.setProfessional(userRequestDTO.isProfessional());
            user.setDateRegistration(LocalDateTime.now());
            user.setDateUpdate(LocalDateTime.now());

            Address address = getAddress(userRequestDTO.getAddress());
            address.setDateRegistration(LocalDateTime.now());
            address.setUser(user);
            user.setAddress(address);
            userRepository.save(user);

        }catch (RuntimeException e){
            throw new ExceptionHandler("Erro ao criar usuário. Tente mais tarde");
        }
    }

    @Override
    public void updateUser(UserUpdateDTO userUpdate) throws ExceptionHandler {
        Optional<User> optionalUser = userRepository.findById(UUID.fromString(userUpdate.getId()));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("usuário não existe e por isso não pode se atualizado");
        }
        User user = optionalUser.get();
        if(userUpdate.getEmail()!= null &&
                !userUpdate.getEmail().equalsIgnoreCase(user.getEmail())){
            validateEmail(userUpdate.getEmail());
        }
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        user.setCpf(userUpdate.getCpf());
        user.setBirthDate(transformStringData(userUpdate.getBirthDate()));
        user.setPhone(userUpdate.getPhone());
        user.setClient(userUpdate.isClient());
        user.setProfessional(userUpdate.isProfessional());

        Address address = getAddress(userUpdate.getAddress());
        address.setUser(user);
        user.setAddress(address);
        userRepository.save(user);
    }

    @Override
    public void updatePassword(UpdatePasswordUserDTO updatePasswordUserDTO) throws ExceptionHandler {
        Optional<User> optionalUser = userRepository.findById(UUID.fromString(updatePasswordUserDTO.getId()));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("usuário não existe.");
        }
        User user = optionalUser.get();
        user.setPassword(updatePasswordUserDTO.getPasswordNew());
        userRepository.save(user);
    }

    @Override
    public List<ResponseUserDTO> listUser() throws ExceptionHandler {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
             return Collections.emptyList();
        }
        // Converter usuários em DTOs
        return users.stream()
                .map(user -> new ResponseUserDTO(
                        user.getId().toString(),
                        user.getName(),
                        user.getEmail(),
                        user.getCpf(),
                        user.getPhone(),
                        user.getBirthDate().toString(),
                        null,//user.getAddress(),
                        user.isClient(),
                        user.isProfessional()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseUserDTO getUserId(String idUser) throws ExceptionHandler {
        return null;
    }

    @Override
    public ResponseUserDTO getUserByEmail(String email) throws ExceptionHandler {
        return null;
    }

    @Override
    public void deleteUser(String idUser) throws ExceptionHandler {

    }

    private Address getAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setNumber(addressDTO.getNumber());
        address.setComplement(addressDTO.getComplement());
        address.setNeighborhood(addressDTO.getNeighborhood());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setNeighborhood(addressDTO.getNeighborhood());
        address.setZipCode(addressDTO.getZipCode());
        address.setDateUpdate(LocalDateTime.now());
        return address;
    }

    private Date transformStringData(String data) throws ExceptionHandler {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formato.parse(data);
        } catch (ParseException e) {
            throw new ExceptionHandler("Formato de data inválido!");
        }
    }

    private void validateEmail(String email) throws ExceptionHandler {
        if(userRepository.findByEmail(email).isPresent()){
            throw new ExceptionHandler("E-mail já cadastrado!");
        }
    }
}
