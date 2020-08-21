package cn.imust.web.controller.task;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("myTaskJob")
public class MyTaskJob {
    public void run(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
    }
}

