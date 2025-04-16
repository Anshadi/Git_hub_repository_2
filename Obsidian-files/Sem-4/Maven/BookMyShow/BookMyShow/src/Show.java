import java.util.ArrayList;
import java.util.List;

public class Show {
    
private ShowTime showTime;
private Movie movie;
private Theatre theatre;
private List<String> availableSeats ;



public Show(ShowTime showTime, Movie movie, Theatre theatre, List<String> availableSeats) {
    this.showTime = showTime;
    this.movie = movie;
    this.theatre = theatre;
    this.availableSeats = new ArrayList<>(this.theatre.getSeats());
}
public ShowTime getShowTime() {
    return showTime;
}
public Movie getMovie() {
    return movie;
}
public Theatre getTheatre() {
    return theatre;
}
public int getAvailableSeats() {
    return availableSeats;
}    

}
