import java.util.Random;
import java.util.Scanner;

public class Runner3 {
	
	
	/*
		locations names are In CAPS 
		txi name are like taxi 1 , taxi 2
		so while giving location please give location in caps 
	*/
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("taxies list are");
		// for taxies generation and putting random values
		Taxi[] taxies = new Taxi[6];
		for (int i = 0; i < taxies.length; i++) {
			taxies[i] = new Taxi("taxi " + (i + 1), (char) (new Random().nextInt(6) + 65), new Random().nextInt(100),
					new Random().nextInt(100) % 7 != 0 ? true : false);
		}

		// for putting taxies at random locations
		Location[] locs = new Location[6];
		for (int i = 0; i < locs.length; i++) {
			locs[i] = new Location((char) (i + 65));
			for (int j = 0; j < taxies.length; j++) {
				if (locs[i].locationName == taxies[j].startingPoint) {
					locs[i].taxies.add(taxies[j]);
				}
			}
		}

		boolean wantsToContinue = true;

		while (wantsToContinue) {

			 System.out.println("press 1 to view taxies");
			 System.out.println("press 2 to book taxi");
			 int input = scanner.nextInt();
			//int input = 2;

			// comment it after testing
			System.out.println("below is the Taxies List ");
			for (int i = 0; i < locs.length; i++) {
				if (locs[i].taxies.size() == 0) {
					System.out.println("location ->" + locs[i].locationName);
				}
				for (int j = 0; j < locs[i].taxies.size(); j++) {
					System.out
							.println("location ->" + locs[i].locationName + " taxi details ->" + locs[i].taxies.get(j));
				}
			}

			switch (input) {
			case 1:
				System.out.println("below is the Taxies List ");
				for (int i = 0; i < locs.length; i++) {
					if (locs[i].taxies.size() == 0) {
						System.out.println("location ->" + locs[i].locationName);
					}
					for (int j = 0; j < locs[i].taxies.size(); j++) {
						System.out.println(
								"location ->" + locs[i].locationName + " taxi details ->" + locs[i].taxies.get(j));
					}
				}
				break;

			case 2:
				System.out.println("enter you name");
				String customerName = scanner.nextLine();
				System.out.println("choose pickup location [ A | B | C | D | E | F ]");
				char startPoint = scanner.next().charAt(0);
				System.out.println("choose drop location [ A | B | C | D | E | F ]");
				char dropPoint = scanner.next().charAt(0);

				// login to find the taxi with minimun fare or the nearest taxi
				// comparison taxi
				Taxi temp = new Taxi();
				temp.earning = 1000000;
				boolean flag = false;

				// find the in dex o locatin to quick the search
				int indexOfLocation = 0;
				int dropIndex = 0;

				// fin the drop index to push the cab or taxi to that index after the ride
				for (int i = 0; i < locs.length; i++) {
					if (locs[i].locationName == dropPoint) {
						dropIndex = i;
					}
				}

				for (int i = 0; i < locs.length; i++) {

					// seach at the same location
					if (locs[i].locationName == startPoint) {

						indexOfLocation = i;

						// this loop will iterate the taxies in tht location
						for (int j = 0; j < locs[i].taxies.size(); j++) {
							if (locs[i].taxies.get(j).earning < temp.earning
									&& locs[i].taxies.get(j).freeStatus == true) {
								temp = locs[i].taxies.get(j);
								flag = true;
							}
						}
						if (flag) {

							System.out.println("welcome " + customerName + " " + temp.taxiName + " will pick you from "
									+ temp.startingPoint + " total fare to be paid is --> "
									+ temp.calculateEarnings(startPoint, dropPoint));

							temp.startingPoint = dropPoint;
							temp.dropPoint = ' ';
							locs[indexOfLocation].taxies.remove(temp);
							locs[dropIndex].taxies.add(temp);
							break;
						}
					}
				}
				if (!flag) {
					// if taxi ids in differet location
					System.out.println("no taxi in you area " + indexOfLocation);
					System.out.println("looking in nearby area");
					int inc = 1;
					int PickedUpTaxiINdex = 0;
					while (!flag) {

						int upper = inc + indexOfLocation;
						int lower = Math.abs(inc - indexOfLocation);
						System.out.println("upper is " + upper);
						System.out.println("lower is " + lower);

						if (upper < locs.length) {
							for (int j = 0; j < locs[upper].taxies.size(); j++) {
								if (locs[upper].taxies.get(j).earning < temp.earning
										&& locs[upper].taxies.get(j).freeStatus == true) {
									temp = locs[upper].taxies.get(j);
									flag = true;
									PickedUpTaxiINdex = upper;
								}
							}
						}
						if (lower >= 0 && lower < indexOfLocation) {
							for (int j = 0; j < locs[lower].taxies.size(); j++) {
								// System.out.println(lower);
								if (locs[lower].taxies.get(j).earning < temp.earning
										&& locs[lower].taxies.get(j).freeStatus == true) {
									temp = locs[lower].taxies.get(j);
									flag = true;
									PickedUpTaxiINdex = lower;
								}
							}
						}
						if (inc > locs.length) {
							break;
						}
						inc++;
					}
					temp.dropPoint = dropPoint;
					temp.startingPoint = startPoint;
					// temp.depatTime = Math.abs(startPoint -dropPoint)*60;
					if (flag) {
						System.out.println("welcome " + customerName + " " + temp.taxiName + " will pick you from "
								+ temp.startingPoint + " total fare to be paid is --> "
								+ temp.calculateEarnings(startPoint, dropPoint));
						
						// status has not been changed in taxis
						// if you want that can be implimented 
						// by = temp.freeStatus = false;
						
						temp.startingPoint = dropPoint;
						temp.dropPoint = ' ';
						locs[PickedUpTaxiINdex].taxies.remove(temp);
						locs[dropIndex].taxies.add(temp);
						break;
					} else {
						System.out.println("no taxi available yet ");
						System.out.println("try after sometime");
					}
				}
				break;
			}
		}
		System.out.println("Do you want to book more");
		System.out.println("Press Y to continue and N to Exit");
		String wantsFlag = scanner.next();
		if (wantsFlag.equals("N") || wantsFlag.equals("n")) {
			wantsToContinue = false;
		}
	}
}
