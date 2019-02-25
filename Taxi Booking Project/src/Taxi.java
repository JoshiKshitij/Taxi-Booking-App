import java.util.ArrayList;
import java.util.List;

public class Taxi {
	
	
	public String taxiName;
	public char startingPoint ;
	public int earning ;
	public int depatTime;
	public char dropPoint;
	boolean freeStatus ;

	public Taxi(String taxiName, char startingPoint, int earning) {
		super();
		this.taxiName = taxiName;
		this.startingPoint = startingPoint;
		this.earning = earning;
	}
	
	public Taxi(String taxiName) {
		this.taxiName = taxiName;
	}
	
	public Taxi(String taxiName, char startingPoint, int earning, boolean status) {
		super();
		this.freeStatus = status;
		this.taxiName = taxiName;
		this.startingPoint = startingPoint;
		this.earning = earning;
	}
	
	@Override
	public int hashCode() {
		//since taxi name are like taxi 1 and taxi 2
		// so i an getting last no and return as a hascode int value
		return new Integer(this.taxiName.split(" ")[this.taxiName.split(" ").length-1]);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass()!= this.getClass()) {
			return false;
		}
		if(this == obj) 
            return true; 
		
		String otherName = ((Taxi)obj).taxiName;
		return this.taxiName.equals(otherName);
	}
	
	public Taxi() {
		this.startingPoint = 'A';
	}

	@Override
	public String toString() {
		return "Taxi [taxiName=" + taxiName + ", startingPoint=" + startingPoint + ", earning=" + earning
				+ ", depatTime=" + depatTime + ", dropPoint=" + dropPoint + ", freeStatus=" + freeStatus + "]";
	}

	public int  calculateEarnings(char start , char end) {
		int totalDistance = Math.abs(start - end )*15;	
		// for first 5 km cahge is 100 and 10 for next km
		int First5kmCharge = 100;
		int nextDistaceCharge = (totalDistance - 5)*10 ;
		
		this.earning = this.earning + First5kmCharge + nextDistaceCharge;
		return First5kmCharge + nextDistaceCharge;
	}
	
	
	
	public static void main(String[] args) {
		List<Taxi> taxis = new ArrayList<Taxi>();
		taxis.add(new Taxi("kj"));
		taxis.add(new Taxi("joshi"));
		taxis.add(new Taxi("Kshitij"));
		
		int ind = taxis.indexOf(new Taxi("Kshitij"));
		System.out.println(ind);
	}
		
}

