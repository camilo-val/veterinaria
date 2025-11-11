package com.vetenrinaria.user.domain.usecase;

import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.user.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SaveUser {
    private final UserGateway userGateway;

    public Optional<User> createUser(User user){

        if (userExist(user.getUsername()))
                throw new BusinessExceptions(BusinessMessageExceptions.USER_INVALID);

        User newUser = User.createUser(user.getId(),user.getUsername(),
                user.getPassword(),user.getRole(), true, new Date(), null);
        return this.userGateway.save(newUser);
    }

    public Optional<User> updateUser(Long id,User user){
        Optional<User> userBd = this.userGateway.findById(id);
        Optional<User> userByUserName = this.userGateway.findByUsername(user.getUsername());
        if (userExist(user.getUsername())){
            if (userBd.isPresent() && !userByUserName.get().getId().equals(userBd.get().getId())) {
                throw new BusinessExceptions(BusinessMessageExceptions.USER_EXIST);
            }
        }
        User newUser = User.createUser(id,user.getUsername(),
                user.getPassword(),user.getRole(), true, userBd.get().getCreateAt(), new Date());
        System.out.println("USER UPDATE: " +  newUser);
        return this.userGateway.save(newUser);
    }

    private Boolean userExist(String username){
        return this.userGateway.findByUsername(username).isPresent();
    }

}
