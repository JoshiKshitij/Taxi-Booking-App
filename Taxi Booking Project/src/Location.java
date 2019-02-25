import java.util.ArrayList;
import java.util.List;

public class Location {

	char locationName;
	List<Taxi> taxies = new ArrayList<>();

	public Location(char locationNAme, List<Taxi> taxies) {
		super();
		this.locationName = locationNAme;
		this.taxies = taxies;
	}

	public Location(char locationNAme) {
		super();
		this.locationName = locationNAme;
	}

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Location [locationNAme=" + locationName + ", taxies=" + taxies + "]";
	}

}
