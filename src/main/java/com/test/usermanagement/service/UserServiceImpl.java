package com.test.usermanagement.service;


import com.test.usermanagement.Repository.UserRepository;
import com.test.usermanagement.Repository.dataModelRepository;
import com.test.usermanagement.Repository.webSiteMonitorRepository;
import com.test.usermanagement.entity.UserEntity;
import com.test.usermanagement.entity.dataModel;
import com.test.usermanagement.entity.websiteMonitorEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl  implements  UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    webSiteMonitorRepository webSiteMonitorRepository;

    @Autowired
    dataModelRepository dataModelRepository;


    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Override
    public UserEntity createUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public UserEntity retrieveUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserEntity updateUserById(Long userId, UserEntity updatedUser) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            BeanUtils.copyProperties(updatedUser, existingUser, "id");
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUserById(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public websiteMonitorEntity addUrl(String url) {
        websiteMonitorEntity websiteMonitorEntity = new websiteMonitorEntity();
        websiteMonitorEntity.setUrl(url);
        return webSiteMonitorRepository.save(websiteMonitorEntity);
    }


    public List<websiteMonitorEntity> getAllWebsiteStatuses() {

        List<websiteMonitorEntity> WEBSITES_TO_MONITOR = new ArrayList<>();
        WEBSITES_TO_MONITOR = webSiteMonitorRepository.findAll();

        List<websiteMonitorEntity> websiteStatuses = new ArrayList<>();

        for (websiteMonitorEntity website : WEBSITES_TO_MONITOR) {
            String status = checkWebsiteStatus(website.getUrl());

            website.setStatus(status);
            websiteStatuses.add(website);
        }

        return websiteStatuses;
    }


    private String checkWebsiteStatus(String website) {
        try {
            URL url = new URL(website);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "SUCCESS";
            } else {
                return "FAILURE";
            }
        } catch (IOException e) {
            return "FAILURE";
        }
    }


    @Override
    public List<dataModel> fetchData(String tableName, String columnName, String uniqueColumn) {


        String columnValue = uniqueColumn;
        List<dataModel> distinctValues = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (isTableExists(connection, tableName)) {
                if (isColumnExists(connection, tableName, columnName)) {
                    distinctValues = getDistinctColumnValues(connection, tableName, columnName, columnValue);
                    if (!distinctValues.isEmpty()) {
                        System.out.println("Distinct Values: " + distinctValues);
                    } else {
                        System.out.println("No matching values found.");
                    }
                } else {
                    System.out.println("Column does not exist!");
                }
            } else {
                System.out.println("Table does not exist!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        dataModelRepository.findByTableNameAndColumnNameAndUniqueColumn(tableName, columnName, uniqueColumn)


        return distinctValues;
    }

    private static boolean isTableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();
        ResultSet resultSet = metadata.getTables(null, null, tableName, null);
        return resultSet.next();
    }

    private static boolean isColumnExists(Connection connection, String tableName, String columnName) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();
        ResultSet resultSet = metadata.getColumns(null, null, tableName, columnName);
        return resultSet.next();
    }


    private static List<dataModel> getDistinctColumnValues(Connection connection, String tableName, String columnName, String columnValue) throws SQLException {


        List<dataModel> dataModelList = new ArrayList<>();
        String sql = "SELECT DISTINCT " + columnName + " FROM " + tableName + " WHERE " + columnName + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, columnValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                dataModel dataModel1 = new dataModel();
                dataModel1.setTableName(tableName);
                dataModel1.setColumnName(columnName);
                dataModel1.setUniqueColumn(resultSet.getString(columnName));
                dataModelList.add(dataModel1);
            }
        }

        return dataModelList;
    }

    @Override
    public dataModel updateData(dataModel dataModel) {

        dataModel dataModel1 = new  dataModel();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {


            List<dataModel> dataModelList = new ArrayList<>();
            String sql = "UPDATE " + dataModel.getTableName() +
                    " SET " + dataModel.getColumnName() +
                    " = ? WHERE " + dataModel.getColumnName() + " = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, dataModel.getUpdateValue());
                preparedStatement.setString(2, dataModel.getUniqueColumn());


                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                     dataModel1.setMsgout("DATA UPDATED SUCCESSFULLY");
                } else {
                    dataModel1.setMsgout("UPDATION FAILED");
                }

            }


        }catch (Exception e){
         System.out.print(e);
        }

        return dataModel1;
    }
}
