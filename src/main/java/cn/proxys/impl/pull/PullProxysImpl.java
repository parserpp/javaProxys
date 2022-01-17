package cn.proxys.impl.pull;

import cn.proxys.utils.Threads;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2022/1/17 10:55 AM
 * @author: sanbo
 */
public class PullProxysImpl {
    public static void main(String[] args) {
        pull();
    }

    public static void pull() {
        ConfigurlUrlsWork.pull();
        Threads.waitForAsyncTask();
        System.out.println("所有任务结束");
    }
}
