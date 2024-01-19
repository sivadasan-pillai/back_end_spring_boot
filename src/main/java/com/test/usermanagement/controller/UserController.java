package com.test.usermanagement.controller;


import com.test.usermanagement.bo.UserBo;
import com.test.usermanagement.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

@Autowired
UserBo userBo;


    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity newUser) {
        UserEntity createdUser = userBo.createUser(newUser);
        createdUser.setResultMessage("Data created Successfully");
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> retrieveUserById(@PathVariable Long userId) {
        UserEntity retrievedUser = userBo.retrieveUserById(userId);
        if (retrievedUser != null) {
            retrievedUser.setResultMessage("Success");
            return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
        } else {
            UserEntity retrievedUser1 = new UserEntity();
            retrievedUser1.setResultMessage("NO DATA FOUND");

            return new ResponseEntity<>(retrievedUser1,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserEntity> updateUserById(@PathVariable Long userId, @RequestBody UserEntity updatedUser) {
        UserEntity modifiedUser = userBo.updateUserById(userId, updatedUser);
        if (modifiedUser != null) {
            modifiedUser.setResultMessage("Success");

            return new ResponseEntity<>(modifiedUser, HttpStatus.OK);
        } else {
            UserEntity modifiedUser1 = new UserEntity();
            modifiedUser1.setResultMessage("FAILURE");
            return new ResponseEntity<>(modifiedUser1,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserEntity> deleteUserById(@PathVariable Long userId) {
        boolean deleted = userBo.deleteUserById(userId);
        if (deleted) {
            UserEntity userEntity = new UserEntity();
            userEntity.setResultMessage("Data deleted Successfully");
            return new ResponseEntity<>(userEntity,HttpStatus.OK);
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setResultMessage("Invalid data");
            return new ResponseEntity<>(userEntity,HttpStatus.NOT_FOUND);
        }
    }








}
