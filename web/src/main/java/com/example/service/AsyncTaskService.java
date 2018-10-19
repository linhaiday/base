package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @description
 * @create 2017-02-23 上午12:00
 * @email gxz04220427@163.com
 */
@Service
public class AsyncTaskService {
    @Async
    public void executeAsyncTask(int i) {
        System.out.println("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);
    }
}
