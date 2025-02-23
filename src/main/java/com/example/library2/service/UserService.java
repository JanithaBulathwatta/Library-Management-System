package com.example.library2.service;

import com.example.library2.dto.UserDTO;
import com.example.library2.entity.User;
import com.example.library2.repo.UserRepo;
import com.example.library2.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveUser(UserDTO userDTO) {
        if(userRepo.existsById(userDTO.getUserID())){
            return VarList.RSP_DUPLICATE;
        }
        else{
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateUser(UserDTO userDTO) {
        if(userRepo.existsById(userDTO.getUserID())){
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<ArrayList<UserDTO>>() {
        }.getType());
    }

    public String deleteUser(int userID) {
        if(userRepo.existsById(userID)){
            userRepo.deleteById(userID);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
