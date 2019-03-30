package com.repo51.deploy.parser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public class JsonArrayParser implements BaseParser<JSONArray> {

    @Override
    public JSONArray parse(InputStream inputStream) {

        StringBuilder responseStrBuilder = new StringBuilder();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

        String inputStr;
        try {
            while ((inputStr = bufferedReader.readLine()) != null){
                responseStrBuilder.append(inputStr);}
            inputStream.close();
            return   new JSONArray(responseStrBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
