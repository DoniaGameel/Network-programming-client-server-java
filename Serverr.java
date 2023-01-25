package Assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class Serverr {
	public static final int MakeAppointments = 6666;
	public static final int CancelAppointments = 6667;
	static Hospital hospital;
	public static int clientNumber = 0;
	public static void main(String[] args) throws IOException
	{
		int N=8;
		hospital=new Hospital(N);
		new Thread() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(MakeAppointments);
					while (true) {
						new Appointment(ss.accept(), clientNumber++).start();
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

		new Thread() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(CancelAppointments);
					while (true) {
						new Appointment(ss.accept(), clientNumber++).start();
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	private static class Appointment extends Thread
	{
		Socket socket;
		int clientNo;
		String Name;
		public Appointment(Socket s,int clientNo) throws IOException {
			this.socket = s;
			this.clientNo = clientNo;
			BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			Name=br.readLine();
		}
		
		public void run() 
		{
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
				while (true) 
				{
					
					if(Name==null)
						break;
					int id =  Integer.parseInt(br.readLine());
					int index=  Integer.parseInt(br.readLine());
					int result;
					
					if (this.socket.getLocalPort() == MakeAppointments)
					{
						String message;
						result=hospital.MakeAppointment(id, index,Name);
						if(result==1)
						{
							message="Making the appointment is done successfully (Success)";
						}
						else if(result==2)
						{
							message="the doctor id is not found in hospital (Failure)";
						}
						else if(result==3)
						{
							message="the timeslot index is out of boundary (Failure)";
						}
						else
						{
							message="the doctor is already busy at this timeslot (Failure)";
						}
						out.println(message);
						hospital.print();
					}
					else
					{
						String message;
						result=hospital.CancelAppointment(id, index,Name);
						if(result==1)
						{
							message="Cancelling the appointment is done successfully (Success)";
						}
						else if(result==2)
						{
							message="the doctor id is not found in hospital (Failure)";
						}
						else if(result==3)
						{
							message="the timeslot index is out of boundary (Failure)";
						}
						else if(result==4)
						{
							message="the doctor doesn’t have an appointment at this timeslot (Failure)";
						}
						else
						{
							message="the doctor has an appointment to a different patient name at this timeslot (Failure)";
						}
						out.println(message);
						hospital.print();
					}
					
					
				}
				out.close();
				br.close();
				this.socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}



