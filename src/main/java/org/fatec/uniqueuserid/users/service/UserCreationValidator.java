package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.springframework.stereotype.Service;

@Service("userCreationValidator")
public class UserCreationValidator {
    public boolean validate(UserCreationDTO dto)  throws Exception{
        if(dto.email == null) {
            throw new Exception("Missing user.email");
        }
        if(dto.name == null) {
            throw new Exception("Missing user.name");
        }
        if(dto.phone == null) {
            throw new Exception("Missing user.phone");
        }
        if(dto.password == null) {
            throw new Exception("Missing user.password");
        }
        return true;
    }
}
