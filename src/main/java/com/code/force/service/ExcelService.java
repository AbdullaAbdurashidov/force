package com.code.force.service;

import com.code.force.controller.ExcelController;
import com.code.force.domain.Users;
import com.code.force.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    UserRepository userRepository;

    public ByteArrayInputStream load(String role){
        List<Users> usersList=userRepository.findByRoleQuery(role);

        ByteArrayInputStream in= ExcelController.userToExcel(usersList);
        return in;
    }
}
