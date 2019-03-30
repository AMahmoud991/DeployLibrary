package com.repo51.deploy.parser;

import java.io.InputStream;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public interface BaseParser<T> {
     T parse(InputStream inputStream);
}
