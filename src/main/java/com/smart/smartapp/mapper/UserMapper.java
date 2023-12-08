package com.smart.smartapp.mapper;

import com.smart.smartapp.POJO.DO.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 * @author zhizj
 * @since 2023-02-15
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    int updateUserName(@Param("id") Long id, @Param("latestName") String latestName, @Param("oldName") String oldName);

    UserDO getById(Long id);

    List<UserDO> listUsers();

    int countUsers();

    int deleteById(Long id);

    int insert(UserDO entity);

}
