package com.ramonaharrison.dev.dreamteamnow;

/**
 * Created by Ramona Harrison
 * on 6/21/15.
 */
public abstract class CardInfo {
    private String type;
    private int priority;

    public String getType() { return this.type; }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
