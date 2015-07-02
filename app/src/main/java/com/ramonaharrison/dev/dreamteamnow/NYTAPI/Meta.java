package com.ramonaharrison.dev.dreamteamnow.NYTAPI;

/**
 * Created by kadeemmaragh on 7/2/15.
 */
import com.google.gson.annotations.Expose;

public class Meta {

    @Expose
    private Integer hits;
    @Expose
    private Integer time;
    @Expose
    private Integer offset;

    /**
     *
     * @return
     * The hits
     */
    public Integer getHits() {
        return hits;
    }

    /**
     *
     * @param hits
     * The hits
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     *
     * @return
     * The time
     */
    public Integer getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     *
     * @return
     * The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset
     * The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
