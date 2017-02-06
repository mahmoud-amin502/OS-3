package os_3;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<queue> queues = new ArrayList<queue>();
		int context_switching = 0;// you may need to make it global
		int process_number = 0;// the variable that will give every process it's
								// number. you may need to make it global

		Scanner s1 = new Scanner(System.in);
		int q_num = 0;
		System.out.println("Number of queues: ");
		q_num = s1.nextInt();
		for (int i = 0; i < q_num; i++) {
			queue q_temp = new queue() {
			};
			Scanner s2 = new Scanner(System.in);
			String algo = "";
			int priorty = 0;

			while (true) {
				System.out.println("Enter queue " + (i + 1) + " algorithm: ");
				algo = s2.nextLine();
				q_temp.set_algo(algo);
				if (algo.equalsIgnoreCase("RR")) {
					System.out.println("Enter queue " + (i + 1)
							+ " quantum time: ");
					q_temp.set_quantum(s2.nextInt());
				}
				if (!q_temp.get_algo().equalsIgnoreCase(""))
					break;
			}

			while (true) {
				System.out.println("Enter queue " + (i + 1) + " proirty");
				priorty = s2.nextInt();
				q_temp.set_priorty(priorty);
				if (q_temp.get_priorty() != 0)
					break;
			}

			int p_num = 0;
			System.out.println("Number of processes for queue " + (i + 1)
					+ ": ");
			p_num = s2.nextInt();
			for (int j = 0; j < p_num; j++) {
				process_number++;
				Process p_temp = new Process() {
				};
				Scanner s3 = new Scanner(System.in);
				System.out.println("Enter process " + process_number
						+ " Arrival time: ");
				p_temp.arrival_time = s3.nextInt();
				System.out.println("Enter process " + process_number
						+ " Duration: ");
				p_temp.duration = s3.nextInt();
				p_temp.process_name = "p"+process_number;
				p_temp.queue_num = (i + 1);
				q_temp.pros.add(p_temp);
			}
			queues.add(q_temp);
		}
		System.out.println("Enter context swiching peroid: ");
		context_switching = s1.nextInt();
		s1.close();
		
		Processor pro = new Processor();
		pro.queues = queues;
		pro.Scheuler();
		pro.EventsMonitor();
		pro.Calc_Turnaround_Time(); 
	
	}


	public static void saveTOfile(String file_name)// for H etnatt yasta.
	{

	}

	public static void loadFROMfile(String file_name)// for H also.
	{

	}

}
