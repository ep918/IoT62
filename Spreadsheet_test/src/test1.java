import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.spreadsheet.SpreadsheetQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class test1 {

	public static void main(String[] args) throws AuthenticationException {

		SpreadsheetService service = new SpreadsheetService("MySpreadsheetIntegration-v1");

		service.setUserCredentials("", "");

		//

		String SPREADSHEET_URL = "https://docs.google.com/spreadsheet/ccc?key=0Apa00XA0yqqgdDFvczZNdmdLX3MxbTVLNFdkX0kxZUE&usp=drive_web#gid=0";
		SpreadsheetFeed feed = null;

		try {
			URL sheetListUrl = new URL(SPREADSHEET_URL);

			SpreadsheetQuery query = new SpreadsheetQuery(sheetListUrl);
			query.setTitleQuery("Sheet1");
			feed = service.getFeed(query, SpreadsheetFeed.class);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		//

		List<SpreadsheetEntry> spreadsheetEntries = feed.getEntries();
		SpreadsheetEntry spreadsheet = spreadsheetEntries.get(0);

		try {
			List<WorksheetEntry> worksheets = spreadsheet.getWorksheets();

			for(WorksheetEntry worksheet : worksheets)
			{
				System.out.println(worksheet.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}



	}

}
