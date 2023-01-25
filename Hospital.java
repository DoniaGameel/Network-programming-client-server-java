package Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hospital {
	Doctor doctors[];
	int N;
	Hospital(int n)
	{
		N=n;
		doctors=new Doctor[n];
		File file= new File("E:\\Assignment.txt");
        try {
			Scanner br = new Scanner(file);
			for(int i=0;i<8;i++)
			{
				String Name=br.nextLine();
				int N=Integer.parseInt(br.nextLine());
				doctors[i]=new Doctor(i,Name,N);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public int MakeAppointment(int id,int index,String Patientname)
	{
		if(id>=N || id<0)
			return 2;
		return doctors[id].setAppointment(index, Patientname);
	}
	
	public int CancelAppointment(int id,int index,String Patientname)
	{
		if(id>=N || id<0)
			return 2;
		return doctors[id].DeleteAppointment(index, Patientname);
	}
	
	public void print()
	{
		for(int i=0;i<N;i++)
		{
			doctors[i].printDoctor();
		}
	}

}
