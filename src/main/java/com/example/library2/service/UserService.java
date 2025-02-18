package com.example.library2.service;

import com.example.library2.dto.UserDTO;
import com.example.library2.entity.User;
import com.example.library2.repo.UserRepo;
import com.example.library2.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
