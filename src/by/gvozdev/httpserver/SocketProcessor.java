package by.gvozdev.httpserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @author Gvozdev Alexsander Apr 26, 2018
 */
public class SocketProcessor implements Runnable {

	EventMessage eventMessage = new EventMessage();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	private Socket s;
	private InputStream is;
	private OutputStream os;

	SocketProcessor(Socket s) throws Throwable {
		this.s = s;
		this.is = s.getInputStream();
		this.os = s.getOutputStream();
	}

	public void run() {
		try {
			readInputHeaders();
			writeResponse("<html><body><h1>SIMPLE HTTP SERVER</h1></br>An example of how to create a server for Java.</body></html>");
		} catch (Throwable t) {
			/* do nothing */
		} finally {
			try {
				s.close();
			} catch (Throwable t) {
				/* do nothing */
			}
		}
		eventMessage.getMessageServer("Client has been finished");
	}

	private void writeResponse(String s) throws Throwable {

		Date dateResponse = new Date();
		String serverName = HttpServer.class.getName().toString();

		String response = "HTTP/1.1 200 OK\r\n" 
		        + "Server: " + serverName
		        + "\r\n"
				+ dateResponse + "\r\n"
				+ "Content-Type: text/html\r\n" 
				+ "Content-Length: " + s.length() 
				+ "\r\n"
				+ "Connection: close\r\n\r\n";
		String result = response + s;
		os.write(result.getBytes());
		os.flush();
	}

	private void readInputHeaders() throws Throwable {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while (true) {
			String s = br.readLine();
			if (s == null || s.trim().length() == 0) {
				break;
			}
		}
	}

}
