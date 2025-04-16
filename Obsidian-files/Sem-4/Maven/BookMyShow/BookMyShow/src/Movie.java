public class Movie {
    private final String title;
    public final String genre;
    private final int duration;

    public Movie(String title, String genre, int duration) {
        this.title =title ;
        this.genre = genre;
        this.duration =duration;
    }
    public String getTitle(){
        return this.title;
    }

    public int getDuration(){
        return this.duration;
    }
    public String getGenre(){
        return this.genre;
    }

    
}
