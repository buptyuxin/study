package com.ali.yanmo.study.job;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 
 */
public class JobManager {

    public static Logger logger = LoggerFactory.getLogger(JobManager.class);

    private ThreadPoolExecutor threadPoolExecutor;
    private BlockingQueue<Job> jobs;

    /*
     * JobManager初始化
     */
    public void init() {
        if (jobs == null) {
            jobs = new ArrayBlockingQueue<>(JobConstants.queueLength);
        }
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new ThreadPoolExecutor(JobConstants.corePoolSize, JobConstants.maximumPoolSize,
                    JobConstants.keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(
                            JobConstants.queueLength));
        }
    }
    
    /*
     * 拉任务
     * 外部使用需要使用定时任务？？？
     */
    public void pullJobs(List<Job> jobList) {
        while(!jobList.isEmpty()) {
            Job job = jobList.get(0);
            if (!jobs.offer(job)) {
                break;
            }
            jobList.remove(0);
        }
    }

    public void start() {
        try {
            while (true) {
                Job job = jobs.take();
                threadPoolExecutor.execute(job);
            }
        } catch (InterruptedException e) {
            logger.error("failed to take job", e.toString());
        }
    }
}
