

import java.util.List;

public class Theatre {

private final String name;
private final int SeatingCapacity;
private final String Location ;
private List<String> seats;  //Once we decided seats it wont change
}


public Theatre(String theatreName , String Location,List<String> seats){
    this.name = theatreName;
    this.Location = Location;
    this.seats = seats;
    this.SeatingCapacity = this.seats.size();
}


public String getName() {
    return name;
}


public int getSeatingCapacity() {
    return SeatingCapacity;
}


public String getLocation() {
    return Location;
}


public List<String> getSeats() {
    return seats;
}

