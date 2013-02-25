package com.cloud4all.enviromentalreporter;

/*

EnviromentalReporterEngine
This class explores sensors to get enviromental data of the device

Copyright (c) 2013, Technosite R&D
All rights reserved.
The research leading to these results has received funding from the 

European Union's Seventh Framework Programme (FP7/2007-2013) under 

grant agreement n° 289016

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are 

met:

 * Redistributions of source code must retain the above copyright 

notice, this
   list of conditions and the following disclaimer. 
 * Redistributions in binary form must reproduce the above copyright 

notice, 
   this list of conditions and the following disclaimer in the 

documentation 
   and/or other materials provided with the distribution. 
 * Neither the name of Technosite R&D nor the names of its 

contributors may 
   be used to endorse or promote products derived from this software 

without 
   specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 

"AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 

THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 

PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 

LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 

CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 

GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 

HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 

LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF 

THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


*/

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
