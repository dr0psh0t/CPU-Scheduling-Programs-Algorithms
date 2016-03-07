package opsys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FCFS
{	
	public static void main(String[] args)
	{
		Scanner con = new Scanner(System.in);
		ArrayList<Process> process = new ArrayList<>();
		int totalProcess, burstTime, arrivalTime;

		System.out.print("\nInput the # of process min 5 max 10: ");
		totalProcess = con.nextInt();

		System.out.println();

		for (int x = 0; x < totalProcess; ++x) {
			System.out.print("Input BT and AT for process " + (x + 1) + ": ");

			burstTime = con.nextInt();
			arrivalTime = con.nextInt();

			process.add(new Process(burstTime, arrivalTime, 0, "p" + (x + 1)));
		}

		Collections.sort(process);

		for(int x = 0; x < totalProcess; ++x)
		{
			// set start time and completion time for each process
			if ((x - 1) < 0)
				process.get(x).setStartTime(process.get(0).getArrivalTime());
			else
				process.get(x).setStartTime(process.get(x - 1).getCompletionTime());

			process.get(x).setCompletionTime(process.get(x).getStartTime() + process.get(x).getBurstTime());

			process.get(x).setWaitingTime(process.get(x).getStartTime() - process.get(x).getArrivalTime());
			process.get(x).setTurnAroundTime(process.get(x).getCompletionTime() - process.get(x).getArrivalTime());
		}
		
		System.out.println("\nFirst Come First Serve");
		GanttChart.printChart(process);
		con.close();
	}
}