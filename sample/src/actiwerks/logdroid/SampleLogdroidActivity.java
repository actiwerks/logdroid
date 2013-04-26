package actiwerks.logdroid;

import objectforms.utils.prn;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SampleLogdroidActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prn.log("onCreateCalled from SampleLogdroidActivity");
		setContentView(R.layout.activity_sample_logdroid);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sample_logdroid, menu);
		return true;
	}

}
