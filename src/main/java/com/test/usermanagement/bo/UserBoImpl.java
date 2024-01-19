package com.test.usermanagement.bo;

import com.test.usermanagement.entity.UserEntity;
import com.test.usermanagement.entity.websiteMonitorEntity;
import com.test.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserBoImpl implements UserBo{

    @Autowired
    UserService userService;



    @Override
    public UserEntity createUser(UserEntity newUser) {
        return userService.createUser(newUser);
    }

    @Override
    public UserEntity retrieveUserById(Long userId) {
        return userService.retrieveUserById(userId);
    }

    @Override
    public UserEntity updateUserById(Long userId, UserEntity updatedUser) {
        return userService.updateUserById(userId,updatedUser);
    }

    @Override
    public boolean deleteUserById(Long userId) {
        return userService.deleteUserById(userId);
    }

    @Override
    public websiteMonitorEntity addUrl(String url) {
        return userService.addUrl(url);
    }

    @Override
    public List<websiteMonitorEntity> getStatus() {
        return userService.getAllWebsiteStatuses();
    }



}
