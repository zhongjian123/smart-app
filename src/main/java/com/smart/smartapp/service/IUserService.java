package com.smart.smartapp.service;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.smart.smartapp.POJO.DO.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhizj
 * @since 2023-02-15
 */
public interface IUserService extends IService<UserDO> {

    Object updateUser(UserDO userDO);

    Object updateUserName(Long id, String userName);

    boolean save(UserDO entity);

}
