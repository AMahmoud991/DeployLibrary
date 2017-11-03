package com.repo51.deploy.parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public class JsonObjectParser implements BaseParser<JSONObject> {

    @Override
    public JSONObject parse(InputStream inputStream) {
        StringBuilder responseStrBuilder = new StringBuilder();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

        String inputStr;
        try {
            while ((inputStr = bufferedReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            new JSONObject(responseStrBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
