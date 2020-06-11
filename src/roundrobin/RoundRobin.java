package roundrobin;

/**
 * @author Savvas Theofilou
 */
public class RoundRobin 
{ 
	// Method that calculates the waiting time for all processes 
	static int[] calcWaitingTime(int burstTime[], int quantum) 
	{
            int numberOfElements=burstTime.length;
            int remainingBurstTime[]=burstTime.clone();
            int waitingTime[]=new int[numberOfElements];
            for (int i=0;i<numberOfElements;i++){
                waitingTime[i]=0;
            }
            int timePassed=0;
            boolean done;
            
            do{
                done=true;
                //Checks if all processes are done
                for (int i=0;i<numberOfElements;i++){
                    if (remainingBurstTime[i]>0){
                        done=false;
                        break;
                    }
                }
                for (int i=0;i<numberOfElements;i++){
                    if (remainingBurstTime[i]!=0){
                        if (remainingBurstTime[i]>quantum){
                            timePassed+=quantum;
                            remainingBurstTime[i]-=quantum;
                        }
                        else{
                            timePassed+=remainingBurstTime[i];
                            waitingTime[i]=timePassed-burstTime[i];
                            remainingBurstTime[i]=0;
                        }
                    }   
                }
            }
            while (!done);
            return waitingTime;
	} 
	
	// Method that calculates turn around time for all processes
	static int[] calcTurnAroundTime(int burstTime[], int waitingTime[]) 
	{
            int numberOfElements=burstTime.length;
            int turnAroundTime[]=new int[numberOfElements];
            for (int i=0;i<numberOfElements;i++){
                turnAroundTime[i]=burstTime[i]+waitingTime[i];
            }
            return turnAroundTime;
	} 
	
	// Method that prints the results and calculates the average waiting and
	// turnaround times
	static void printAvgTimes(int burstTime[], int quantum) 
	{
		int n = burstTime.length;
		int totalWaitingTime = 0;
		int totalTurnAroundTime = 0; 
	
		// Find waiting time of all processes 
		int[] waitingTime = calcWaitingTime(burstTime, quantum); 
	
		// Find turn around time for all processes 
		int[] turnAroundTime = calcTurnAroundTime(burstTime, waitingTime); 
	
		// Display processes along with all details 
		System.out.println("Process " + " Burst Time " + 
					" Waiting Time " + " Turnaround Time"); 
		System.out.println("=======  ==========  ============  ===============");
		// Calculate total waiting time and total turn 
		// around time 
		for (int i = 0; i < n; i++) { 
			totalWaitingTime += waitingTime[i]; 
			totalTurnAroundTime += turnAroundTime[i]; 
			System.out.println(i + "\t\t" + burstTime[i] +"\t " + 
							waitingTime[i] +"\t\t " + turnAroundTime[i]); 
		} 
	
		System.out.println("\nAverage waiting time = " + 
						(float)totalWaitingTime / (float)n); 
		System.out.println("Average turnaround time = " + 
						(float)totalTurnAroundTime / (float)n); 
	} 
	
	// Driver Method to test your algorithm with a simple example
	public static void main(String[] args) 
	{
		// Burst time of processes. The array index is the process ID.
		int burstTime[] = {5, 15, 4, 3}; 

		// Time quantum 
		int quantum = 3;
		
		printAvgTimes(burstTime, quantum); 
	} 
} 
