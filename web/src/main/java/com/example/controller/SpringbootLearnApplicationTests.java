package com.example.controller;

import com.example.service.AsyncTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SpringbootLearnApplicationTests {
    @Autowired
    private AsyncTaskService asyncTaskService;

    @Test
    public void contextLoads() {
    }
    @Test
    public void threadTest() {
        //AsyncTaskService ats = new AsyncTaskService();
        for (int i = 0; i < 2000; i++) {
            asyncTaskService.executeAsyncTask(i);
            //System.out.println(i);
        }
    }
}
