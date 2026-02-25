package com.smart.smartapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
    public void testHashMap() {
        System.out.println("------");
        Map<Integer, Object> map = new HashMap<>(2);
        for (int i = 1; i < 10; i++) {
            map.put(i, "test-" + i);
            System.out.println(i);
        }
        // concurrentHashMap get方法允许多线程同时访问，但是put方法只会使用synchronized锁定单个节点，确保多线程安全；效率更高
        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("tt", 123);
        concurrentHashMap.get("tt");
        concurrentHashMap.put("tt", 2222222);
        Map<String, Object> hashtable = new Hashtable();
        // hashtable get\put使用synchronized作用于整个方法确保多线程并发安全
        hashtable.get(123);
        hashtable.put("333", 333);

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
        String pic = "http://192.168.5.176:9090/rtmp/snaps/20231016/2023101615/20231016152255_114828_2d1d3ee905122a54da7f9b18e05dc0a2.jpg";
        URL url = new URL(pic);
        String path = url.getPath();
        System.out.println(path);
    }

    @Test
    public void testFile() {
        String dir = "D:\\data\\images\\record\\ba\\20240710\\images\\waterLine\\F8956BDAB1D097B489FD1ABA169756BF";
        String fileName = "20240710142636_F8956BDAB1D097B489FD1ABA169756BF_waterLine_origin.jpg";
        File file = new File(dir);
        if (file.exists()) {
            System.out.println("dir 存在");
        }
        File file1 = new File(dir, fileName);
        if (file1.exists()) {
            System.out.println("file1 存在");
        }
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>(5);
        map.put("tet", 111);
        map.get("test");
    }

    @Test
    public void NetworkInterfaceCheck() {
        String address = "https://www.baidu.com/smart-center/api/smart/center/v1/alarm/data";
        try {
            URL url = new URL(address);
            String host = url.getHost();
            InetAddress inetAddress = InetAddress.getByName(host);
            if (inetAddress.isReachable(5000)) {
                System.out.println("可达");
            } else {
                System.out.println("不可达");
            }
            int port = url.getPort();
            try (Socket socket = new Socket(host, port)) {
            }
            System.out.println("接口可访问");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // 目录检索分析、东莞
    @Test
    public void testCatalog() throws IOException {
        String dir = "D:\\Users\\zhizj\\Desktop\\天翼.txt";
        String catalogFile = "D:\\Users\\zhizj\\Desktop\\目录设备.json";

        File file = new File(dir);
        if (file.exists()) {
            System.out.println("dir 存在");
        }
        List<String> lines = Files.readAllLines(Paths.get(dir));
        int i = 0;
        Set<String> deviceList = new HashSet<>();
        do {
            if (lines.get(i).equals(" <SumNum>4146</SumNum>")) {
                i = i + 3;
                String deviceIdStr = lines.get(i);
                String deviceId = deviceIdStr.substring(13, 33);
                deviceList.add(deviceId);
            } else {
                i++;
            }
        } while (i < lines.size());
        System.out.println("deviceList:");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> jsonArray = objectMapper.readValue(
                new File(catalogFile), new TypeReference<List<Map<String, Object>>>() {
                }
        );
        Set<String> notExists = new HashSet<>();
        Set<String> exists = new HashSet<>();
        jsonArray.forEach(e -> {
            if (deviceList.contains(e.get("deviceID"))) {
                exists.add((String) e.get("deviceID"));
            } else {
                notExists.add((String) e.get("deviceID"));
            }
        });

        List<String> list = deviceList.stream().filter(e -> !exists.contains(e)).collect(Collectors.toList());
        System.out.println("没有解析出来的设备：");
        list.forEach(e -> System.out.println(e));

    }


    @Test
    public void testCatalog2() throws IOException {
        String catalogFile = "D:\\Projects\\work\\LinkCM\\temp.json";

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> jsonArray = objectMapper.readValue(
                new File(catalogFile), new TypeReference<List<Map<String, Object>>>() {
                }
        );
        Set<String> deviceList = new HashSet<>();
        Set<String> catalogList = new HashSet<>();
        jsonArray.forEach(e -> {
            String deviceID = (String) e.get("deviceID");
            String type = deviceID.substring(10, 13);

            if (type.equals("131") || type.equals("132")) {
                deviceList.add(deviceID);
            } else {
                catalogList.add(deviceID);
            }
        });

        System.out.println("--");

    }

    @Test
    public void testCopy() throws IOException {
        Path bakPath = Paths.get("D:\\Users\\zhizj\\Desktop\\sdk\\bin-bak");
        File bakFile = bakPath.toFile();
        File[] bakFiles = bakFile.listFiles();
        Set<String> bakFileNames = Arrays.stream(bakFiles).map(e -> e.getName()).collect(Collectors.toSet());


        Path okPath = Paths.get("D:\\Users\\zhizj\\Desktop\\sdk\\bin-ok");
        File okFile = okPath.toFile();
        File[] files = okFile.listFiles();
        for (File file : files) {
            if (bakFileNames.contains(file.getName())) {
                System.out.println(file.getName());
            } else if (!file.getName().endsWith(".dll")) {
                System.out.println("not dll=== " + file.getName());
            } else if (file.getName().contains("opencv")) {
                System.out.println("opencv=== " + file.getName());
            } else {
                File dep = Paths.get(file.getParentFile().getParentFile().toString(), "dependence-mini", file.getName()).toFile();
                FileCopyUtils.copy(file, dep);
            }
        }
    }

}
