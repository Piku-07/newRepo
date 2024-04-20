package automation.tests;

import org.testng.annotations.Test;
import automation.baseclass.baseClass1;
import automation.pageobjects.ConferencePlaybook;

public class ConferenceExecution extends baseClass1{

	String conferenceName = "AACR";
	String conferenceyear = "2023";
	String conferencemonth = "Jun";
	
	@Test
	public void Sorting() throws InterruptedException {
		ConferencePlaybook cplaybook=  dboard.goToConference();
		cplaybook.sort();
	}
	
	@Test
	public void Filter() {
		ConferencePlaybook cplaybook=  dboard.goToConference();
		cplaybook.filter(conferenceName,conferencemonth, conferenceyear);
	}
	
	@Test
	public void Pagination() {
		ConferencePlaybook cplaybook=  dboard.goToConference();
		cplaybook.pagination();
	}
	

}
