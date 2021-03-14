//Programmer: Justin Hastings 12147349
//File: WaterChargeGUI.java
//Date: October 16 2020
//Purpose: COIT11222 assignment three, task 2 - WaterCharge
//This class will calculate the water charge for each user
//and return the charge to the WaterChargeGUI class.

public class WaterCharge
{
	//Defines variables
	private String customerName;
	private String phoneNum;
	private int waterUsage;

	//default constructor
	public WaterCharge()
	{
		customerName = null;
		phoneNum = null;
		waterUsage = 0;
	}

	//paramterised constructor
	public WaterCharge(String customerName, String phoneNum, int waterUsage)
	{
		//Sets the array values as the arguments being passed into the method.
		this.customerName = customerName;
		this.phoneNum = phoneNum;
		this.waterUsage = waterUsage;
	}

	//3 set (Mutator) methods
	public void setName(String customerName)
	{
		//Sets the customerName array value to the argument being passed into the method
		this.customerName = customerName;
	}

	public void setPhone(String phoneNum)
	{
		//Sets the phoneNum array value to the argument being passed into the method
		this.phoneNum = phoneNum;
	}

	public void setWaterUsage(int waterUsage)
	{
		//Sets the waterUsage array value to the argument being passed into the method
		this.waterUsage = waterUsage;
	}

	//3 get (Accessor) methods
	public String getName()
	{
		//Returns the customer name from the array
		return customerName;
	}

	public String getPhoneNum()
	{
		//Returns the phone number from the array
		return phoneNum;
	}

	public int getWaterUsage()
	{
		//Returns the water usage from the array
		return waterUsage;
	}

	//calculateCharge() method
	public double calculateCharge()
	{

		//Sets the customer's water usage for calculating the customer's charge as the water usage value in the array for that customer
		int customerWaterUsage = getWaterUsage();

		//Constant for the fixed sewage service rate
		final int FIXED_SERVICE_FEE = 120;

		//Constant for the fixed low water use rate
		final double LOW_RATE = 0.8;
		//Constant for the fixed middle water use rate
		final double MID_RATE = 0.9;
		//Constant for the fixed high water use rate
		final double HIGH_RATE = 1.1;

		//Constant to determine low to middle water use
		final int MIDDLE_USAGE = 50;
		//Constant to determine middle to high water use
		final int HIGH_USAGE= 90;

		//Double declaration for the customer's water usage charge
		double customerUsageCharge = 0;

		//Double declaration for the customer's total charge (usage charge + service fee)
		double customerTotalCharge = 0;

		//Checks water usage, determine rates based on water usage
		//Checks if the water usage is equal to or below 50KL
		if (customerWaterUsage <= MIDDLE_USAGE)
		{
			//Use LOW_RATE
			//Sets the usage charge to the water usage * the low rate(0.8)
			customerUsageCharge = customerWaterUsage * LOW_RATE;
		}
		//Checks if the water usage is above 50KL but equal to or below 50KL
		else if (customerWaterUsage <= HIGH_USAGE)
		{
			//Use MID_RATE + LOW_RATE for first 50KL
			//First 50KL is at low rate, rest is at middle rate
			//Sets the usage charge to the middle usage(50) * low rate(0.8) + the middle rate(0.9) * (water usage - middle usage(50))
			customerUsageCharge = (MIDDLE_USAGE * LOW_RATE) + (MID_RATE * (customerWaterUsage - MIDDLE_USAGE));
		}
		//to use if the water usage is above 90KL
		else
		{
			//Use HIGH_RATE for excess + MID_RATE for 40KL + LOW_RATE for 50KL
			//First 50KL is at low rate, next 40KL is at the middle rate, rest is at high rate
			//Sets the usage charge to the middle usage(50) * low rate(0.8) + the middle rate(0.9) * (high usage(90) - middle usage(50)) + the high rate(1.1) * (water usage - high usage(90))
			customerUsageCharge = (MIDDLE_USAGE * LOW_RATE) + ((HIGH_USAGE - MIDDLE_USAGE) * MID_RATE) + (HIGH_RATE * (customerWaterUsage - HIGH_USAGE));
		}
		//Calculates the customer's total charge by adding their usage charge to the fixed service fee of $120
		customerTotalCharge = customerUsageCharge + FIXED_SERVICE_FEE;
		//Returns the customer's total charge so that it's value can be used in the main method after it has been called
		return customerTotalCharge;
	}

}