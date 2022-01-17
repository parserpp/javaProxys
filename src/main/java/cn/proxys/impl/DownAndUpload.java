package cn.proxys.impl;

import cn.proxys.Main;
import cn.proxys.utils.TextUtils;
import ff.jnezha.jnt.Jnt;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2022/1/17 1:17 PM
 * @author: sanbo
 */
public class DownAndUpload {
    public static void down() {


        String s1 = "https://raw.githubusercontent.com/parserpp/ip_ports/main/proxyinfo.txt";
        String s2 = "https://cdn.jsdelivr.net/gh/parserpp/ip_ports/proxyinfo.txt";
        String body = Jnt.get(s1);
        if (TextUtils.isEmpty(body)) {
            body = Jnt.get(s2);
        }
        System.out.println(body);
        if (body.contains("\n")) {

            String[] ss = body.split("\n");
            System.out.println("包含\n---->" + ss.length);
            if (ss != null) {
                for (String ww : ss) {
                    Main.addResult(ww);
                }
            }
        }

    }

}
