package com.sm.atlas_v1.core;

import org.apache.spark.streaming.api.java.JavaStreamingContext;

public interface IStreamingService {

	public void start();
	public void stop();
	public void init();
	public JavaStreamingContext getContext();
}
