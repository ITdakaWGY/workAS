package com.as.upload.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

public class FileTest {
    @Value("${basePath}")
    private String basePath;

    @Test
    public void del(){

        FileUnloadUtil.getInstance().delFile("E:\\imgs\\JmImgs\\1583216359266.png");
    }
}
