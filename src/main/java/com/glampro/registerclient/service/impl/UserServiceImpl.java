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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected AddressRepository addressRepository;

    @Override
    public void createUser(UserRequestDTO userRequestDTO) throws ExceptionHandler {
        try{
            //criando usuário
            User user = new User();
            validateEmail(userRequestDTO.getEmail());
            validateCpf(userRequestDTO.getCpf());
            user.setName(userRequestDTO.getName());
            user.setEmail(userRequestDTO.getEmail());
            user.setCpf(userRequestDTO.getCpf());
            user.setPassword(userRequestDTO.getPassword());
            user.setBirthDate(transformStringData(userRequestDTO.getBirthDate()));
            user.setPhone(userRequestDTO.getPhone());
            user.setClient(userRequestDTO.isClient());
            user.setProfessional(userRequestDTO.isProfessional());
            user.setProfessional(userRequestDTO.isCompany());
            user.setDateRegistration(LocalDateTime.now());
            user.setDateUpdate(LocalDateTime.now());
            user.setActive(true);

            Address address = getAddress(userRequestDTO.getAddress());
            address.setDateRegistration(LocalDateTime.now());
            address.setUser(user);
            userRepository.save(user);
            addressRepository.save(address);

        }catch (RuntimeException e){
            throw new ExceptionHandler("Erro ao criar usuário. Tente mais tarde");
        }
    }

    @Override
    public void updateServiceSalon(UserUpdateDTO userUpdate) throws ExceptionHandler {
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
        user.setBirthDate(transformStringData(userUpdate.getBirthDate()));
        user.setPhone(userUpdate.getPhone());
        user.setClient(userUpdate.isClient());
        user.setProfessional(userUpdate.isProfessional());
        user.setProfessional(userUpdate.isCompany());

        Address address = addressRepository.findByUser(user);
        address.setStreet(userUpdate.getAddress().getStreet());
        address.setNumber(userUpdate.getAddress().getNumber());
        address.setComplement(userUpdate.getAddress().getComplement());
        address.setCity(userUpdate.getAddress().getCity());
        address.setState(userUpdate.getAddress().getState());
        address.setZipCode(userUpdate.getAddress().getZipCode());
        address.setDateUpdate(LocalDateTime.now());
        userRepository.save(user);
        addressRepository.save(address);
    }

    @Override
    public void updatePassword(UpdatePasswordUserDTO updatePasswordUserDTO) throws ExceptionHandler {
        Optional<User> optionalUser = userRepository.findById(UUID.fromString(updatePasswordUserDTO.getId()));
        if (optionalUser.isEmpty() || !optionalUser.get().isActive()) {
            throw new RuntimeException("usuário não existe.");
        }
        User user = optionalUser.get();
        user.setPassword(updatePasswordUserDTO.getPasswordNew());
        userRepository.save(user);
    }

    @Override
    public List<UserResponseDTO> listUser() throws ExceptionHandler {
        return userRepository.findAllUsersWithAddresses(null);
    }

    @Override
    public UserResponseDTO getUserId(String idUser) throws ExceptionHandler {
        List<UserResponseDTO> listUser = userRepository.findAllUsersWithAddresses(UUID.fromString(idUser));
        if (listUser.isEmpty()) {
            return null;
        }
        return listUser.get(0);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) throws ExceptionHandler {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void deleteUser(String idUser) throws ExceptionHandler {
        Optional<User> optionalUser = userRepository.findById(UUID.fromString(idUser));
        if (optionalUser.isEmpty() || !optionalUser.get().isActive()) {
            throw new RuntimeException("usuário não existe e por isso não pode ser deletado");
        }
        User user = optionalUser.get();
        user.setActive(false);
        userRepository.save(user);
    }

    private Address getAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setComplement(addressDTO.getComplement());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        address.setDateUpdate(LocalDateTime.now());
        return address;
    }

    private Date transformStringData(String data) throws ExceptionHandler {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formato.parse(data);
        } catch (ParseException e) {
            throw new ExceptionHandler("Formato de data inválido! Fomato correto = dd-MM-yyyy");
        }
    }

    private void validateEmail(String email) throws ExceptionHandler {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent() && optionalUser.get().isActive()){
            throw new ExceptionHandler("E-mail já cadastrado!");
        }
    }

    private void validateCpf(String cpf) throws ExceptionHandler {
        Optional<User> optionalUser = userRepository.findByCpf(cpf);
        if(optionalUser.isPresent() && optionalUser.get().isActive()){
            throw new ExceptionHandler("CPF já cadastrado!");
        }
    }
}
