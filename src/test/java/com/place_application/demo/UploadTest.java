package com.place_application.demo;

import java.io.File;

public class UploadTest {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\小安\\AppData\\Local\\Temp\\tomcat-docbase.8080.8801769884198251583\\images\\upload\\places\\t");
        System.out.println(file);
        System.out.println( file.mkdir());
    }
}
