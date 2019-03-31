// -----------------------------------------------------
// Assignment 4
// Part: 2
// Written by: Laurent (40020483)
// This assignment is meant practice the use of LinkedList
// Read through a list of cellphone information and create
// a LinkedList with that information
// -----------------------------------------------------

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #4 Part 2
 * Due Date: April 8 2019
 * Cellphone Class
 *
 * Privacy leak: Uses the Instance vars from CellListUtilization for keyInputs
 */
public class CellPhone {

	private long   serialNumber;
	private String brand;
	private int    year;
	private double price;

	/**
	 * CellPhone Default Constructor
	 */
	public CellPhone() {
		System.out.println("Default constructor of CellPhone has been called.");
	}

	/**
	 * CellPhone parameterized Constructor
	 *
	 * @param serialNumber a long that represents the serialNumber
	 * @param brand        a String that represents the brand
	 * @param year         an int that represents the year
	 * @param price        a double that represents the price
	 */
	public CellPhone(long serialNumber, String brand, int year, double price) {
		this.serialNumber = serialNumber;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}

	/**
	 * Copy constructor
	 *
	 * @param cellphone    the CellPhone to be cloned
	 * @param serialNumber the Serial Number of the new Cellphone
	 */
	public CellPhone(CellPhone cellphone, long serialNumber) {
		this.serialNumber = serialNumber;
		this.brand = cellphone.brand;
		this.year = cellphone.year;
		this.price = cellphone.price;
	}

	@Override
	/**
	 * Clone method
	 * @return a copy of a CellPhone with a new serial number
	 */
	public CellPhone clone() {
		Scanner keyIn           = CellListUtilization.Instance.keyIn;
		boolean isCorrect       = false;
		long    newSerialNumber = 0;

		while (!isCorrect)
		{
			try
			{
				System.out.print("Please enter a new serial number for the cloned cellphone: ");
				newSerialNumber = keyIn.nextLong();
				isCorrect = true;
			} catch (InputMismatchException e)
			{
				System.out.println("Please enter a valid serial number. Try again...");

				if (keyIn.hasNextLine())
				{
					keyIn.nextLine();
				}
			}
		}

		return new CellPhone(this, newSerialNumber);
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		CellPhone cellPhone = (CellPhone) o;
		return year == cellPhone.year &&
				Double.compare(cellPhone.price, price) == 0 &&
				brand.equals(cellPhone.brand);
	}

	@Override
	public String toString() {
		return "Cellphone information: \n" +
				"Serial Number is \t" + serialNumber + "\n" +
				"Brand is \t\t\t" + brand + "\n" +
				"Model Year is \t\t" + year + "\n" +
				"Price is \t\t\t" + price +  "\n";
	}
}
