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

//    /**
//     * 验证过的方法。功能是测试url是否可用
//     *
//     * @param url
//     *            地址样式为http://www.baidu.com/，或者是www.baidu.com
//     * @return
//     */
//    @SuppressWarnings("unused")
//    private static boolean pingURL(String url) {
//        String s = null;
//        if (!url.contains("/")) {
//            s = realPing(url).toString();
//        } else {
//            url = url.substring(url.indexOf("/") + 2);
//            url = url.substring(0, url.indexOf("/"));
//            Domain domain = realPing(url);
//            s = domain.toString();
//        }
//        L.d("pingURL->:" + s);
//        if (TextUtils.isEmpty(s)) {
//            return false;
//        } else {
//            return true;
//        }
//    }

    public static boolean isConnnectedIpA(String ip, int port) {
        return isConnnected("https://www.ip138.com/",ip,port);
    }
    public static boolean isConnnectedIpB(String ip, int port) {
        return isConnnected("https://www.iplocation.net/",ip,port);
    }
    public static boolean isConnnectedIpC(String ip, int port) {
        return isConnnected("https://whatismyipaddress.com/",ip,port);
    }
    public static boolean isConnnected(String url,String ip, int port) {
        try {
            Document doc = Req.getDocument(url,ip,port);
            String res = doc.text();
            if (!TextUtils.isEmpty(res)) {
                if (res.contains(ip)) {
                    return true;
                }
            }
        } catch (Throwable e) {

        }
        return false;
    }


//    /**
//     * 通过测试百度判断是否联网
//     */
//    public static boolean isConnnectedByBrowserBaidu(String ip, int port) {
//        String url = "http://www.baidu.com/";
//
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        httpClient.getCredentialsProvider().setCredentials(new AuthScope(ip, port), null);
//        HttpHost proxy = new HttpHost(ip, port);
//        httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
//        // 请求超时
//        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
//        // 读取超时
//        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);
//
//        HttpGet httpGet = new HttpGet(url);
//        StringBuffer sb = new StringBuffer();
//        InputStreamReader is = null;
//        BufferedReader br = null;
//        // 执行
//        try {
//            HttpResponse response = httpClient.execute(httpGet);
//
//            HttpEntity entry = response.getEntity();
//
//            if (entry != null) {
//                is = new InputStreamReader(entry.getContent(), "UTF-8");
//                br = new BufferedReader(is);
//                String str = null;
//                while ((str = br.readLine()) != null) {
//                    sb.append(str.trim());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        if (sb.toString().contains("百度一下，你就知道")) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 测试百度是否连接
//     *
//     * @return
//     */
//    public static boolean isConnnectedByBrowserBaidu() {
//        return testPing("http://www.baidu.com/", "百度一下", "UTF-8");
//    }
//
//    /**
//     * 判断是否可以直接连接网站
//     *
//     * @param urlStr
//     *            目标网站
//     * @param checkStr
//     *            判断字符
//     * @param codedFormat
//     *            网站编码,默认"UTF-8"
//     * @return
//     */
//    private static boolean testPing(String urlStr, String checkStr, String codedFormat) {
//        if (TextUtils.isEmpty(urlStr) || TextUtils.isEmpty(checkStr)) {
//            return false;
//        }
//        if (TextUtils.isEmpty(codedFormat)) {
//            codedFormat = "UTF-8";
//        }
//        try {
//            URL url = new URL(urlStr);
//            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
//            uc.setConnectTimeout(10000);
//            uc.setReadTimeout(10000);
//            uc.connect();
//
//            StringBuilder page = new StringBuilder();
//            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), codedFormat));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                page.append(line + "\n");
//            }
//            if (page.toString().contains(checkStr)) {
//                return true;
//            }
//        } catch (Throwable e) {
//            return false;
//        }
//        return false;
//    }
}
