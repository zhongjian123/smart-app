package com.smart.smartapp.controller;


import com.smart.smartapp.POJO.DO.UserDO;
import com.smart.smartapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 * @author zhizj
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public List<UserDO> getUserList() {
        return userService.list();
    }

    @PostMapping
    public String createUser(@RequestBody UserDO userDO) {
        userDO.setCreateTime(new Date());
        userService.save(userDO);
        return HttpStatus.OK.getReasonPhrase();
    }

    @PutMapping("/{id}")
    public Object updateUser(@PathVariable("id") Long id, @RequestBody UserDO userDO) {
        userDO.setId(id);
        return userService.updateUser(userDO);
    }

    @PutMapping("/{id}/name")
    public Object updateUser(@PathVariable("id") Long id, @RequestParam("name") String name) {
        return userService.updateUserName(id, name);
    }


}
