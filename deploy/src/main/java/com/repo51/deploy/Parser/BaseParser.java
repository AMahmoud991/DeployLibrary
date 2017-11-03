package com.repo51.deploy.Parser;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public interface BaseParser<T> {

     T parse(InputStream inputStream);
}
