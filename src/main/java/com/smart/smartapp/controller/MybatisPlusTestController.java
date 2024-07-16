package com.smart.smartapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.smartapp.POJO.DO.UserDO;
import com.smart.smartapp.mapper.UserMapper;
import com.smart.smartapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/12/21
 */
@RestController
@RequestMapping("/mybatis/plus")
public class MybatisPlusTestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public void test() {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();

        // condition作用是当前条件是否生效
        queryWrapper.eq(true, "name", "zhi22");
        List<UserDO> list = userService.list(queryWrapper);
        System.out.println(list);

        Map<String, Object> map = new HashMap<>();
        map.put("age", 15);
        queryWrapper.allEq(map);
        List<UserDO> list1 = userService.list(queryWrapper);
        System.out.println(list1);
    }

    @GetMapping("/page")
    public void page() {
        Page<UserDO> page = new Page(20, 2);
        Page selectPage = userMapper.selectPage(page, null);

        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true, "name", "zhi22");
        queryWrapper.orderByAsc("id");
        Page<UserDO> userDOPage = userService.page(page, queryWrapper);

        System.out.println(userDOPage);

    }

    @GetMapping("/save")
    public void save() {
        UserDO userDO1 = new UserDO("t1", 11, "2222");
        UserDO userDO2 = new UserDO("t2", 11, "2222");
        UserDO userDO3 = new UserDO("t3", 11, "2222");
        Collection<UserDO> list = new ArrayList<>();
        list.add(userDO1);
        list.add(userDO2);
        list.add(userDO3);

        // batchSize 插入批次数量：分批次插入所有的数据，每次往数据库插入n条数据;batchSize=2,总共10条数据，就会分5次插入数据库，每次最多插入两条数据
        userService.saveBatch(list, 1);

        System.out.println(list);

    }

    @GetMapping("/update")
    public void update() {
        UpdateWrapper<UserDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("create_time='2023-12-18 17:29:20'");
        userService.update(updateWrapper);
        System.out.println("success");

    }


    @GetMapping("/get")
    public void get() {
        UserDO userDO = userService.getById(1);

        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true, "name", "zhi22");
//        queryWrapper.last("limit 1");
        UserDO one = userService.getOne(queryWrapper, false);

        Map<String, Object> getMap = userService.getMap(queryWrapper);

        System.out.println("success");

    }

}
