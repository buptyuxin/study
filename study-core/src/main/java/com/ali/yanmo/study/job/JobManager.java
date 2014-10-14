package com.ali.yanmo.study.job;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JobManager {

    private List<Job> jobs;
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
 
}