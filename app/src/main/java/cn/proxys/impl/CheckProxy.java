package cn.proxys.impl;

import cn.proxys.utils.Req;
import cn.proxys.utils.TextUtils;
import org.jsoup.nodes.Document;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2022/1/14 5:46 PM
 * @author: sanbo
 */
public class CheckProxy {

    public static boolean isConnnectedIpA(String ip, int port) {
        return isConnnectedByIp("https://www.ip138.com/", ip, port);
    }

    public static boolean isConnnectedIpB(String ip, int port) {
        return isConnnectedByIp("https://www.iplocation.net/", ip, port);
    }

    public static boolean isConnnectedIpC(String ip, int port) {
        return isConnnectedByIp("https://whatismyipaddress.com/", ip, port);
    }

    public static boolean isConnnectedByIp(String url, String ip, int port) {
        return connectBase(url, ip, port, ip);
    }

    public static boolean isConnnectedByBaidu(String ip, int port) {
        return isConnnectedByText("https://www.baidu.com/", ip, port, "百度一下，你就知道");
    }

    public static boolean isConnnectedByWandoujia(String ip, int port) {
        return isConnnectedByText("https://www.wandoujia.com/", ip, port, "豌豆荚");
    }

    public static boolean isConnnectedByText(String url, String ip, int port, String text) {
        return connectBase(url, ip, port, text);
    }


    public static boolean connectBase(String url, String ip, int port, CharSequence containsChar) {
        try {
            Document doc = Req.getDocument(url, ip, port);
            String res = doc.text();
            if (!TextUtils.isEmpty(res)) {
                if (res.contains(ip)) {
                    return true;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }
}
