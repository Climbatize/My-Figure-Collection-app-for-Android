package net.myfigurecollection.api.request;

import android.net.Uri;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.json.gson.GsonFactory;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import net.myfigurecollection.api.CollectionMode;

/**
 * Created by Climbatize on 18/11/13.
 */
public class CollectionRequest extends GoogleHttpClientSpiceRequest<CollectionMode> {

    public final static String REQUEST_TYPE = "json";
    public final static String MODE = "collection";
    public final static String URL_TEMPLATE = "http://myfigurecollection.net/api.php?mode=%s&username=%s&type=%s&status=%s&page=%s&root=%s";
    private String mUsername, mPage, mStatus, mRoot;

    public CollectionRequest(String username, String page, String status, String root) {
        super(CollectionMode.class);
        this.mUsername = username;
        this.mPage = page;
        this.mStatus = status;
        this.mRoot = root;
    }

    @Override
    public CollectionMode loadDataFromNetwork() throws Exception {
        // With Uri.Builder class we can build our url is a safe manner
        Uri.Builder uriBuilder = Uri.parse(String.format(URL_TEMPLATE, MODE, mUsername, REQUEST_TYPE, mStatus, mPage, mRoot)).buildUpon();

        String url = uriBuilder.build().toString();

        HttpRequest request = getHttpRequestFactory().buildGetRequest(new GenericUrl(url));
        request.setParser(new GsonFactory().createJsonObjectParser());
        return  request.execute().parseAs(getResultType());
    }

    /**
     * This method generates a unique cache key for this request. In this case our cache key depends just on the
     * keyword.
     *
     * @return
     */
    public String createCacheKey() {
        return MODE + "." + mUsername + "." + mPage + "." + mStatus + "." + mRoot;
    }
}