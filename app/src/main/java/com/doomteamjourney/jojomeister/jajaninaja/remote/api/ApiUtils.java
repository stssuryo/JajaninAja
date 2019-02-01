package com.doomteamjourney.jojomeister.jajaninaja.remote.api;

public class ApiUtils {
    public static final String BASE_URL = "http://jajaninaja.store/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}
