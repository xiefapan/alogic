package com.alogic.doer.demo;

import java.util.HashMap;
import java.util.Map;

import com.alogic.doer.client.TaskSubmitter;
import com.alogic.doer.core.TaskReport.TaskState;
import com.anysoft.util.Settings;

public class Demo {

	public static void main(String[] args) {
		Settings settings = Settings.get();
		settings.SetValue("doer.master","java:///com/alogic/doer/demo/doer.demo.xml#com.alogic.doer.demo.Demo");
		
		Map<String,String> parameters = new HashMap<String,String>();
		
		parameters.put("name", "yyduan");
		
		TaskSubmitter.submit("job", "demo", parameters);
		
		while (true){
			
			TaskState state = TaskSubmitter.getTaskReport("job", "demo").state();
			
			if (state == TaskState.Done || state == TaskState.Failed){
				break;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
