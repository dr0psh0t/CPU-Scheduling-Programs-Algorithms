/*
 * Dagondon
 * GanttChart.java
 */
package opsys;

import java.util.ArrayList;
import java.util.LinkedList;

public class GanttChart
{
	public static Process getLowestProcess(ArrayList<Process> list)
	{
		Process lowP = list.get(0);
		
		if(list.size() == 1)
			return lowP;
		
		for(int x = 1; x < list.size(); ++x)
		{
			if(list.get(x).getBurstTime() < lowP.getBurstTime())
				lowP = list.get(x);
		}
		
		return lowP;
	}
	
	public static ArrayList<Process> getProcessesAtRange(int range, ArrayList<Process> process)
	{
		ArrayList<Process> list = new ArrayList<>();
		
		for(Process p : process)
		{
			if(p.getArrivalTime() <= range)
			{
				if(!p.getDone())
					list.add(p);
			}
		}
		
		return list;
	}
	
	public static ArrayList<Process> getProcessesAtRange(int range, ArrayList<Process> process, LinkedList<Process> requestQueue)
	{
		ArrayList<Process> list = new ArrayList<>();
		
		for(Process p : process)
		{
			if(p.getArrivalTime() <= range)
			{
				if(!requestQueue.contains(p))
				{
					if(!p.getDone())
						list.add(p);
				}
			}
		}
		
		return list;
	}
	
	public static void printChart(ArrayList<Process> process)
	{
		int size = process.size();
		
		for(int x = 1; x <= size; ++x)
		{
			System.out.print("+----");
			
			if(x == size)
				System.out.print("+");
		}
		
		System.out.println();
		
		for(int y = 0; y < size; ++y)	// print processes
		{
			System.out.printf("| %s ", process.get(y).getProcessName());
			
			if((y+1) == size)
				System.out.print("|");
		}
		
		System.out.println();
		
		for(int x = 1; x <= size; ++x)
		{
			System.out.print("+----");
			
			if(x == size)
				System.out.print("+");
		}
		
		System.out.println();
		
		for(int x = 0; x < size; ++x)	// print start and completion time
		{
			Process process2 = process.get(x);
			int startTime = process2.getStartTime();
			
			if(startTime > 9)
				System.out.print(process2.getStartTime()+"   ");
			else
				System.out.print(process2.getStartTime()+"    ");
			
			if(x == (size - 1))
				System.out.print(process2.getCompletionTime());
		}
	}
}