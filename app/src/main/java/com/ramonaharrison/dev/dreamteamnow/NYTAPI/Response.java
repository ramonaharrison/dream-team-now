package com.ramonaharrison.dev.dreamteamnow.NYTAPI;

/**
 * Created by kadeemmaragh on 7/2/15.
 */
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @Expose
    private Meta meta;
    @Expose
    private List<Doc> docs = new ArrayList<Doc>();

    /**
     *
     * @return
     * The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     * The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     *
     * @return
     * The docs
     */
    public List<Doc> getDocs() {
        return docs;
    }

    /**
     *
     * @param docs
     * The docs
     */
    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

}