package Car;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class car {
private String Carid;
private String brand;
private String Model;
private double basepriceperday;
private boolean isAvailable;
public car(String Carid,String brand, String model,double basepriceperday) {
	this.Carid=Carid;
	this.brand =brand;
	this.Model=model;
	this.basepriceperday=basepriceperday;
	this.isAvailable=true;
}
public String getCarid()
{
	return Carid;
}
public String getbrand() {
	return brand;
}
public String getmodel() {
	return Model;
}
public double CalculatePrice(int rentalDays)
{
	return basepriceperday*rentalDays;
}
public boolean isAvailable() {
	return isAvailable;
}
public void rent() {
	isAvailable=false;
}
public void returnCar() {
	isAvailable=true;
}
}
class Customer{
	private String customerid;
	private String name;
	public Customer(String customerid,String name) {
		this.customerid =customerid;
		this.name=name; 
		}
	public String getcustomerid() {
		return customerid;
	}
	public String getname() {
		return name;
	}
}
class Rental{
	private car car;
	private Customer customer;
	private int Days;
	public Rental(car car,Customer customer,int Days) {
		this.car=car;
		this.customer=customer;
		this.Days=Days;
	}
	public car getcar() {
		return car;
	}
	public Customer getcustomer() {
		return customer;
	}
	public int getDays() {
		return Days;
	}
	
}
class RentalCarSystem {
	private List<car>cars;
	private List <Customer>customers;
	private List<Rental>rentals;
	public RentalCarSystem() {
		cars=new ArrayList<>();
		customers=new ArrayList<>();
		rentals=new ArrayList<>();
	}
	public void addCar(car car) {
		cars.add(car);
		
	}
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	public void rentCar(car car,Customer customer, int Days) {
		if(car.isAvailable()) {
			car.rent();
			rentals.add(new Rental(car,customer,Days));
			
		}else {
			System.out.println("Car is not available for rent");
		}
		
	}
	public void returnCar(car car) {
		car.returnCar();
		Rental rentalToRemove=null;
		for(Rental rental:rentals) {
			if(rental.getcar()==car) {
				rentalToRemove=rental;
				break;
			}
		}
		if(rentalToRemove!=null) {
			rentals.remove(rentalToRemove);
		}else {
			System.out.println("Car Was Not rented");
		}
	}
	public void menu() {
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("=====Car Rental System====");
			System.out.println("1.Rent a Car");
			System.out.println("2.Return a Car");
			System.out.println("3.Exit");
			System.out.println("Enter Your Choice:");
			int choice=sc.nextInt();
			sc.nextLine();
			if(choice==1) {
				System.out.println("\n==Rent a Car==\n");
				System.out.println("Entre Your Name:");
				String CustomerName=sc.nextLine();
				System.out.println("\n Available Cars: ");
				for(car car:cars) {
					if(car.isAvailable()) {
						System.out.println(car.getCarid()+"-"+car.getbrand()+""+car.getmodel());
					}
				}
				System.out.println("\n Enter The Car ID You Want To rent:");
				String carid=sc.nextLine();
				System.out.println("Enter The Number of Days Of Rental:");
				int rentalDays=sc.nextInt();
				sc.nextLine();
				Customer newCustomer=new Customer("CUS"+(customers.size()+1),CustomerName);
				addCustomer(newCustomer);
				car selectedCar=null;
				for(car car:cars) {
					if(car.getCarid().equals(carid)&&car.isAvailable()) {
						selectedCar=car;
						break;
					}
				}
				if(selectedCar!=null) {
					double totalprice=selectedCar.CalculatePrice(rentalDays);
					System.out.println("\n==Rental information==\n");
					System.out.println("Customer ID:"+newCustomer.getcustomerid());
					System.out.println("Customer Name:"+newCustomer.getname());
					System.out.println("Car:"+selectedCar.getbrand()+""+selectedCar.getmodel());
					System.out.println("Renatl Days:"+rentalDays);
					System.out.printf("Total Price:$%2fn",totalprice);
					System.out.print("\n Confirm rental(Y/N):");
					String confirm=sc.nextLine();
					if(confirm.equalsIgnoreCase("y")) {
						rentCar(selectedCar,newCustomer,rentalDays);
						System.out.println("\n Car rented Successfully.");
						
					}else {
						System.out.println("\nRental canceled.");
					}
				}
					else {
						System.out.println("\nInvalid car selection or not available for rent.");
					}
				}else if
					
				(choice==2) {
					System.out.println("\n==Return a Car==\n");
					System.out.println("Entre The Car ID You Want TO Return:");
					String carid=sc.nextLine();
					car carToRetun=null;
					for(car car:cars) {
						if(car.getCarid().equals(carid)&&!car.isAvailable()) {
							carToRetun=car;
							break;
						}
					}
					if(carToRetun != null ) {
						Customer customer=null;
						for(Rental rental:rentals) {
							if(rental.getcar()==carToRetun) {
								customer=rental.getcustomer();
								break;
							}
						}
						if(customer!=null) {
							returnCar(carToRetun);
							System.out.println("Car returned successfully by="+customer.getname());
					}else {
						System.out.println	("Car Was Not rented or rented information is missing.");
					}
					}else {
						System.out.println("Invalid ID or car is not rented");
					}
				}else if(choice==3) {
					break;
				}else {
					System.out.println("Invalid choice.Please Entre valid option.");
				}
		}
		 
		System.out.println("\n Thank You For Using The Car Rental System!");
		
			
	}	
}