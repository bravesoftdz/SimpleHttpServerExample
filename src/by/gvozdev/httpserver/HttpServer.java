package by.gvozdev.httpserver;

import java.net.ServerSocket;
import java.net.Socket;

import by.gvozdev.httpserver.constant.HttpConstants;

/**
 * @author Gvozdev Alexsander Apr 26, 2018
 */
public class HttpServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Throwable {

		EventMessage eventMessage = new EventMessage();

		ServerSocket ss = new ServerSocket(HttpConstants.HTTP_PORT);
		while (true) {
			Socket s = ss.accept();

			eventMessage.getMessageServer("Client has been accepted");

			new Thread(new SocketProcessor(s)).start();
		}
	}

}
