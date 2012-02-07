package org.openschedule.activities;

import org.openschedule.MainApplication;

/**
 * @author dmfrey
 */
public interface OpenScheduleActivity {
	
	MainApplication getApplicationContext(); 
	
	void showProgressDialog();
	
	void showProgressDialog( String message );
	
	void dismissProgressDialog();

}
