package common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoxuliang on 16/1/14.
 */
public class HttpUtil {

    public enum Method {

        GET("GET"), HEAD("HEAD"), POST("POST"), PUT("PUT"), DELETE("DELETE"), TRACE("TRACE"), CONNECT("CONNECT");

        public String value;

        Method(String value) {
            this.value = value;
        }
    }

    public enum Header {

        REFERER("Referer"), USER_AGENT("User-Agent");

        public String value;

        Header(String value) {
            this.value = value;
        }

    }

    /**
     * 功能描述: 组装Post请求的NameValuePair参数
     * 爬虫系统
     *
     * @param formData
     * @return
     */
    public static NameValuePair[] getFormDataValuePairs(String formData) {
        if (formData == null || formData.isEmpty()) {
            return null;
        }
        String[] dataArray = formData.split(";");
        String[] dataValue = null;
        List<NameValuePair> nameValuePairList = Lists.newArrayList();
        NameValuePair[] nameValuePairArr = null;
        for (int i = 0; i < dataArray.length; i++) {

            dataValue = dataArray[i].split(":");
            if (dataValue.length == 2) {
                nameValuePairList.add(new BasicNameValuePair(dataValue[0].trim(), dataValue[1].trim()));
            } else {
                nameValuePairList.add(new BasicNameValuePair(dataValue[0].trim(), dataArray[i].substring(dataArray[i].indexOf(":") + 1)));
            }
        }
        nameValuePairArr = nameValuePairList.toArray(new NameValuePair[nameValuePairList.size()]);
        return nameValuePairArr;
    }

    /**
     * cookie String转为Map
     *
     * @param cookie
     * @return
     */
    public static Map<String, String> cookie2Map(String cookie) {
        if (cookie == null || cookie.isEmpty()) {
            return null;
        }
        Map<String, String> cookieMap = Maps.newHashMap();
        String[] cookieArr = cookie.split(";");
        if (cookieArr == null || cookieArr.length == 0) {
            return null;
        }
        for (String cookieNode : cookieArr) {
            if (cookieNode.indexOf("=") < 0) {
                continue;
            }
            String key = cookieNode.substring(0, cookieNode.indexOf("=")).trim();
            String value = cookieNode.substring(cookieNode.indexOf("=") + 1).trim();
            cookieMap.put(key, value);
        }
        return cookieMap;
    }

    /**
     * url参数转map
     *
     * @param url
     * @return
     */
    public static Map<String, String> urlParam2Map(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        if ((url.indexOf("?") < 0) || (url.indexOf("?") >= url.length() - 2)) {
            return null;
        }
        String paramStr = url.substring(url.indexOf("?") + 1);
        Map<String, String> paramMap = Maps.newHashMap();
        String[] paramArr = paramStr.split("&");
        if (paramArr == null || paramArr.length == 0) {
            return null;
        }
        for (String paramNode : paramArr) {
            if (paramNode.indexOf("=") < 0) {
                continue;
            }
            String key = paramNode.substring(0, paramNode.indexOf("=")).trim();
            String value = paramNode.substring(paramNode.indexOf("=") + 1).trim();
            paramMap.put(key, value);
        }
        return paramMap;
    }

    /**
     * 组装url参数
     *
     * @param urlPrefix
     * @param paramMap
     * @return
     */
    public static String map2UrlParam(String urlPrefix, Map<String, String> paramMap) {
        if (urlPrefix == null || urlPrefix.isEmpty()) {
            return null;
        }
        if (paramMap == null || paramMap.isEmpty()) {
            return urlPrefix;
        }
        if ((urlPrefix.indexOf("?") < 0)) {
            urlPrefix = urlPrefix + "?";
        }
        StringBuffer url = new StringBuffer().append(urlPrefix);
        Set<String> keySet = paramMap.keySet();
        boolean isFirst = true;
        for (String key : keySet) {
            if (isFirst) {
                url.append(key).append("=").append(paramMap.get(key));
                isFirst = false;
            } else {
                url.append("&").append(key).append("=").append(paramMap.get(key));
            }
        }
        return url.toString();
    }

    /**
     * Html特殊字符转义
     *
     * @param str
     * @return
     */
    public static String encodeHtml(String str) {
        if (str == null) {
            return null;
        }
        return StringEscapeUtils.escapeHtml3(str);
    }

    /**
     * Html特殊字符解义
     *
     * @param str
     * @return
     */
    public static String decodeHtml(String str) {
        if (str == null) {
            return null;
        }
        return StringEscapeUtils.unescapeHtml3(str);
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

}
