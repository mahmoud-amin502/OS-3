package os_3;


import java.util.ArrayList;
import java.util.Collections;

public class Processor {

	ArrayList<Job> output;
	ArrayList<queue> queues;
	int clock = 0;

	public Processor() {
		output = new ArrayList<Job>();
		queues = new ArrayList<queue>();
		clock = 0;
	}

	private void Sort_queues() {
		Collections.sort(queues, new SortTheQueue());
		for (int i = 0; i < queues.size(); i++) {
			if (queues.get(i).get_algo().equalsIgnoreCase("SJF")) {
				queues.get(i).Sort_Processes();
			}
		}
	}

	public void Print() {
		Sort_queues();
		for (int i = 0; i < queues.size(); i++) {
			System.out.println(queues.get(i).get_priorty() + " "
					+ queues.get(i).get_algo());
		}
	}

	private int clock_initiation() {
		Sort_queues();

		int cur_arrival, Priority_arrival, value;

		value = Integer.MAX_VALUE - 1000;
		Priority_arrival = queues.get(0).pros.get(0).arrival_time;
		for (int i = 1; i < queues.size(); i++) {
			cur_arrival = queues.get(i).pros.get(0).arrival_time;
			if (cur_arrival < value) {
				value = cur_arrival;
			}
		}
		
		return min(Priority_arrival, value);
	}

	private int min(int i1, int i2) {
		if(i1 < i2)
			return i1;
		else
			return i2;
	}

	private int next_process() {
		int queue_idx = 0, cur_arrival;
		int Priority_arrive = Integer.MAX_VALUE;
		for (int i = 1; i < queues.size(); i++) {
			cur_arrival = queues.get(i).pros.get(0).arrival_time;
			if (cur_arrival < Priority_arrive) {
				if (clock > cur_arrival)
					continue;
				Priority_arrive = cur_arrival;
				queue_idx = i;
			}
		}
		if (Priority_arrive == Integer.MAX_VALUE)
			return 1;
		return queue_idx;
	}

	public void Scheuler() {

		int cur_arrival, best_candidate, Queue_idx;
		String tmp_type;
		clock = clock_initiation();
		while (!queues.isEmpty()) {

			Sort_queues();
			Queue_idx = 0;
			if (queues.size() > 1)
				Queue_idx = next_process();

			cur_arrival = queues.get(0).pros.get(0).arrival_time;
			best_candidate = queues.get(Queue_idx).pros.get(0)
					.arrival_time;

			if (best_candidate + clock < cur_arrival) {
				tmp_type = queues.get(Queue_idx).get_algo();
				if (tmp_type.equalsIgnoreCase("RR"))
					RR(Queue_idx);
				else
					FCFS(Queue_idx, queues.get(0).pros.get(0)
							.arrival_time, false);
			} else {
				tmp_type = queues.get(0).get_algo();
				if (tmp_type.equalsIgnoreCase("RR"))
					RR(0);
				else
					FCFS(0, queues.get(0).pros.get(0).arrival_time, true);
			}
		}
	}

	private void FCFS(int queue_idx, int arrive, boolean flag) {
		Job job = new Job();
		queue q_temp = queues.get(queue_idx);
		if (flag == false) {
			job.finish = q_temp.pros.get(0).arrival_time;
			if (q_temp.pros.get(0).duration + clock <= arrive) {
				job.start = clock;
				clock += (q_temp.pros.get(0).duration + 1);

				job.finish = clock;
				job.job_name = q_temp.pros.get(0).process_name;
				job.state_Types(true);
				q_temp.Remove_Process();
				remove_empty_queue(queue_idx);
			} else {
				job.start = clock;
				// q.Print_masseage(false);
				q_temp.pros.get(0).duration = q_temp.pros.get(0).duration + clock - arrive;
				clock = arrive + 1;
				job.finish = arrive;
				job.job_name = q_temp.pros.get(0).process_name;
				job.state_Types(false);
			}
		} else {
//			job.setArrive(queues.get(0).pros.get(0).arrival_time);
			job.arrive = queues.get(0).pros.get(0).arrival_time;
			job.start = clock;
			clock += (queues.get(0).pros.get(0).duration + 1);
			job.finish = clock;
			job.job_name = queues.get(0).pros.get(0).process_name;
			job.state_Types(true);
			queues.get(0).Remove_Process();
			remove_empty_queue(0);
		}
		output.add(job);
	}

	private void RR(int queue_idx) {
		Job job = new Job();
		queue q_temp = queues.get(queue_idx);
		int var;
		Process p_temp = q_temp.pros.get(0);
		var = min(p_temp.duration, q_temp.get_quantum());
		job.start = clock;
		clock += (var + 1);
		job.finish = clock;
		job.arrive = p_temp.arrival_time;
		p_temp.duration = p_temp.duration - q_temp.get_quantum();
		if (p_temp.duration <= 0) {
			job.job_name = q_temp.pros.get(0).process_name;
			job.state_Types(true);
			q_temp.Remove_Process();
		} else {
			// q.Print_masseage(false);
			job.job_name = q_temp.pros.get(0).process_name;
			job.state_Types(false);
			q_temp.Remove_Process();
			q_temp.pros.add(p_temp);
		}
		remove_empty_queue(queue_idx);
		output.add(job);
	}

	private void remove_empty_queue(int idx) {
		if (queues.get(idx).pros.size() == 0) {
			queues.remove(idx);
		}
	}

	public void EventsMonitor() {
		for (int i = 0; i < output.size(); i++)
			output.get(i).show();
		System.out.println("--------------------------------");
	}

	public void Calc_Turnaround_Time() {
		int Pro_time = 0, Total_Time = 0;
		Job job = new Job();
		String Pro_name;

		for (int i = 0; i < output.size(); i++) {
			if (output.get(i).job_name.equals("--"))
				continue;
			Pro_time = 0;
			job = output.get(i);
			Pro_name = output.get(i).job_name;
			for (int j = 0; j < output.size(); j++) {
				if (i != j) {
					job = output.get(j);
					if (job.job_name.equals(Pro_name)) {
						Pro_time += (job.start - job.arrive);
						job.arrive = job.start;
						output.get(j).job_name = "--";
					}
				}
			}
			Total_Time += Pro_time;
			System.out.println("Process " + Pro_name + " Time in System is "
					+ Pro_time);
		}
		System.out.println("Total TurnAround Time = " + Total_Time);
	}
}
