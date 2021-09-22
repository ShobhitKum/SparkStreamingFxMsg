package com.sm.receiever;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;
import org.apache.spark.SparkEnv;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;
import org.spark_project.guava.io.Closeables;

import com.sm.atlas_v1.core.StreamingServiceImpl;

public class SocketReceiver extends Receiver<String>  {
	final static Logger logger = Logger.getLogger(StreamingServiceImpl.class);

	private String host;
	private int port;

	public SocketReceiver(StorageLevel storageLevel) {
		super(storageLevel);
	}
	
	public SocketReceiver(StorageLevel st,String host,int port) {
		super(st);
		this.host=host;
		this.port=port;
	}

	@Override
	public void onStart() {
		new Thread(this::receive).start();
		
	}

	@Override
	public void onStop() {
		System.out.println("Stop called");
		
	}
	
	
	 /** Create a socket connection and receive data until receiver is stopped */
	  private void receive() {
	    try {
	      Socket socket = null;
	      BufferedReader reader = null;
	      try {
	        // connect to the server
	        socket = new Socket(host, port);
	        reader = new BufferedReader(
	            new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
	        // Until stopped or connection broken continue reading
	        String userInput;
	        while (!isStopped() && (userInput = reader.readLine()) != null) {
	          System.out.println("Received data '" + userInput + "'");
				logger.info("ExecutorID kushob RECEIVER ID--->"+SparkEnv.get().executorId());

	          store(userInput);
	        }
	      } finally {
	        Closeables.close(reader, /* swallowIOException = */ true);
	        Closeables.close(socket,  /* swallowIOException = */ true);
	      }
	      // Restart in an attempt to connect again when server is active again
	      restart("Trying to connect again");
	    } catch(ConnectException ce) {
	      // restart if could not connect to server
	      restart("Could not connect", ce);
	    } catch(Throwable t) {
	      restart("Error receiving data", t);
	    }
	  }


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}
	  
	  
	  
	}