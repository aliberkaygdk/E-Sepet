package com.aliberkaygedikoglu.bitirmeproje.retrofit;

public class ApiUtils {
    public static final String BASE_URL = "http://kasimadalan.pe.hu/";


    public static YemekDao getYemeklerDao(){
        return RetrofitClient.getClient(BASE_URL).create(YemekDao.class);
    }
}
