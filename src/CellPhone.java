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
	public CellPhone(long serialNumber, String brand, double price, int year) {
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

	/**
	 * Gets serial number
	 * @return serial number (long)
	 */
	public long getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Set serial number
	 * @param serialNumber long containing the serial number
	 */
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Get the brand
	 * @return returns a string containing the brand of the cellphone
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Set the brand
	 * @param brand a String containing the brand of the cellphone
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Get the year
	 * @return an int representing the year of the cellphone
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set the year
	 * @param year an int containing the year of the cellphone
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Get the price
	 * @return a double representing the price of the cellphone
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the price
	 * @param price a double containing the price of the cellphone
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Checks whether two cellphones are equal (serial number notwithstanding)
	 * @param o object to check whether it is equal
	 * @return
	 */
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
	/**
	 * Returns a string containign the information of the cellphone
	 */
	public String toString() {
		return "[" + serialNumber + ": " + brand + " " + price + "$ " + year + "]";
	}
}
