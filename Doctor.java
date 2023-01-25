package Assignment;

public class Doctor {
	int id;
	String name;
	int n;
	boolean[] timeslots;
	String[] patients;
	
	public Doctor(int ID, String Name,int N) 
	{
		n=N;
		timeslots=new boolean[N];
		patients=new String[N];
		id=ID;
		name=Name;
	}
	public int setAppointment(int index,String name) 
	{
		if(index>=n || index<0)
			return 3;
		if(timeslots[index]==false)
		{
			timeslots[index]=true;
			patients[index]=name;
			return 1;
		}
		return 4;
	}
	
	public int DeleteAppointment(int index,String name) 
	{
		if(index>=n || index<0)
			return 3;
		if(timeslots[index]==true )
		{
			if(patients[index].equals(name))
			{
			timeslots[index]=false;
			patients[index]=null;;
			return 1;
			}
			else
				return 5;
		}
		return 4;
	}
	public void printDoctor()
	{
		System.out.println("Doctors's ID : "+id );
		System.out.println("Doctors's patients : ");
		for(int i=0;i<n ;i++)
		{
			if(timeslots[i]==true)
			{
				System.out.println("Patient : "+patients[i] +" At time slot : "+i);
			}
		}
		System.out.println();
	}

}
