package hangtoo.job.stock;

import org.apache.log4j.Logger;


public class Job {
	
	private final static Logger log= Logger.getLogger(Job.class);
	
	
    public void taskCycle(){
    	//每天执行，接口日使用统计，
    	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    	synchronized(Job.class){
    		
			try {
				
		    	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}

    	}
    }

    
}
