package com.cloud4all.enviromentalreporter;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class EnviromentalReporterEngine implements SensorEventListener {

	
	// Constructor
			public EnviromentalReporterEngine(Context context) {
		initSensors(context);
	}
	
	// ** Attributes for sensors **
	
	private float _brightness = 0;
	public float getBrightness() {
		return _brightness;
	}
	
	// ** Methods for sensors **

	
	private  SensorManager sensorManager = null;
	public boolean initSensors(Context ct) {
				sensorManager = (SensorManager) ct.getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager == null) return false;
		else {
			initBrightnessSensor();
			return true;
		}
	}
	
	// ** SensorEventListener 
	
	 @Override public void onSensorChanged(SensorEvent event) {
		 if (event.sensor.getType() == Sensor.TYPE_LIGHT){ // brightness sensor 
	_brightness = event.values[0];		 
		 }
			 }
	 
	 @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {   
		 		 	 }
 

// * Brightness *	
	   
	 private  Sensor brightnessSensor = null;  
	 
	 public boolean initBrightnessSensor() {
		 brightnessSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT ); 
		 if (brightnessSensor == null) return false;
		 else {
			 sensorManager.registerListener(this, brightnessSensor, SensorManager.SENSOR_DELAY_FASTEST);
			 			 return true;
		 }
	 }
	
	
}
