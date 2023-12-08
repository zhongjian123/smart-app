package com.smart.smartapp;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.smart.smartapp.POJO.DO.UserDO;
import com.smart.smartapp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class SmartAppApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserDO> userDOList = userMapper.selectList(null);
//        Assert.assertEquals(5, userDOList.size());
        userDOList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        UserDO userDO = new UserDO();
        userDO.setAge(10);
        userDO.setEmail("123@qq.com");
        userDO.setName("zhizhongjian");
        userMapper.insert(userDO);
        System.out.println(userDO);
    }

    @Test
    public void testCodeGenerate() {
        String url="jdbc:mysql://127.0.0.1:3306/smart-app?useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=GMT%2B8";
        FastAutoGenerator.create(url, "root", "admin123")
                .globalConfig(builder -> {
                    builder.author("zhizj") // 设置作者
                            // .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\Linkcm\\smart-app\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.smart.smartapp") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Linkcm\\smart-app\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
