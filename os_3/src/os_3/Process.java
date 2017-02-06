package os_3;

import java.util.Comparator;

abstract public class Process {
	
	int arrival_time = 0;
	int duration = 0;
	int queue_num = 0;
	String  process_name = "#";
}
class SortByArrival implements Comparator<Process>{
    @Override
    public int compare(Process p1 , Process p2){
        return p1.arrival_time - p2.arrival_time ; 
}
}