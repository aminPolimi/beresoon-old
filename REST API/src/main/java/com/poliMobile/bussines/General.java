package com.poliMobile.bussines;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class General {

	private static final String __JMLOG__ = "/var/log/poliMobile/beresoon.log";
	private static final Boolean __JM_LOGGING__ = true;
	private BufferedWriter bw = null;
	
	protected static SecureRandom random = new SecureRandom();
    
    public synchronized String generateToken() {
            long longToken = Math.abs( random.nextLong() );
            String random = Long.toString( longToken, 16 );
            return random ;
    }
	
	public void br_log(String function_name,String message)
	{
		if (__JM_LOGGING__)
		{
			String df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			String log = df + "\t" + function_name + "\t"+ message + "\n";
			try{
				bw = new BufferedWriter(new FileWriter(new File(__JMLOG__),true));
			}catch(Exception e){			
				
			}
			try{
				bw.write(log);
				bw.flush();
			}catch(Exception e){			
				
			}
		}
	}
}
