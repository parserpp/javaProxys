package cn.proxys;

import cn.proxys.utils.FileUtils;
import cn.proxys.utils.Threads;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;
import java.util.List;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2022/1/14 5:08 PM
 * @author: sanbo
 */
public class Ts {
    public static void main(String[] args) {
        List<String> urs = FileUtils.readForArray("data/Configurl.conf");
        for (String url : urs) {
            Threads.sumbit(new Runnable() {
                @Override
                public void run() {
                    try {
                        getDocument(url);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            });

        }

//        try {
//            getDocument("https://www.kuaidaili.com/free/outha/880/?yundun=c63d3862084f03a6368f");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    public static Document getDocument(String url) throws Exception {
          /*String url="https://www.marklines.com/cn/vehicle_sales/search_country/search/?searchID=587200";
          Connection connect = Jsoup.connect(url).userAgent("")
                  .header("Cookie", "PLAY_LANG=cn; _plh=b9289d0a863a8fc9c79fb938f15372f7731d13fb; PLATFORM_SESSION=39034d07000717c664134556ad39869771aabc04-_ldi=520275&_lsh=8cf91cdbcbbb255adff5cba6061f561b642f5157&csrfToken=209f20c8473bc0518413c226f898ff79cd69c3ff-1539926671235-b853a6a63c77dd8fcc364a58&_lpt=%2Fcn%2Fvehicle_sales%2Fsearch&_lsi=1646321; _ga=GA1.2.2146952143.1539926675; _gid=GA1.2.1032787565.1539926675; _plh_notime=8cf91cdbcbbb255adff5cba6061f561b642f5157")
                 .timeout(360000000);
          Document document = connect.get();*/
        WebClient wc = new WebClient(BrowserVersion.CHROME);

        //是否使用不安全的SSL
        wc.getOptions().setUseInsecureSSL(true);
        //启用JS解释器，默认为true
        wc.getOptions().setJavaScriptEnabled(true);
        //禁用CSS
        wc.getOptions().setCssEnabled(false);
        //js运行错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnScriptError(false);
        //状态码错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //是否允许使用ActiveX
        wc.getOptions().setActiveXNative(false);
        //等待js时间
        wc.waitForBackgroundJavaScript(600 * 1000);
        //设置Ajax异步处理控制器即启用Ajax支持
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        //设置超时时间
        wc.getOptions().setTimeout(1000000);
        //不跟踪抓取
        wc.getOptions().setDoNotTrackEnabled(false);
        WebRequest request = new WebRequest(new URL(url));
        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
//        request.setAdditionalHeader("Cookie", "PLAY_LANG=cn; _plh=b9289d0a863a8fc9c79fb938f15372f7731d13fb; PLATFORM_SESSION=39034d07000717c664134556ad39869771aabc04-_ldi=520275&_lsh=8cf91cdbcbbb255adff5cba6061f561b642f5157&csrfToken=209f20c8473bc0518413c226f898ff79cd69c3ff-1539926671235-b853a6a63c77dd8fcc364a58&_lpt=%2Fcn%2Fvehicle_sales%2Fsearch&_lsi=1646321; _ga=GA1.2.2146952143.1539926675; _gid=GA1.2.1032787565.1539926675; _plh_notime=8cf91cdbcbbb255adff5cba6061f561b642f5157");
        try {
            //模拟浏览器打开一个目标网址
            HtmlPage htmlPage = wc.getPage(request);
            //为了获取js执行的数据 线程开始沉睡等待
//            Thread.sleep(1000);//这个线程的等待 因为js加载需要时间的
//            webClient.waitForBackgroundJavaScript() or
//            webClient.waitForBackgroundJavaScriptStartingBefore()
            wc.waitForBackgroundJavaScript(50000);
            wc.waitForBackgroundJavaScriptStartingBefore(50000);

            //以xml形式获取响应文本
            String xml = htmlPage.asXml();

            System.out.println("=================================================");
            System.out.println(url + "----->" + htmlPage.getWebResponse().getStatusCode());
//            System.out.println(xml);
            if (htmlPage.getWebResponse().getStatusCode() == 200) {
                FileUtils.saveTextToFile("data/alive_url.conf", url, true);
            }
            //并转为Document对象return
            return Jsoup.parse(xml);
            //System.out.println(xml.contains("结果.xls"));//false
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}