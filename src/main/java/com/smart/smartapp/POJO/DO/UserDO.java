package com.smart.smartapp.POJO.DO;

import com.alibaba.fastjson2.util.DateUtils;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhizj
 * @since 2023-02-15
 */
@Data
@TableName("t_user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public UserDO() {
    }

    public UserDO(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

}
