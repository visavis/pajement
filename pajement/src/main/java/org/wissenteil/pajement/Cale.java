package org.wissenteil.pajement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Cale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int interval = -8;
		String timeUnit = "minutes";
		int direction =-1;

		String printedDate = "";
		SimpleDateFormat printableDate = new SimpleDateFormat("HH:mm");
		Calendar dateMoved = new GregorianCalendar();
		if (timeUnit.equals("minutes")) {
			dateMoved.add(Calendar.MINUTE, interval * direction);
		}

		printedDate = printableDate.format(dateMoved.getTime());
		System.out.println(printedDate);

//		 getExecutor().type(
//		 find(CommonLocatorsNames.INPUT_FIELD).parameterize(field),
//		 printedDate);

	}
}
