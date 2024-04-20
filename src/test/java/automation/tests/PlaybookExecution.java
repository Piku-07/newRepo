package automation.tests;
import org.testng.annotations.Test;

import automation.baseclass.baseClass1;
import automation.pageobjects.Playbook;

public class PlaybookExecution extends baseClass1{
	String conferenceName= "AACR";
	String playbookFilter= "ED008";
	String workflowStatus= "APPROVED";
	
	@Test
	public void Sorting() {
		Playbook playbook= dboard.gotoPlaybook(conferenceName);
		playbook.sortPlaybook();
	}
	
	@Test
	public void Filter() {
		Playbook playbook= dboard.gotoPlaybook(conferenceName);
		playbook.filterPlaybook(playbookFilter, workflowStatus);
	}
	
	@Test
	public void addPlaybook() throws InterruptedException {
		Playbook playbook= dboard.gotoPlaybook(conferenceName);
		playbook.addPlaybook(playbookFilter);
	}
}
