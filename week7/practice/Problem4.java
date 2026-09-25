package week7.practice;
interface Playable {
    String play();

    String pause();

    String play(int fromSecond);
}

abstract class MediaFile {
    private static int count = 1000;
    private final String fileId;

    MediaFile() {
        count++;
        fileId = "MF-" + count;
    }

    public abstract String getFormatInfo();

    public String getFileId() {
        return fileId;
    }
}

class AudioFile extends MediaFile implements Playable {
    private String title;

    AudioFile(String title) {
        this.title = title;
    }

    public String play() {
        return "Playing audio: " + title;
    }

    public String pause() {
        return "Paused audio: " + title;
    }

    public String play(int fromSecond) {
        int minute = fromSecond / 60;
        int second = fromSecond % 60;

        return "Playing audio: " + title
                + " from " + minute + ":"
                + String.format("%02d", second);
    }

    public String getFormatInfo() {
        return "Audio file, ID: " + getFileId();
    }
}

class Podcast implements Playable {
    private String title;
    private int episodeNumber;

    Podcast(String title, int episodeNumber) {
        this.title = title;
        this.episodeNumber = episodeNumber;
    }

    public String play() {
        return "Streaming episode " + episodeNumber
                + " of " + title;
    }

    public String pause() {
        return "Paused episode " + episodeNumber
                + " of " + title;
    }

    public String play(int fromSecond) {
        return "Streaming episode " + episodeNumber
                + " of " + title
                + " from " + fromSecond + " seconds";
    }
}

public class Problem4 {

    static void launchAll(Playable[] items) {
        for (Playable item : items) {
            System.out.println(item.play());
        }
    }

    public static void main(String[] args) {

        AudioFile a =
            new AudioFile("Morning Jazz");

        Podcast p =
            new Podcast("Tech Talk", 12);

        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());

        System.out.println(p.play());

        Playable ref = a;

        launchAll(new Playable[]{ref, p});
    }
}