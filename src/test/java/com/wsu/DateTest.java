package com.wsu;

import static org.junit.Assert.*;
import org.junit.Test;
import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;
import java.util.logging.Logger;


//These are needed to use PowerMock/EasyMock
@RunWith(PowerMockRunner.class)
@PrepareForTest(Date.class)
/**
 * The key take away points from this test class are as follows
 * 1) Mockito should not be used when you do not need to.  Create new objects and uses their real methods.  
 * 2) Mockito should be used if you want to stub out, or manipulate the behavior of a non-static method within the class.
 * (see initializeAttributes and toString for examples).  Basically, using Mockito allow the Unit test to mock methods within the tested
 * method to see to desired outcome. 
 * 3) Use PowerMock and EasyMock to address static methods since Mockito cannot do that (see isDateValid for example).  Essentially it 
 * does the same thing as Mockito. 
 * 
 * 4) Therefore, when unit testing if the method does not have sub-methods use standard JUnit.  When the tested method has inner methods
 * use Mockitol to mock the behavior then go from there.  If the tested method has static methods, use PowerMockito.  You can also use a combination 
 * of both
 */
public class DateTest {
	
	/**Logger for debugging*/
	private Logger logger = Logger.getLogger("Date Test Logger");
	
	@Test 
	public void testDate() {

		Date d  = spy(new Date(1,2,2000));
		
		//verify(d).initalizeAttributes(); //fails, says the method was not called though it is in the constructor
		
		//If thee constructor worked, then DD, Mm, and Yyyy should have been set 
		assertEquals(1, d.getDd()); // fails, expected 1 but got 0
		assertEquals(2, d.getMm()); //fails, expected 2 but got 0
		assertEquals(2000, d.getYyyy()); //fails, expected 2000 but got 0
	}
	
	@Test
	public void testLastDayOfMonth() {
		//Use Mockito since I do not need to parameters of the constructor
		Date date = new Date(23, 1, 2020);
		assertEquals(31, date.lastDayOfMonth(1, 2020));
		
		date = new Date(23, 2, 2020);
		assertEquals(29, date.lastDayOfMonth(2, 2020));
		
		date = new Date(23, 2, 2019);
		assertEquals(28, date.lastDayOfMonth(2, 2019));		
	}
	
	@Test
	public void testIsValidDate() {
		//Best possible example for PowerMock.  Mocking inner methods to test outter method
		//PowerMock is used to mock up static inner methods
		PowerMock.mockStaticPartialStrict(Date.class, "validRangeForMonth");
		
		//Mocking the behavior of the inner methods
		EasyMock.expect(Date.validRangeForMonth(5)).andReturn(true);
		EasyMock.expect(Date.validRangeForMonth(13)).andReturn(false);
		PowerMock.replayAll();		
		
		//Test isValidDate
		assertTrue(Date.isValidDate(31, 5, 1982));
		assertFalse(Date.isValidDate(31, 13, 1982));
		
		PowerMock.mockStaticPartial(Date.class, "validRangeForDay");
		
		//Mocking the behavior of the inner methods
		EasyMock.expect(Date.validRangeForDay(31)).andReturn(true);
		EasyMock.expect(Date.validRangeForDay(32)).andReturn(false);
		EasyMock.expect(Date.validRangeForDay(-1)).andReturn(false);
		PowerMock.replayAll();
		
		//Test isValidDate
		assertFalse(Date.isValidDate(32, 5, 1982));
		assertFalse(Date.isValidDate(-1, 5, 1982));
		assertTrue(Date.isValidDate(31, 5, 1982));
		
		PowerMock.mockStaticPartial(Date.class, "validRangeForYear");
		
		//Mocking the behavior of the inner methods
		EasyMock.expect(Date.validRangeForYear(1982)).andReturn(true);
		EasyMock.expect(Date.validRangeForYear(1600)).andReturn(false);
		EasyMock.expect(Date.validRangeForYear(2020)).andReturn(false);
		PowerMock.replayAll();
		
		//Test isValidDate
		assertTrue(Date.isValidDate(31, 5, 1982));
		assertFalse(Date.isValidDate(31, 5, 1600));
		assertFalse(Date.isValidDate(31, 5, 2020));
	}
	
	@Test
	public void testValidationCombination() {
		//PowerMock allows for testing static methods.  Mock in general is used to test methods inside a method. 
		PowerMock.mockStaticPartial(Date.class, "isLeap");
		EasyMock.expect(Date.isLeap(2012)).andReturn(true);
		PowerMock.replayAll();
		
		assertTrue(Date.validCombination(9,9, 2012));
		
		PowerMock.mockStaticPartial(Date.class, "isLeap");
		EasyMock.expect(Date.isLeap(2017)).andReturn(false);
		PowerMock.replayAll();
		
		assertTrue(Date.validCombination(31, 1, 2017));
	}

