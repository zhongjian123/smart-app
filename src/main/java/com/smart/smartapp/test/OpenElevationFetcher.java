package com.smart.smartapp.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 使用Open Elevation API
 */
public class OpenElevationFetcher {
    private static final String ELEVATION_URL = "https://api.open-elevation.com/api/v1/lookup";

    public static void main(String[] args) {
        double latitude = 29.709239;
        double longitude = 118.317325;
        try {
            double elevation = getElevation(latitude, longitude);
            System.out.println("Elevation at (" + latitude + ", " + longitude + ") is " + elevation + " meters.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getElevation(double latitude, double longitude) throws Exception {
        String urlString = String.format("%s?locations=%f,%f", ELEVATION_URL, latitude, longitude);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();

        String jsonResponse = response.toString();
        double elevation = parseElevationFromJson(jsonResponse);
        return elevation;
    }

    private static double parseElevationFromJson(String jsonResponse) {
        int elevationIndex = jsonResponse.indexOf("\"elevation\"");
        if (elevationIndex != -1) {
            int startIndex = jsonResponse.indexOf(":", elevationIndex) + 1;
            int endIndex = jsonResponse.indexOf("}", startIndex);
            String elevationStr = jsonResponse.substring(startIndex, endIndex).trim();
            return Double.parseDouble(elevationStr);
        } else {
            throw new RuntimeException("Elevation data not found in the response.");
        }
    }
}
