package cn.proxys.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2022/1/14 5:29 PM
 * @author: sanbo
 */
public class Threads {
    private static ExecutorService executor = Executors.newScheduledThreadPool(5);

    /**
     * 耗时服务线程池.处理网络,缓存和文件操作
     */
    public synchronized static void sumbit(Runnable command) {

        if (executor.isShutdown()) {
            executor = Executors.newScheduledThreadPool(5);
        }
        executor.submit(command);
    }


    public static void waitForAsyncTask() {
        try {
            if (!executor.isShutdown())
                executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (Throwable ignore) {
            ignore.printStackTrace();
        }
    }
}