	@Test
	public void testInitializeAttributes() {
		//Best example for using Mockito to spy a class, set method returns, call a method, and verify those methods worked
		//Use Mockito for non-static method is fine
		Date spyDate = spy(new Date(31, 5,1982));
		
		//Stub all methods within initializeAttributes
		doReturn("Tuesday").when(spyDate).dateToDayName();
		doReturn(5).when(spyDate).dateToDayNumber();
		doReturn("Gemini").when(spyDate).zodiacSign();
		
		//Make the call
		spyDate.initalizeAttributes();
				
		//These methods are called in initializeAttibutes, verify there were called
		verify(spyDate).dateToDayName();
		verify(spyDate).dateToDayNumber();
		verify(spyDate).zodiacSign();
		
		//If the above three methods worked, then name, day, and zodiac variables should have been set
		assertEquals("Tuesday", spyDate.getDayName());
		assertEquals(5, spyDate.getDayNumber());
		assertEquals("Gemini", spyDate.getZodiacSign());
	}

	@Test
	public void testToString() {
		
		Date spyDate2  = spy(new Date(30,8,2017));
		
		//Stub all methods within toString()
		doReturn("Wednesday").when(spyDate2).getDayName();
		doReturn(8).when(spyDate2).getMm();
		doReturn(30).when(spyDate2).getDd();
		doReturn(2017).when(spyDate2).getYyyy();
		doReturn(242).when(spyDate2).getDayNumber();
		doReturn("Virgo").when(spyDate2).getZodiacSign();
		
		//Run the test
		assertEquals("Wednesday, 8/30/2017, is the 242 of "
				+ "the year and the zodiac sign is Virgo",spyDate2.toString());

	}

	@Test
	public void testZodiacSign() {
		Date date = spy(new Date(22,12,2010));
		
		//Idea is to set the month and dae then verify that the zodiac output matches the what the input suggests
		date.setMonthAndDay(1, 19);
		assertEquals("Capricorn", date.zodiacSign());

		// more border cases based on the implementation:
		date.setMonthAndDay(12, 31);
		assertEquals("Capricorn", date.zodiacSign());
		date.setMonthAndDay(1, 1);
		assertEquals("Capricorn", date.zodiacSign());

		// "Aquarius": January 20 - February 18
		date.setMonthAndDay(1, 20);
		assertEquals("Aquarius", date.zodiacSign());
		date.setMonthAndDay(1, 31);
		assertEquals("Aquarius",date.zodiacSign());
		date.setMonthAndDay(2, 1);
		assertEquals("Aquarius",date.zodiacSign());
		date.setMonthAndDay(2, 18);
		assertEquals("Aquarius",date.zodiacSign());

		// "Pisces": February 19 - March 20
		date.setMonthAndDay(2,19);
		assertEquals("Pisces",date.zodiacSign());
		date.setMonthAndDay(2, 29);
		assertEquals("Pisces",date.zodiacSign());
		date.setMonthAndDay(3, 1);
		assertEquals("Pisces",date.zodiacSign());
		date.setMonthAndDay(3, 20);
		assertEquals("Pisces",date.zodiacSign());

		// "Aries": March 21 - April 19
		date.setMonthAndDay(3, 21);
		assertEquals("Aries",date.zodiacSign());
		date.setMonthAndDay(3, 31);
		assertEquals("Aries",date.zodiacSign());
		date.setMonthAndDay(4, 1);
		assertEquals("Aries",date.zodiacSign());
		date.setMonthAndDay(4, 19);
		assertEquals("Aries",date.zodiacSign());

		// "Taurus": April 20 - May 20
		date.setMonthAndDay(4, 20);
		assertEquals("Taurus",date.zodiacSign());
		date.setMonthAndDay(4, 30);
		assertEquals("Taurus",date.zodiacSign());
		date.setMonthAndDay(5, 1);
		assertEquals("Taurus",date.zodiacSign());
		date.setMonthAndDay(5, 20);
		assertEquals("Taurus",date.zodiacSign());

		// "Gemini": May 21 - June 20
		date.setMonthAndDay(5, 21);
		assertEquals("Gemini",date.zodiacSign());
		date.setMonthAndDay(5, 31);
		assertEquals("Gemini",date.zodiacSign());
		date.setMonthAndDay(6, 1);
		assertEquals("Gemini",date.zodiacSign());
		date.setMonthAndDay(6, 20);
		assertEquals("Gemini",date.zodiacSign());

		// "Cancer": June 21 - July 22
		date.setMonthAndDay(6, 21);
		assertEquals("Cancer",date.zodiacSign());
		date.setMonthAndDay(6, 30);
		assertEquals("Cancer",date.zodiacSign());
		date.setMonthAndDay(7, 1);
		assertEquals("Cancer",date.zodiacSign());
		date.setMonthAndDay(7, 20);
		assertEquals("Cancer",date.zodiacSign());

		// "Leo": July 23 - August 22
		date.setMonthAndDay(7, 23);
		assertEquals("Leo",date.zodiacSign());
		date.setMonthAndDay(7, 31);
		assertEquals("Leo",date.zodiacSign());
		date.setMonthAndDay(8, 1);
		assertEquals("Leo",date.zodiacSign());
		date.setMonthAndDay(8, 22);
		assertEquals("Leo",date.zodiacSign());

		// "Virgo": August 23 - September 22
		date.setMonthAndDay(8, 23);
		assertEquals("Virgo",date.zodiacSign());
		date.setMonthAndDay(8, 31);
		assertEquals("Virgo",date.zodiacSign());
		date.setMonthAndDay(9, 1);
		assertEquals("Virgo",date.zodiacSign());
		date.setMonthAndDay(9, 22);
		assertEquals("Virgo",date.zodiacSign());

		// "Libra": September 23 - October 22
		date.setMonthAndDay(9, 23);
		assertEquals("Libra",date.zodiacSign());
		date.setMonthAndDay(9, 30);
		assertEquals("Libra",date.zodiacSign());
		date.setMonthAndDay(10, 1);
		assertEquals("Libra",date.zodiacSign());
		date.setMonthAndDay(10, 22);
		assertEquals("Libra",date.zodiacSign());
		
		// "Scorpio": October 23 - November 21
		date.setMonthAndDay(10, 23);
		assertEquals("Scorpio",date.zodiacSign());
		date.setMonthAndDay(10, 31);
		assertEquals("Scorpio",date.zodiacSign());
		date.setMonthAndDay(11, 1);
		assertEquals("Scorpio",date.zodiacSign());
		date.setMonthAndDay(11, 21);
		assertEquals("Scorpio",date.zodiacSign());

		// "Sagittarius": November 22 - December 21
		date.setMonthAndDay(11, 23);
		assertEquals("Sagittarius",date.zodiacSign());
		date.setMonthAndDay(11, 30);
		assertEquals("Sagittarius",date.zodiacSign());
		date.setMonthAndDay(12, 1);
		assertEquals("Sagittarius",date.zodiacSign());
		date.setMonthAndDay(12, 21);
		assertEquals("Sagittarius",date.zodiacSign());
	}

