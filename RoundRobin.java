package opsys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class RoundRobin
{
	public static void main(String[] args)
	{
		Scanner con = new Scanner(System.in);
		ArrayList<Process> process = new ArrayList<>();
		
		int totalProcess, burstTime, arrivalTime, timeQuantity;
		
		System.out.print("Input the # of process min 5 max 10: ");
		totalProcess = con.nextInt();

		System.out.println();

		for (int x = 0; x < totalProcess; ++x)
		{
			System.out.print("Input BT and AT for process " + (x + 1) + ": ");

			burstTime = con.nextInt();
			arrivalTime = con.nextInt();

			process.add(new Process(burstTime, arrivalTime, 0, "p" + (x + 1)));
		}
		
		System.out.print("\nEnter time quantity: ");
		timeQuantity = con.nextInt();
		
		/*System.out.println("\nProcess\t\tBT\tAT");
		System.out.println("---------------------------");
		
		for(Process p : process)
			System.out.println(p);*/
		
		Collections.sort(process);

		LinkedList<Process> ganttList = new LinkedList<>();
		LinkedList<Process> requestQueue = new LinkedList<>();
		
		ganttList.add(process.get(0));
		ganttList.get(0).setStartTime(process.get(0).getArrivalTime());
		ganttList.get(0).setCompletionTime(ganttList.get(0).getStartTime() + timeQuantity);
		
		int total = process.size();
		int range = ganttList.get(0).getCompletionTime();
		
		process.get(0).decrementBT(timeQuantity);
		
		if(process.get(0).getBurstTime() < 1)
		{
			process.get(0).setDoneTrue();
			--total;
		}
		
		requestQueue.addAll(GanttChart.getProcessesAtRange(range, process));
		
		if(!process.get(0).getDone())
		{
			if(!requestQueue.isEmpty())
				requestQueue.remove(process.get(0));
			
			requestQueue.add(process.get(0));
		}
		
		for(Process p : requestQueue)
			System.out.print(p.getProcessName()+" ");
		System.out.println("---");
		
		while(total > 0)
		{
			Process p1 = requestQueue.pollFirst();
			
			Process p2 = new Process(p1);
			
			int index = process.indexOf(p1);
			
			p2.setStartTime(ganttList.getLast().getCompletionTime());
			
			if(p1.getBurstTime() >= timeQuantity)
				p2.setCompletionTime(p2.getStartTime() + timeQuantity);
			else
				p2.setCompletionTime(p2.getStartTime() + p2.getBurstTime());
			
			process.get(index).decrementBT(timeQuantity);
			
			ganttList.add(p2);
			
			range = ganttList.getLast().getCompletionTime();
			
			if(process.get(index).getBurstTime() < 1)
			{
				process.get(index).setDoneTrue();
				--total;
			}
			
			requestQueue.addAll(GanttChart.getProcessesAtRange(range, process, requestQueue));
			
			if(!process.get(index).getDone())
			{
				requestQueue.remove(process.get(index));
				requestQueue.add(process.get(index));
			}
			
			for(Process p : requestQueue)
				System.out.print(p.getProcessName()+" ");
			System.out.println();
		}
		
		GanttChart.printChart(new ArrayList<>(ganttList));
		System.out.println();
		
		for(Process p : requestQueue)
			System.out.print(p.getProcessName()+" ");
		
		con.close();
	}	
}