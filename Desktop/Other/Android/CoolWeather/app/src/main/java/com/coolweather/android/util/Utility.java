package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    //解析和处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject jsonObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setPrivinceName(jsonObject.getString("name"));
                    province.setProvinceCode(jsonObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //解析和处理服务器返回的市级数据
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i<allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setPrivinceId(provinceId);
                    city.save();
                }

                return true;
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    //解析处理服务器返回的县级数据
    public static boolean handleCountyReponse(String resopnse, int cityId) {
        if (!TextUtils.isEmpty(resopnse)) {
            try {
                JSONArray allCounties = new JSONArray(resopnse);
                for (int i = 0; i<allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.save();
                }
                return true;
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return false;
    }


    //将返回的json数据解析成weather实体类
    public static Weather handleWeatherResponse(String resopnse) {
        try {
            JSONObject jsonObject = new JSONObject(resopnse);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
