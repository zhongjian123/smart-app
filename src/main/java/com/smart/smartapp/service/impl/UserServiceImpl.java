package com.smart.smartapp.service.impl;

import com.smart.smartapp.POJO.DO.UserDO;
import com.smart.smartapp.mapper.UserMapper;
import com.smart.smartapp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhizj
 * @since 2023-02-15
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object updateUser(UserDO userDO) {
        int result = userMapper.updateById(userDO);
        return result > 0;
    }


    @Override
    public Object updateUserName(Long id, String userName) {
        UserDO userDO = userMapper.selectById(id);
        int result = userMapper.updateUserName(id, userName, userDO.getName());
        return result > 0;
    }

    @Override
    public boolean save(UserDO entity) {
        int i = userMapper.insert(entity);
        return i > 0;
    }


}
