package testCode;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CleanDate {
	/**Logger for debugging*/
	
	Map<Integer, String> dateToDayMap = new HashMap<Integer, String>();

	private int mm;
	private int dd;
	private int yyyy;
	private int dayNumber=0;
	private String dayName = "";
	private String zodiacSign = "";
	private String isLeap = null;
	int days[] = { 31, 28, 31, 30, 31, 30, 
            31, 31, 30, 31, 30, 31 }; 

	/**
	 * Constructor call
	 * @param dd - the day of the month
	 * @param mm - the month in the year
	 * @param yyyy - the four digit year
	 */
	public CleanDate(int dd,int mm,int yyyy){	
		this.mm=mm;
		this.dd=dd;
		this.yyyy=yyyy;
		
		initalizeAttributes();
	}
	
	/**Method used to set the dateName, dateNumber, and zodiacsign using the parameter in the constructor*/
	public void initalizeAttributes() {

		if(validRangeForDay(dd) && validRangeForMonth(mm) && validRangeForYear(yyyy)) {
			this.dayNumber= dateToDayNumber();
			this.dayName= dateToDayName();
			this.zodiacSign=zodiacSign();
		} else {
			System.out.println("Cannot initalize attributes since a dd, mm, or yyyy is invalid");
		}
		
		dateToDayMap.put(1, "Fri");
		dateToDayMap.put(2, "Sat");
		dateToDayMap.put(3, "Sun");
		dateToDayMap.put(4, "Mon");
		dateToDayMap.put(5, "Tues");
		dateToDayMap.put(6, "Wed");
		dateToDayMap.put(7, "Thu");
	}
	
	// Returns the date in the following format:
	// <dayName>, <mm>/<dd>/<yyyy>, is the <dayNumber> of the year and the zodiac sign is <zodiacSign>
	public String toString(){
		isLeap = (isLeap(yyyy)) ? ", and it is a leap year" : ", and it is not a leap year";
		return getDayName() + ", " + mm + "/" + dd+ "/" +yyyy + ", is the " + getDayNumber() + " of the year and the zodiac sign is " + zodiacSign;
	}
	
	public void setMonthAndDay(int month, int day) {
		this.mm = month;
		this.dd = day;
	}
	
	public boolean checkZodiacDates(int month, int day1, int day2) {
		if(getMm() == month && getDd() >= day1 && getDd() <= day2) {
			return true;
		} else {
			return false;		
		}
	}

	// Returns the zodiac sign 
	public String zodiacSign(){
		if(checkZodiacDates(12, 22, 31) || checkZodiacDates(1, 1, 19)) {
			return "Capricorn";
		}		
		if(checkZodiacDates(1, 20, 31) || checkZodiacDates(2, 1, 18)) {
			return "Aquarius";
		}		
		if(checkZodiacDates(2,19, 29) || checkZodiacDates(3, 1, 20)) {
			return "Pisces";
		} 
		if(checkZodiacDates(3,20, 31) || checkZodiacDates(4, 1, 19)) {
			return "Aries";
		}
		if(checkZodiacDates(4,20, 30) || checkZodiacDates(5, 1, 20)) {
			return "Taurus";
		} 
		if(checkZodiacDates(5,21, 31) || checkZodiacDates(6, 1, 20)) {
			return "Gemini";
		}
		if(checkZodiacDates(6,21, 30) || checkZodiacDates(7, 1, 22)) {
			return "Cancer";
		}
		if(checkZodiacDates(7,23, 31) || checkZodiacDates(8, 1, 22)) {
			return "Leo";
		}
		if(checkZodiacDates(8,23, 31) || checkZodiacDates(9, 1, 22)) {
			return "Virgo";
		}
		if(checkZodiacDates(9,23, 30) || checkZodiacDates(10, 1, 22)) {
			return "Libra";
		} 
		if(checkZodiacDates(10,23, 31) || checkZodiacDates(11, 1, 21)) {
			return "Scorpio";
		} 
		if(checkZodiacDates(11,22, 30) || checkZodiacDates(12, 1, 21)) {
			return "Sagittarius";
		} else
			return "";
	}



	//validRangeForDay will return true if the parameter thisDay is in the valid range
	public static boolean validRangeForDay(int thisDay)
	{
		if ((thisDay >= 1) && (thisDay <= 31))
		{
			System.out.println("Day = "+thisDay);
			return true;
		}else {
			return false;
		}
		
	}

	//validRangeForMonth will return true if the parameter thisMonth is in the valid range
	public static boolean validRangeForMonth(int thisMonth) {
		if ((thisMonth >= 1) && (thisMonth <= 12)){
			System.out.println("Month = "+thisMonth);
			return true;
		}
		else
		{
			return false;
		}
	}

	// validRangeForYear will return true if the parameter thisYear is in the valid range
	public static boolean validRangeForYear(int thisYear) {
		if ((thisYear >= 1700) && (thisYear <= 2020))
		{
			System.out.println("Year = "+thisYear);
			return true;
		}
		return false;
	}

	//validCombination will return true if the parameters are a valid combination 
	public static boolean validCombination(int thisDay,int thisMonth,int thisYear){
		int[] months = new int[]{2, 4, 6, 8, 11};
		for(int i = 0; i < months.length; i++) {
			if(thisDay == 31 && thisMonth == months[i]) {
				System.out.println("Day = "+thisDay+" cannot happen when month is"+ thisMonth);
				return false;
			}
		}
		if ((thisDay == 30) && (thisMonth == 2))
		{
			System.out.println("Day = "+thisDay+" cannot happen in February");
			return false;
		}
		if ((thisDay == 29) && (thisMonth == 2) && !isLeap(thisYear)){
			System.out.println("Day = "+thisDay+" cannot happen in February."); 
			return false;
		}
		return true;
	}
	
	// Returns true if the combination of the parameters is valid 
	public static boolean isValidDate(int thisDay,int thisMonth,int thisYear){
		System.out.println("Entering isValid");
		if (!validRangeForDay(thisDay)){
			return false;
		}
		if (!validRangeForMonth(thisMonth)){
			return false;
		}
		if (!validRangeForYear(thisYear)){
			return false;
		}
		return true;
	}

	    // unless it is a century year. 
		// Century years are leap years only if they 
		// are multiples of 400 (Inglis, 1961); 
		// thus, 1992, 1996, and 2000 are leap years, 
		//while the year 1900 is not a leap year
		public static boolean isLeap(int year)
		{
			if( ((year%4==0) && (year%100!=0)) || (year%400==0)){
				return true;
			}
			return false;
		}


		public int getMm() {
			return mm;
		}

		public void setMm(int mm) {
			this.mm = mm;
		}

		public int getDd() {
			return dd;
		}

		public void setDd(int dd) {
			this.dd = dd;
		}

		public int getYyyy() {
			return yyyy;
		}

		public void setYyyy(int yyyy) {
			this.yyyy = yyyy;
		}

		public int getDayNumber() {
			return dayNumber;
		}

		public void setDayNumber(int dayNumber) {
			this.dayNumber = dayNumber;
		}

		public String getDayName() {
			return dayName;
		}

		public void setDayName(String dayName) {
			this.dayName = dayName;
		}

		public String getZodiacSign() {
			return zodiacSign;
		}

		public void setZodiacSign(String zodiacSign) {
			this.zodiacSign = zodiacSign;
		}

	// returns the last day of the month
	public  int lastDayOfMonth(int month,int year){ 
		int index = month - 1;	
		System.out.println(days[index]);
		int lastDay = days[index];
		if(isLeap(yyyy) && mm == 2) {
			lastDay = lastDay + 1;
		}
		return lastDay;
	}

	// TODO: for Jan 1 it should return 1; for Jan 2 it should return 2 etc.
	public int dateToDayNumber(){
		dayNumber = 0;
		if(mm == 1) {
			//We are in the first month so just go ahead and set/return dd as dayName
			dayNumber = dd;
			return dayNumber;
		} else if(mm == 2) {
			//Get the array value for index 0 and add it to dd then return
			traverseMonths();
			return dayNumber;
		} else {
			//Do the similar as when mm is two; however, must take into account the leap year
			traverseMonths();
			if(isLeap(yyyy)) {
				dayNumber = dayNumber + 1;
			}
			return dayNumber;
		}
	}
	
	private final void traverseMonths() {
		int traverseSize = mm - 1;
		for (int i = 0; i < traverseSize; i ++) {
			dayNumber = dayNumber + days[i];
		}
		dayNumber = dayNumber + dd;
	}

	// TODO: for Jan 1, 2017 it should return Sunday; for Jan 2, 2017 it should return Monday etc.
	public  String dateToDayName(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, dd);
		cal.set(Calendar.MONTH,mm);
		cal.set(Calendar.YEAR,yyyy);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if(!isLeap(yyyy)) {
			day = day-1;
		}
		dayName = dateToDayMap.get(Integer.valueOf(day));
		return dayName;
	}
}
