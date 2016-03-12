package com.iceoton.managesqlite.map;

import com.google.maps.model.LatLng;
import com.iceoton.managesqlite.model.Elements;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by jaturon on 3/12/2016 AD.
 */
public class GoogleMatrixRequest {
    private static final String API_KEY = "AIzaSyA4wyuqA58OJ6QUrS2oql8UivPbw40zyyQ";
    OkHttpClient client;

    public GoogleMatrixRequest() {
        client = new OkHttpClient();
    }

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public Elements getDistanceDuration(LatLng start, LatLng end){
        String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?" +
                "origins="+ start.toString() + "&destinations="+ end.toString() +
                "&mode=driving"+ "&language=en-EN"+ "&key=" + API_KEY;
        //System.out.println("Request URL = " + url_request);
        String response = "";
        try {
            response = this.run(url_request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(response);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray rows = jsonObject.getJSONArray("rows");
        JSONObject temp = rows.getJSONObject(0).getJSONArray("elements").getJSONObject(0);
        Elements elements = new Elements();
        elements.setDistance(temp.getJSONObject("distance").getInt("value"));
        elements.setDuration(temp.getJSONObject("duration").getInt("value"));

        return elements;
    }

    public static void main(String[] args) throws IOException {
        GoogleMatrixRequest request = new GoogleMatrixRequest();
        LatLng start = new LatLng(13.694032,100.493454);
        LatLng end = new LatLng(13.698549,100.49848);
        request.getDistanceDuration(start, end);
    }
}
