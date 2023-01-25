package Assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
	public static final int MakeAppointments = 6666;
	public static final int CancelAppointments = 6667;
	
	public static void main(String[] args) throws IOException {
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		Socket makeSocket = new Socket("localhost", MakeAppointments);
		Socket CancelSocket = new Socket("localhost", CancelAppointments);
		PrintWriter outMake = new PrintWriter(makeSocket.getOutputStream(), true);
		PrintWriter outCancel = new PrintWriter(CancelSocket.getOutputStream(), true);
		BufferedReader socketReaderMake = new BufferedReader(new InputStreamReader(makeSocket.getInputStream()));
		BufferedReader socketReaderCancel = new BufferedReader(new InputStreamReader(CancelSocket.getInputStream()));
		System.out.println("Enter Your Name:");
		String Name = consoleReader.readLine();
		outMake.println(Name); //send message to server
		outCancel.println(Name); //send message to server
		while(true)
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter 1 to make appointment and 2 to cancel appointment");
			int choice =scanner.nextInt();
			System.out.println("Enter the doctor id");
			int id =scanner.nextInt();
			System.out.println("Enter the time slot index");
			int index =scanner.nextInt();
			if(choice==0)
				break;
			if(choice == 1 ) 
			{
				outMake.println(id);
				outMake.println(index);
				System.out.println(socketReaderMake.readLine());
			}
			else
			{
				outCancel.println(id);
				outCancel.println(index);
				System.out.println(socketReaderCancel.readLine()); 
			}
			
		}
		consoleReader.close();
		outMake.close();
		outCancel.close();
		consoleReader.close();
		makeSocket.close();
		CancelSocket.close();
		socketReaderMake.close();
		socketReaderCancel.close();
	}
	

}
