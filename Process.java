/*
 * Dagondon
 * Process.java
 */
package opsys;

public class Process implements Comparable<Process>
{
	private int burstTime;
	private int arrivalTime;
	private int startTime;
	private int completionTime;
	private int waitingTime;
	private int turnAroundTime;
	private int priority;
	
	private boolean done;
	private String processName;
	
	public Process(int burstTime, int arrivalTime, int priority, String processName)
	{
		this.burstTime = burstTime;
		this.arrivalTime = arrivalTime;
		this.processName = processName;
		this.priority = priority;
		done = false;
	}
	
	public Process(Process p)
	{
		this.burstTime = p.burstTime;
		this.arrivalTime = p.arrivalTime;
		this.processName = p.processName;
		this.priority = p.priority;
		done = p.done;
	}
	
	public void setSTAndCTtoZero()
	{
		startTime = 0;
		completionTime = 0;
	}
	
	public void decrementBT(){
		--burstTime;
	}
	
	public void decrementBT(int timeQuantity){
		burstTime = burstTime - timeQuantity;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public boolean getDone(){
		return done;
	}
	
	public void setDoneTrue(){
		done = true;
	}
	
	public String getProcessName(){
		return processName;
	}
	
	public int getBurstTime(){
		return burstTime;
	}
	
	public int getArrivalTime(){
		return arrivalTime;
	}
	
	public void setCompletionTime(int completionTime){
		this.completionTime = completionTime;
	}
	
	public void setStartTime(int startTime){
		this.startTime = startTime;
	}
	
	public int getCompletionTime(){
		return completionTime;
	}
	
	public int getStartTime(){
		return startTime;
	}
	
	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}
	
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	public int getTurnAroundTime() {
		return turnAroundTime;
	}
	
	public int getWaitingTime() {
		return waitingTime;
	}
	
	public boolean isArrivalTimeEqual(Process process){
		return arrivalTime == process.getArrivalTime();
	}
	
	public String get(){
		return processName+"\t\t"+burstTime+"\t"+priority;
	}
	
	@Override
	public String toString(){
		//return processName+"\t\t"+burstTime+"\t"+arrivalTime+"\t"+waitingTime+"\t"+turnAroundTime;
		return processName+"\t\t"+burstTime+"\t"+arrivalTime;
	}
	
	public void print(){
		System.out.println(processName+"\t"+burstTime+"\t"+arrivalTime);
	}

	@Override
	public int compareTo(Process o){
		return this.arrivalTime - o.arrivalTime;
	}
	
	public int compareBurstTime(int burstTime){
		return this.burstTime - burstTime;
	}
	
	public int comparePriority(int priority){
		return this.priority - priority;
	}
}