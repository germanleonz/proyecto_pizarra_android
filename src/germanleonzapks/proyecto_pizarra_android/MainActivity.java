package germanleonzapks.proyecto_pizarra_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Button loginButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loginButton = (Button) findViewById(R.id.button_login);
		loginButton.setOnClickListener(new MyButtonClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class MyButtonClickListener implements OnClickListener {
		public void onClick(View v) {
			Intent i;
			switch (v.getId()) {
				case R.id.button_login:
					i = new Intent(MainActivity.this, LoginActivity.class);
					startActivity(i);
					break;
				case R.id.button_sign_in:
					i = new Intent(MainActivity.this, RegistrationActivity.class);
					startActivity(i);
					break;
			}
		}
	}
}