	@Test
	public void testIsLeap() {
		// divisible by 4:
		assertTrue(Date.isLeap(2012));
		// not divisible by 4
		assertFalse(Date.isLeap(2007));
		// century, not  divisible by 400
		assertFalse(Date.isLeap(1900));
		// century, divisible by 400
		assertTrue(Date.isLeap(2000));
	}
	
	@Test
	public void testDateToDayNumber() {
		Date date = new Date(23, 1, 2009);
		
		//Test set from constructor
		assertEquals(23, date.dateToDayNumber());		
		
		//Test on leap year
		date = spy(new Date(2, 2, 2000));
		assertEquals(33, date.dateToDayNumber());
		
		date = spy(new Date(1, 3, 1999));
		assertEquals(60, date.dateToDayNumber());
		
		//Test on Leap Year
		date = spy(new Date(1, 3, 2020));
		assertEquals(61, date.dateToDayNumber());
	}
	
	@Test
	public void testDateToDayName() {
		Date date = new Date(9, 9, 2020);
		assertEquals("Wed", date.dateToDayName());
		assertNotEquals("Thu", date.dateToDayName());
		
		date = new Date(1, 12, 2019);
		assertEquals("Sun", date.dateToDayName());
		assertNotEquals("Mon", date.dateToDayName());
	}

	@Test
	public void testValidCombination() {
		//Using PowerMock for static methods without expecting a reply.  These are not inner static methods
		PowerMock.createStrictPartialMock(Date.class, "validCombination");
		PowerMock.replayAll();	
		assertTrue(Date.validCombination(23, 1, 2009));
		
		//Checking for days that over-exceed the month
		assertFalse(Date.validCombination(30, 2, 2009));
		
		//Test Leap Year
		assertTrue(Date.validCombination(29, 2, 2020));	
		
		//Test non-leap Feb
		assertFalse(Date.validCombination(29, 2, 1999));
	}
	
	@Test
	public void testValidRangeForDay() {
		PowerMock.createStrictPartialMock(Date.class, "validRangeForDay");
		PowerMock.replayAll();
		assertTrue(Date.validRangeForDay(20));
		assertFalse(Date.validRangeForDay(32));
		assertFalse(Date.validRangeForDay(0));
	}

	@Test
	public void testValidateRangeForMonth() {
		PowerMock.createStrictPartialMock(Date.class, "validRangeForMonth");
		PowerMock.replay();
		assertTrue(Date.validRangeForMonth(10));
		assertFalse(Date.validRangeForMonth(13));
		assertFalse(Date.validRangeForMonth(0));
	}
	
	@Test
	public void testValidRangeForYear() {
		PowerMock.createStrictPartialMock(Date.class, "validRangeForYear");
		PowerMock.replay();
		assertTrue(Date.validRangeForYear(2020));
		assertTrue(Date.validRangeForYear(1700));
		assertFalse(Date.validRangeForYear(1699));
		assertFalse(Date.validRangeForYear(2021));
	}
}
