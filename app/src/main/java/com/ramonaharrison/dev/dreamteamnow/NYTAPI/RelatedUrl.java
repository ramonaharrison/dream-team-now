
package com.ramonaharrison.dev.dreamteamnow.NYTAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedUrl {

    @SerializedName("suggested_link_text")
    @Expose
    private String suggestedLinkText;
    @Expose
    private String url;

    /**
     *
     * @return
     * The suggestedLinkText
     */
    public String getSuggestedLinkText() {
        return suggestedLinkText;
    }

    /**
     *
     * @param suggestedLinkText
     * The suggested_link_text
     */
    public void setSuggestedLinkText(String suggestedLinkText) {
        this.suggestedLinkText = suggestedLinkText;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}