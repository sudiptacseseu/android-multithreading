package com.sudipcseseu.multithreading.handler;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomHandler {

    private final Runnable POISON = new Runnable() {
        @Override
        public void run() {
        }
    };

    private final BlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();

    public CustomHandler() {
        initWorkerThread();
    }

    private void initWorkerThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("CustomHandler", "worker (looper) thread initialized");
                while (true) {
                    Runnable runnable;
                    try {
                        runnable = mQueue.take();
                    } catch (InterruptedException e) {
                        return;
                    }
                    if (runnable == POISON) {
                        Log.d("CustomHandler", "poison data detected; stopping working thread");
                        return;
                    }
                    runnable.run();
                }
            }
        }).start();
    }

    public void stop() {
        Log.d("CustomHandler", "injecting poison data into the queue");
        mQueue.clear();
        mQueue.add(POISON);
    }

    public void post(Runnable job) {
        mQueue.add(job);
    }
}
