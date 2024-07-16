package com.smart.smartapp;

import org.geotools.geometry.jts.JTS;

import org.geotools.referencing.CRS;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

import java.math.BigDecimal;

public class CoordinateConversion {

    @Test
    public void testCoordinateConversionddd() throws Exception {
        System.setProperty("org.geotools.referencing.forceXY", "true");
        // 定义源坐标系（WGS84 经纬度坐标系）
        CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326");
        // 定义目标坐标系（Web Mercator 投影坐标系）
        CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:3857");
        // 定义经纬度坐标
        double longitude = 113.340513; // 经度
        double latitude = 23.166103;   // 纬度

        // 创建 Coordinate 对象
        Coordinate sour = new Coordinate(longitude, latitude);
        MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
        Coordinate tar = new Coordinate();
        JTS.transform(sour, tar, transform);

        BigDecimal x = new BigDecimal(tar.getX());
        double pointX = x.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();

        BigDecimal y = new BigDecimal(tar.getY());
        double pointY = y.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("x: " + pointX);
        System.out.println("y: " + pointY);
    }

}
