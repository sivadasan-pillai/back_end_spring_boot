package com.test.usermanagement.controller;

import com.test.usermanagement.bo.UserBo;
import com.test.usermanagement.entity.dataModel;
import com.test.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/column")
@CrossOrigin("*")
public class columnUpdateController {


    @Autowired
    UserService userService;



    @GetMapping("/fetchData")
    public List<dataModel> fetchData(
            @RequestParam String tableName,
            @RequestParam String columnName,
            @RequestParam String uniqueColumn
    ) {
        return userService.fetchData(tableName, columnName, uniqueColumn);
    }

    @PostMapping("/updateData")
    public dataModel updateData(@RequestBody dataModel dataModel) {
        return userService.updateData(dataModel);
    }
}
