package com.ali.yanmo.study.job;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JobManager {

    private ThreadPoolExecutor threadPoolExecutor;
    private BlockingQueue<Runnable> jobQueue;

    public void init() {
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new ThreadPoolExecutor(JobConstants.corePoolSize, JobConstants.maximumPoolSize,
                    JobConstants.keepAliveTime, TimeUnit.MILLISECONDS, jobQueue);
        }
    }

    public void pullJobs() {
        
    }

    public void start() {
        while(true) {
        }
    }
}
