package com.hovanly.dut.recyclerviewlikegmail;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Copyright@ AsianTech.Inc
 * Created by Ly Ho V. on 19/07/2017
 */
public class FileUtils {
    private FileUtils() {
        // No-op
    }

    public static String getJsonDataFormAsset(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
