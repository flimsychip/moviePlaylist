package P2MoviePlaylist;

public class Movie {
    private String title; // title of movie
    private int duration; // duration in minutes
    private int startTime; // start time for movie

    // initialize title, duration, startTime
    Movie(String t, int d, int st) {
        this.title = t;
        this.duration = d;
        this.startTime = st;
    }

    // initialize title, duration, startTime=0
    Movie(String t, int d) {
        this.title = t;
        this.duration = d;
        this.startTime = 0;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String t) {
        this.title = t;
    }
    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int d) {
        this.duration = d;
    }
    public int getStartTime() {
        return this.startTime;
    }
    public void setStartTime(int t) {
        this.startTime = t;
    }

    public String toString() {
        return "Title: " + this.title + " | Duration: " + this.duration + " | Start time: " + this.startTime;
    }
}
