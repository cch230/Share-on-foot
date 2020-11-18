package com.example.shareonfoot;


public class Global {
    //public static final String baseURL = "http://192.168.0.3:8080/";     //로컬 주소
    public static final String baseURL = "http://118.67.128.228:8080/"; //AWS 주소

    public static double bitmapWidth = 500;

    private static Global instance = null;

    public static synchronized Global getInstance(){
        if(null == instance){
            instance = new Global();
        }
        return instance;
    }

    public static String getOriginalPath(String imgPath){
        return baseURL+imgPath;
    }

}
