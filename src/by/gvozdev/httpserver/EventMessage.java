/**
 * 
 */
package by.gvozdev.httpserver;

/**
 * @author Gvozdev Alexsander Apr 26, 2018
 */
public class EventMessage {

	public void getMessageServer(String msg) {

		String message = msg;

		if (message.isEmpty()) {
			System.err.println("NO A MESSAGE!!!");
		} else {
			System.err.println(message);
		}

	}

}
