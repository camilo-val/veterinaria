package com.vetenrinaria.user.domain.usecase;

import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.user.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SaveUser {
    private final UserGateway userGateway;

    public Optional<User> createUser(User user){

        if (userExist(user.getUsername()))
                throw new BusinessExceptions(BusinessMessageExceptions.USER_INVALID);

        User newUser = User.createUser(user.getId(),user.getUsername(),
                user.getPassword(),user.getRole());
        return this.userGateway.save(newUser);
    }

    public Optional<User> updateUser(User user){
        Optional<User> userBd = this.userGateway.findById(user.getId());
        if (userExist(user.getUsername())){
            if (userBd.isPresent() && !userBd.get().getUsername().equals(user.getUsername())){
                throw new BusinessExceptions(BusinessMessageExceptions.USER_INVALID);
            }
        }

        User newUser = User.createUser(user.getId(),user.getUsername(),
                user.getPassword(),user.getRole());
        return this.userGateway.save(newUser);
    }

    private Boolean userExist(String username){
        return this.userGateway.findByUsername(username).isPresent();
    }

}
