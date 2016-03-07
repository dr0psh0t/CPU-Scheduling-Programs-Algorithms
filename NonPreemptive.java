package opsys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class NonPreemptive {

	public static void main(String[] args) {
		
		Scanner con = new Scanner(System.in);
		ArrayList<Process> process = new ArrayList<>();
		
		int totalProcess, burstTime, arrivalTime;
		
		System.out.print("Input the # of process min 5 max 10: ");
		totalProcess = con.nextInt();

		System.out.println();

		for (int x = 0; x < totalProcess; ++x) {
			System.out.print("Input BT and AT for process " + (x + 1) + ": ");

			burstTime = con.nextInt();
			arrivalTime = con.nextInt();

			process.add(new Process(burstTime, arrivalTime, 0, "p" + (x + 1)));
		}
		
		System.out.println("\nProcess\t\tBT\tAT");
		System.out.println("---------------------------");
		
		for(Process p : process)
			System.out.println(p);

		Collections.sort(process, new RuntimeComparator());
		
		LinkedList<Process> ganttList = new LinkedList<>();
		ArrayList<Process> tempList;
		
		process.get(0).setStartTime(process.get(0).getArrivalTime());
		process.get(0).setCompletionTime(process.get(0).getBurstTime());
		process.get(0).setDoneTrue();
		
		ganttList.add(process.get(0));
		
		int range = process.get(0).getCompletionTime();
		int total = process.size() - 1;
		
		while(total > 0)
		{
			tempList = GanttChart.getProcessesAtRange(range, process);
			
			Process p1;
			
			if(tempList.size() != 0)
			{
				p1 = GanttChart.getLowestProcess(tempList);
				
				p1.setStartTime(ganttList.getLast().getCompletionTime());
				
				range += p1.getBurstTime();
				
				p1.setCompletionTime(range);
				
				p1.setDoneTrue();
				
				ganttList.add(p1);
				
				tempList.clear();
				--total;
			}
			else
				range += 1;
		}
		
		GanttChart.printChart(new ArrayList<>(ganttList));
		con.close();
	}
}