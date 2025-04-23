package com.glampro.registerclient.service;

import com.glampro.registerclient.dto.UserResponseDTO;
import com.glampro.registerclient.dto.UpdatePasswordUserDTO;
import com.glampro.registerclient.dto.UserRequestDTO;
import com.glampro.registerclient.dto.UserUpdateDTO;
import com.glampro.registerclient.exception.ExceptionHandler;

import java.util.List;

public interface UserService {

    void createUser(UserRequestDTO userRequestDTO) throws ExceptionHandler;

    void updateServiceSalon(UserUpdateDTO userUpdate) throws ExceptionHandler;

    void updatePassword(UpdatePasswordUserDTO updatePasswordUserDTO) throws ExceptionHandler;

    List<UserResponseDTO> listUser() throws ExceptionHandler;

    UserResponseDTO getUserId(String idUser) throws ExceptionHandler;

    UserResponseDTO getUserByEmail(String email) throws ExceptionHandler;

    void deleteUser(String idUser) throws ExceptionHandler;


}
