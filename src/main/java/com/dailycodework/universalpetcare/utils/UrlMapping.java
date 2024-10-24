package com.dailycodework.universalpetcare.utils;

public class UrlMapping {
    public static final String API = "/api/v1";
    public static final String USERS = API + "/users";
    public static final String REGISTER = "/register";
    public static final String UPDATE = "/update/{userId}";
    public static final String DELETE_USER_BY_ID = "/delete/{userId}";
    public static final String USER_ID = "/{userId}";
}
