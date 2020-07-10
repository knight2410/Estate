package com.estate.service.impl;

import com.estate.dto.MapModel;
import com.estate.service.IMapService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class MapServiceImpl implements IMapService {
    @Override
    public MapModel getLatLngFromAddress(String location) {
        String lat = null;
        String lng = null;
        MapModel apiModel = new MapModel();
        try {
            String locationUrl = URLEncoder.encode(location, "UTF-8");
            String value = this.request("https://maps.googleapis.com/maps/api/geocode/json?address=" + locationUrl + "&key=AIzaSyBXpKX5_kZ1rCDH1ql7ELptErbUKumGh5A");
            JSONObject jsonObject = new JSONObject(value);
            JSONArray results = jsonObject.getJSONArray("results");
            JSONObject geoMetryObject, locations, json;
            for (int i = 0; i < results.length(); i++) {
                json = results.getJSONObject(i);
                geoMetryObject = json.getJSONObject("geometry");
                locations = geoMetryObject.getJSONObject("location");
                lat = locations.get("lat").toString();
                lng = locations.get("lng").toString();
            }
            apiModel.setLat(Double.parseDouble(lat));
            apiModel.setLng(Double.parseDouble(lng));

        } catch (Exception e1) {
            return new MapModel();
        }
        return apiModel;
    }

    private String request(String endpoint) throws Exception {
        StringBuilder sb = new StringBuilder();

        URL url = new URL(endpoint);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            //InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String inputLine = bufferedReader.readLine();
            while (inputLine != null) {
                sb.append(inputLine);
                inputLine = bufferedReader.readLine();
            }
        } finally {
            urlConnection.disconnect();
        }
        return sb.toString();

    }
}
