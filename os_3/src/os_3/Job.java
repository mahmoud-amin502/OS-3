package os_3;

public class Job {
	
	String job_name;
    int start;
    int finish;
    String state; 
    int arrive; 
    
    public Job() {
        job_name = " ";
        start = 0;
        finish = 0;
        state = " " ; 
    }
   
    public void show() {
        System.out.println("Process "+ this.job_name + " Enterd At " + this.start + " and " + this.state + this.finish );
    }
    
    public void state_Types(boolean value){
        String s1 = " out of the queue at " ; 
        String s2 = " Still  waiting at " ;
        if( value ) this.state = s1;
        else this.state = s2; 
    }

}
