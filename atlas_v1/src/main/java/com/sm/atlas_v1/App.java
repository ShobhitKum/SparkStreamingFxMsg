package com.sm.atlas_v1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.sm.atlas_v1.core.IStreamingService;
import com.sm.atlas_v1.core.StreamingServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
	final static Logger logger = Logger.getLogger(App.class);

static{
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }
	
    public static void main( String[] args )
    {
    	logger.info( "Starting Streamig services" );
    	IStreamingService service = new StreamingServiceImpl();
    	service.start();
    }
}
