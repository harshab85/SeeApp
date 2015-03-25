package uofprojects.see.util;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by home on 2015-03-20.
 */
public final class ServiceUtil {

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final String LOCAL_URL_HOST = "http://100.64.183.209:8080";

    public static final String REGISTER_URL = LOCAL_URL_HOST + "/SeeService/see/register";

    public static final String CREATE_CHANNEL = LOCAL_URL_HOST + "/SeeService/see/createChannel";

    public static final String GET_CHANNELS = LOCAL_URL_HOST + "/SeeService/see/getChannels";

    public static final String SUBSCRIBE = LOCAL_URL_HOST + "/SeeService/see/subscribe";

    public static final String POLL = LOCAL_URL_HOST + "/SeeService/see/poll";

    public enum PayloadKeys{
        UserId("userId"), ChannelName("channelName"), ChannelNames("channelNames"),
        Subscriptions("subscriptions");

        private String key;

        PayloadKeys(String key){
            this.key = key;
        }

        public String getKey(){
            return this.key;
        }
    }

    public enum ResponseKeys{
        Success("success"), Error("errorMessage"),
        Channels("channels"), VideoUrl("videoUrl");

        private String key;

        ResponseKeys(String key){
            this.key = key;
        }

        public String getKey(){
            return this.key;
        }
    }


    public static HttpPost getPostRequest(String url, StringEntity entity) throws MalformedURLException, URISyntaxException {
        URL postUrl = new URL(url);
        HttpPost post = new HttpPost(postUrl.toURI());
        post.setEntity(entity);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept-Encoding", "application/json");

        return post;
    }

    public static HttpGet getRequest(String url) throws MalformedURLException, URISyntaxException {
        URL getUrl = new URL(url);
        HttpGet get = new HttpGet(getUrl.toURI());
        return get;
    }

    private static StringEntity POLL_STRING_ENTITY;
    static{
        JSONObject params = new JSONObject();
        try {
            params.put(ServiceUtil.PayloadKeys.UserId.getKey(), StorageUtil.getStringValue(ServiceUtil.PayloadKeys.UserId.getKey()));
            POLL_STRING_ENTITY = new StringEntity(params.toString(), ServiceUtil.DEFAULT_CHARSET);
        }
        catch (Exception e){
        }
    }

    public static StringEntity getPollRequestEntity(){
        return POLL_STRING_ENTITY;
    }



}