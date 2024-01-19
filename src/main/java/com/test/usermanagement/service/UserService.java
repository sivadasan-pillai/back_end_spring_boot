package com.test.usermanagement.service;

import com.test.usermanagement.entity.UserEntity;
import com.test.usermanagement.entity.dataModel;
import com.test.usermanagement.entity.websiteMonitorEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {


    public UserEntity createUser(UserEntity newUser);

    public UserEntity retrieveUserById( Long userId);

    public UserEntity updateUserById(Long userId, UserEntity updatedUser);

    public boolean deleteUserById( Long userId);

   public  websiteMonitorEntity addUrl(String url);

    public List<websiteMonitorEntity> getAllWebsiteStatuses();

   public List<dataModel> fetchData(String tableName, String columnName, String uniqueColumn);

    public dataModel updateData(dataModel dataModel);
}
