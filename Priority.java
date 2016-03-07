package opsys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Priority
{
	static Scanner con = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		ArrayList<Process> process = new ArrayList<>();
		
		int totalProcess, burstTime, priority;
		
		System.out.print("Input the # of process min 5 max 10: ");
		totalProcess = con.nextInt();

		System.out.println();
		
		for (int x = 0; x < totalProcess; ++x) {
			System.out.print("Input BT and Priority for process " + (x + 1) + ": ");

			burstTime = con.nextInt();
			priority = con.nextInt();

			process.add(new Process(burstTime, 0, priority, "p" + (x + 1)));
		}
		
		System.out.println("\nProcess\t\tBT\tPriority");
		System.out.println("---------------------------------");
		
		for(Process p : process)
			System.out.println(p.get());
		
		Collections.sort(process, new PriorityComparator());
		
		for(int x = 0; x < totalProcess; ++x)
		{
			// set start time and completion time for each process
			if((x - 1) < 0)
				process.get(x).setStartTime(process.get(0).getArrivalTime());
			else
				process.get(x).setStartTime(process.get(x - 1).getCompletionTime());

			process.get(x).setCompletionTime(process.get(x).getStartTime() + process.get(x).getBurstTime());

			process.get(x).setWaitingTime(process.get(x).getStartTime() - process.get(x).getArrivalTime());
			process.get(x).setTurnAroundTime(process.get(x).getCompletionTime() - process.get(x).getArrivalTime());
		}
		
		GanttChart.printChart(process);
		con.close();
	}
}