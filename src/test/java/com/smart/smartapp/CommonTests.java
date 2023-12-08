package com.smart.smartapp;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/8/9
 */
@SpringBootTest
public class CommonTests {

    @Test
    public void test() {
        List<List<String>> subLists = new ArrayList<>();
        List<String> insertList = Arrays.asList("1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3");
        List<String> list = new ArrayList();
        for (int i = 0; i < insertList.size(); i++) {
            String uid = insertList.get(i);
            list.add(uid);
            if (list.size() % 10 == 0) {
                subLists.add(list);
                list = new ArrayList();
            }
        }
        if (!list.isEmpty()) {
            subLists.add(list);
        }
        System.out.println("--------------sss" + subLists);
    }

    @Test
    public void testAbs() {
        int total = 199;
        double page = total / 100.0;
        int abs = (int) Math.ceil(page);
        System.out.println(abs);
    }

    @Test
    public void testStr() {
        int total = 50;
        int pageSize = 50;
        int totalPage = (total + pageSize - 1) / pageSize;
        String test = "erer,ere";
        System.out.println(totalPage);
        Map<String, Object> map = new HashMap<>();
        String ss = "dd";
        CustomMap<String, Object> customMap = new CustomMap<>();
        customMap.put("ww", "wewew");
        Object ww = customMap.get("ww");
        System.out.println(ww);
    }

    @Test
    public void testCopyFile() {
        Path source = Paths.get("D:\\15767\\Pictures\\Camera Roll\\test.jpg");
        Path target = Paths.get("D:\\15767\\Pictures\\Camera Roll\\stage\\image\\test2.jpg");
        try {
            if (!target.getParent().toFile().exists()) {
                target.getParent().toFile().mkdirs();
            }
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("ddddddddddd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTime() {
        // pic字段值：/rtmp/snaps/20231013/2023101318/20231013180031_32911_a8bfbe1127509bc22c4a81c7a641c17b.jpg
        String pic = "/rtmp/snaps/20231013/2023101318/20231013180031_32911_a8bfbe1127509bc22c4a81c7a641c17b.jpg";
        String fileName = Paths.get(pic).getFileName().toString();
        if (!fileName.contains("_")) {
            return;
        }
        String datetime = fileName.substring(0, fileName.indexOf("_"));
        System.out.println(datetime);
    }

    @Test
    public void testUrl() throws MalformedURLException {
        String pic="http://192.168.5.176:9090/rtmp/snaps/20231016/2023101615/20231016152255_114828_2d1d3ee905122a54da7f9b18e05dc0a2.jpg";
        URL url = new URL(pic);
        String path = url.getPath();
        System.out.println(path);
    }

}
