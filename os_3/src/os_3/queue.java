package os_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class queue {
	ArrayList<Process> pros = new ArrayList<Process>();
	private int priorty = 0;// 1,2,3
	private String algorithm = "*";// SJF,FCFS,RR
	private int quantum_time = 0;// for round robin only

	public int compareTo(queue q) {
		return q.priorty - this.priorty;
	}

	void set_priorty(int x) {
		if (x == 1 || x == 2 || x == 3)
			priorty = x;
		else
			System.out.println("Please enter value from 1 to 3");
	}

	void set_algo(String x) {
		if (x.equalsIgnoreCase("SJF") || x.equalsIgnoreCase("FCFS") || x.equalsIgnoreCase("RR"))
			algorithm = x;
		else
			System.out.println(
					"You have to use 'SJF' for short job first, 'FCFS' for first come first served and 'RR' for round robin.");
	}

	void set_quantum(int x) {
		if (algorithm.equalsIgnoreCase("RR"))
			quantum_time = x;
		else
			System.out.println("There is no quantum time for " + algorithm + " agorithm.");
	}

	int get_priorty() {
		return priorty;

	}

	String get_algo() {
		return algorithm;
	}

	int get_quantum() {
		return quantum_time;
	}

	public void bubbleSortSJF() {
		int size = pros.size() - 1;
		for (int i = 0; i < pros.size() - 1; i++) {
			for (int j = 0; j < size; j++) {
				if (pros.get(j).duration > pros.get(j + 1).duration) {
					// Swap arrivalTime
					int temp = pros.get(j).duration;
					pros.get(j).duration = pros.get(j + 1).duration;
					pros.get(j + 1).duration = temp;
					// Swap name
					String tep = pros.get(j).process_name;
					pros.get(j).process_name = pros.get(j + 1).process_name;
					pros.get(j + 1).process_name = tep;
					// Swap time
					int tmp = pros.get(j).arrival_time;
					pros.get(j).arrival_time = pros.get(j + 1).arrival_time;
					pros.get(j + 1).arrival_time = tmp;
				}
			}
			size--;
		}
	}

	public void Remove_Process() {
		// if(flag) Print_masseage(true);
		pros.remove(0);
	}

	public void Sort_Processes() {
		Collections.sort(pros, new SortByArrival());
	}

	public void printqueue() {

		for (int i = 0; i < pros.size(); i++) {
			System.out.println("Process " + pros.get(i).process_name + " Arrived at " + pros.get(i).arrival_time
					+ " and it time = " + pros.get(i).duration);
		}
	}

	// public void bubbleSortFCFS() {
	// int size = pros.size()-1;
	// for (int i = 0; i < pros.size() - 1; i++) {
	// for (int j = 0; j < size; j++) {
	// if (pros.get(j).arrival_time > pros.get(j+1).arrival_time) {
	// //Swap arrivalTime
	// int temp = pros.get(j).arrival_time;
	// pros.get(j).arrival_time = pros.get(j + 1).arrival_time;
	// pros.get(j + 1).arrival_time = temp;
	// //Swap name
	// int tep = pros.get(j).process_num;
	// pros.get(j).process_num = pros.get(j+1).process_num;
	// pros.get(j+1).process_num = tep;
	// //Swap time
	// int tmp = pros.get(j).duration;
	// pros.get(j).duration = pros.get(j+1).duration;
	// pros.get(j+1).duration = tmp;
	// }
	// }
	// size--;
	// }
	// }

}

class SortTheQueue implements Comparator<queue> {
	@Override
	public int compare(queue q1, queue q2) {
		return q2.get_priorty() - q1.get_priorty();
	}
}
