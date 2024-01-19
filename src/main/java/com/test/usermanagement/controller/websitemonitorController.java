package com.test.usermanagement.controller;


import com.test.usermanagement.bo.UserBo;
import com.test.usermanagement.entity.websiteMonitorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/websitemonitor")
@CrossOrigin("*")
public class websitemonitorController {


    @Autowired
    UserBo userBo;

    @PostMapping("/add")
    public ResponseEntity<websiteMonitorEntity> add(@RequestBody websiteMonitorEntity websiteMonitorEntity){
        websiteMonitorEntity websiteMonitorEntity1 = userBo.addUrl(websiteMonitorEntity.getUrl());
        websiteMonitorEntity1.setStatus("added successfully");
        return new ResponseEntity<>(websiteMonitorEntity1,HttpStatus.CREATED);
    }



    @RequestMapping(value = "/status", method = RequestMethod.GET)

    public ResponseEntity<List<websiteMonitorEntity> > getStatus() {
        List<websiteMonitorEntity> websiteMonitorEntity1 = new ArrayList<>();

        websiteMonitorEntity1 = userBo.getStatus();
        return new ResponseEntity<>(websiteMonitorEntity1, HttpStatus.CREATED);
    }


}
