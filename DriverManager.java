package Car;
public class DriverManager {
	public static void main(String[] args) {
			RentalCarSystem rentalcarsystem=new RentalCarSystem ();
			car car1=new car("C001","Toyota","camry",600.0);
			car car2=new car("C002","Honda","Accord",700.0);
			car car3=new car("C003","Mahindra","thar",1500.0);
			rentalcarsystem.addCar(car1);
			rentalcarsystem.addCar(car2);
			rentalcarsystem.addCar(car3);
			rentalcarsystem.menu();
		}
	}
