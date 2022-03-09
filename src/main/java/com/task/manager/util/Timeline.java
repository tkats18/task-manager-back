package com.task.manager.util;

public enum Timeline {

    OVERDUE("OVERDUE"),
    NEAR_DUE("NEAR_DUE"),
    UPCOMING("UPCOMING");

    private String timeline;

    Timeline(String timeline) {
        this.timeline = timeline;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }
}
