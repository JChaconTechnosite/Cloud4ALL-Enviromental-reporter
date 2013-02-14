package com.cloud4all.enviromentalreporter;
/*

EnviromentalReporterService
This class is the interface for the Enviromental reporter connnection service.
Use binding connection service to use it.	

Developed by Technosite.
This resource is under New BSD license.
Please, use this license for distribution.

 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class EnviromentalReporterService extends Service{
	
	// ** Service management
	
	private final IBinder mBinder = new MyBinder();
	private EnviromentalReporterEngine enviromentalReporterEngine = null;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	public class MyBinder extends Binder {
		EnviromentalReporterService getService() {
			return EnviromentalReporterService.this;
		}
	}

	// ** Method for engine
	
	public EnviromentalReporterEngine getEngine() {
		enviromentalReporterEngine = new EnviromentalReporterEngine(getApplicationContext());
		return enviromentalReporterEngine ; 
	}
	
	// ** Methods for results

	// method for getting data from the Enviromental reporter
	public String getResults(int typeOfData) {
		enviromentalReporterEngine = new EnviromentalReporterEngine(getApplicationContext());
		String results = null;
		switch (typeOfData) {
		case EnviromentalReporter.TYPE_BRIGHTNESS :
			results = String.valueOf(enviromentalReporterEngine.getBrightness());
			break;
				}
		return results;
	}

	// method for getting data from the Enviromental reporter using a custom context
	public String getResultsWithContext(Context context, int typeOfData) {
		enviromentalReporterEngine = new EnviromentalReporterEngine(context);
		String results = null;
		switch (typeOfData) {
		case EnviromentalReporter.TYPE_BRIGHTNESS :
			results = String.valueOf(enviromentalReporterEngine.getBrightness());
			break;
				}
		return results;
	}

}
