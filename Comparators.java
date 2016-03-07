/*
 * Dagondon
 * Comparators.java
 */
package opsys;

import java.util.Comparator;

class RuntimeComparator implements Comparator<Process>
{
	@Override
	public int compare(Process o1, Process o2)
	{
		int arrivalResult = o1.compareTo(o2);
		
		if(arrivalResult != 0)
			return arrivalResult;
		
		return o1.compareBurstTime(o2.getBurstTime());
	}
}

class PriorityComparator implements Comparator<Process>
{
	@Override
	public int compare(Process o1, Process o2){
		return o1.comparePriority(o2.getPriority());
	}
}