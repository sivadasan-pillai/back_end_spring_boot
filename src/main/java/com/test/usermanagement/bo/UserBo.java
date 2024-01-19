package com.test.usermanagement.bo;

import com.test.usermanagement.entity.UserEntity;
import com.test.usermanagement.entity.websiteMonitorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserBo {

    public UserEntity createUser( UserEntity newUser);

    public UserEntity retrieveUserById( Long userId);

    public UserEntity updateUserById(Long userId, UserEntity updatedUser);

    public boolean deleteUserById( Long userId);

   public websiteMonitorEntity addUrl(String url);

   public List<websiteMonitorEntity> getStatus();
}
