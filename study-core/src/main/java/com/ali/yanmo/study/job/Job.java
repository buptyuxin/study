package com.ali.yanmo.study.job;

public abstract class Job implements Runnable {

    @Override
    public void run() {
        // TODO yanmo.yx 2014Äê10ÔÂ14ÈÕ Auto-generated function stub
        rawRun();
    }
    
    public abstract void rawRun();

}
