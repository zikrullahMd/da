package com.webshop.webshop.utility;
import com.webshop.webshop.model.UserModel;
import org.springframework.stereotype.Service;

import static com.webshop.webshop.ApplicationConstants.users;

@Service
public class UserService {

    public UserModel getUserById(int id){
        UserModel result = null;
        for(UserModel user : users){
            if(user.getUserId() == id){
                result = user;
            }
        }


        return result;

    }

}
