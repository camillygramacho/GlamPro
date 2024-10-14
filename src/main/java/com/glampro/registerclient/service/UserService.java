package com.glampro.registerclient.service;

import com.glampro.registerclient.dto.ResponseUserDTO;
import com.glampro.registerclient.dto.UpdatePasswordUserDTO;
import com.glampro.registerclient.dto.UserRequestDTO;
import com.glampro.registerclient.dto.UserUpdateDTO;
import com.glampro.registerclient.exception.ExceptionHandler;

import java.util.List;

public interface UserService {

    void createUser(UserRequestDTO userRequestDTO) throws ExceptionHandler;

    void updateUser(UserUpdateDTO userUpdate) throws ExceptionHandler;

    void updatePassword(UpdatePasswordUserDTO updatePasswordUserDTO) throws ExceptionHandler;

    List<ResponseUserDTO> listUser() throws ExceptionHandler;

    ResponseUserDTO getUserId(String idUser) throws ExceptionHandler;

    ResponseUserDTO getUserByEmail(String email) throws ExceptionHandler;

    void deleteUser(String idUser)  throws ExceptionHandler;


}
