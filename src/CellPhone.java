// -----------------------------------------------------
// Assignment 4
// Part: 2
// Written by: Laurent (40020483)
// This assignment is meant practice the use of LinkedList
// Read through a list of cellphone information and create
// a LinkedList with that information
// -----------------------------------------------------

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #4 Part 2
 * Due Date: April 8 2019
 * Cellphone Class
 */
public class CellPhone {

	private long serialNumber;
	private String brand;
	private int year;
	private double price;

	/**
	 * CellPhone Default Constructor
	 */
	public CellPhone()
	{
		System.out.println("Default constructor of CellPhone has been called.");
	}

	/**
	 * CellPhone parameterized Constructor
	 * @param serialNumber a long that represents the serialNumber
	 * @param brand a String that represents the brand
	 * @param year an int that represents the year
	 * @param price a double that represents the price
	 */
	public CellPhone(long serialNumber, String brand, int year, double price) {
		this.serialNumber = serialNumber;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}

	// 		=== Getters, Setters, equals and toString ===
	//		 |										 |
	//      \|/										\|/
	//		 V										 v

	public long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
