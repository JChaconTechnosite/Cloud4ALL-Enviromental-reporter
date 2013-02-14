package com.cloud4all.enviromentalreporter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Viewer extends Activity {

	private TextView textResults = null;
	private Button btnRefresh= null;
	private EnviromentalReporterEngine reporter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewer);
		textResults = (TextView) findViewById(R.id.txtResults);
		btnRefresh = (Button) findViewById(R.id.btnRefresh);
	btnRefresh.setEnabled(false);
		doBindService(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_viewer, menu);
		return true;
	}

	// ** Interface
	
	
	public void refreshData(View view) {
		textResults.setText("Enviromental reporter\n\nBrightness: "+ sEnviromentalReporter.getResults(EnviromentalReporter.TYPE_BRIGHTNESS)); 		
	}
	
	// ** Service connection

		private EnviromentalReporterService sEnviromentalReporter;

		private ServiceConnection mConnection = new ServiceConnection() {

			public void onServiceConnected(ComponentName className, IBinder binder) {
				sEnviromentalReporter = ((EnviromentalReporterService.MyBinder) binder).getService();
				if (sEnviromentalReporter != null) {
					reporter = sEnviromentalReporter.getEngine();
					btnRefresh.setEnabled(true);
					textResults.setText("Service connected. Please, touch the refresh button."); 
				}
			}

			public void onServiceDisconnected(ComponentName className) {
				sEnviromentalReporter = null;
				btnRefresh.setEnabled(false);
			}
		};

		// starts the service connection using bindings
		void doBindService() {
			bindService(new Intent(this, EnviromentalReporterService.class), mConnection,
					Context.BIND_AUTO_CREATE);
		}

	
	
}
