package opsys;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * Dagondon
 */

public class Preemptive
{
	public static void main(String[] args)
	{
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
		
		ArrayList<Process> tempList;
		LinkedList<Process> ganttList = new LinkedList<>();
		
		ganttList.add(process.get(0));
		ganttList.get(0).setStartTime(process.get(0).getArrivalTime());
		ganttList.get(0).setCompletionTime(ganttList.get(0).getStartTime() + 1);
		
		int total = process.size();
		
		process.get(0).decrementBT();
		
		if(process.get(0).getBurstTime() < 1)
		{
			process.get(0).setDoneTrue();
			--total;
		}
		
		int range = ganttList.get(0).getCompletionTime();
		
		while(total > 0)
		{
			tempList = GanttChart.getProcessesAtRange(range, process);				// step 1: get list at specified range
			
			Process p1 = GanttChart.getLowestProcess(tempList);					// step 2: get the lowest runtime from a list from step 1
			
			Process p2 = new Process(p1);								// step 2.1: create a new process objecct to avoid mutation of elements from ganttList
			
			int index = process.indexOf(p1);							// step 3: get the index of the acquired lowest runtime process from step 2
			
			p2.setStartTime(ganttList.getLast().getCompletionTime());	// step 4: set the start time of the acquired lowest runtime process using the completion time
																		//			of the last element in ganttList
			
			p2.setCompletionTime(p2.getStartTime() + 1);				// step 5: set the completion time of the acquired lowest runtime process using the
																		//			start time + 1 of the that same process
			
			process.get(index).decrementBT();							// step 6: decrement the burst time by 1
			
			ganttList.add(p2);											// step 7: add the acquired lowest runtime process to ganttList
			
			range = ganttList.getLast().getCompletionTime();			// step 8: set the new range using the completion time of the last element in ganttList
																		//			(the completion time of acquired lowest runtime process)
			
			tempList.clear();											// step 9: clear the tempList
			
			if(process.get(index).getBurstTime() < 1)					//
			{
				process.get(index).setDoneTrue();
				--total;
			}
		}
		
		GanttChart.printChart(new ArrayList<>(ganttList));
		con.close();
	}
}