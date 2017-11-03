package com.repo51.deploy.Parser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.InputStream;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public class ImageParser implements BaseParser<Bitmap> {
    @Override
    public Bitmap  parse(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }
}
