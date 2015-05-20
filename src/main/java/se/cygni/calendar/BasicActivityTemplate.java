package se.cygni.calendar;

public abstract class BasicActivityTemplate implements ActivityTemplate {
    private String title;

    public BasicActivityTemplate() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
