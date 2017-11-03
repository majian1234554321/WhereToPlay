package com.fanc.wheretoplay.rx.gsonconverter;

/**
 * Created by DELL on 2016/6/12.
 */

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {

        StringReader sr = new StringReader(value.string());//

        JsonReader jsonReader = gson.newJsonReader(sr);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}