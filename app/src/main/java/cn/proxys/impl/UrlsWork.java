package cn.proxys.impl;

import cn.proxys.utils.FileUtils;
import cn.proxys.utils.TextUtils;
import cn.proxys.utils.Threads;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2022/1/14 5:41 PM
 * @author: sanbo
 */
public class UrlsWork {

    private static List<String> urls = new ArrayList<String>();
    public static void pull() {
        // 1.parser url
        parserUrls();
        System.out.println("url size: " + urls.size());
        if (urls.size() > 0) {
            // 2.get proxy check
            getProxy();
        }

    }

    private static void getProxy() {
        try {
            for (int i = 0; i < urls.size(); i++) {
                final String url = urls.get(i);
                Threads.sumbit(new Runnable() {
                    @Override
                    public void run() {
                        getproxy(url);
                    }
                });
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void getproxy(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36")
                    .header("Accept-Encoding", "gzip, deflate, sdch").timeout(30 * 1000).get();
            Pattern p = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)\\s+(\\d+)");
            Matcher m = p.matcher(doc.text());
            String ip = null;
            int port = -1;
            if (m.find()) {
                while (m.find()) {
                    ip = m.group(1);
                    port = Integer.parseInt(m.group(2));
                    System.out.println(url + "--" + Thread.currentThread().getName() + "----" +
                            "getipA====>" + ip + ":" + port);
                }
            } else {
                p = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)\\:(\\d+)");
                m = p.matcher(doc.text());
                while (m.find()) {
                    ip = m.group(1);
                    port = Integer.parseInt(m.group(2));
                    System.out.println(url + "--" + Thread.currentThread().getName() + "----" +
                            "getipB====>" + ip + ":" + port);
                }
            }
            System.out.println("proxy: [" + ip + ":" + port + "]");
//            if (ip != null && port != -1) {
//                PublicDB.insertToDB(ip, port);
//            }
        } catch (Exception e) {
            //L.e("error", e.toString());
        }

    }

    /**
     * parser urls
     */
    private static void parserUrls() {
        urls.addAll(FileUtils.readForArray("data/Configurl.conf"));
    }
}
