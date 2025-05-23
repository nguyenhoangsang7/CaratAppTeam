package com.example.caratexpense.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StockApiClient {
    private static final String BASE_URL = "https://financialmodelingprep.com/api/";  // Base URL của API
    private static Retrofit retrofit = null;
//Đây là lớp giúp tạo đối tượng Retrofit đã được cấu hình sẵn.
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())  // Converter để chuyển đổi JSON thành đối tượng Java
                    .build();
        }
        return retrofit;
    }
}
