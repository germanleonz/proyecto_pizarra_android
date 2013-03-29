package germanleonzapks.proyecto_pizarra_android;

import java.util.HashMap;

import models.SessionManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class HomeActivity extends Activity {

	private TextView mTextView;
	
	private SessionManager sessionManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		// Inicializamos el manejador de sesiones
		sessionManager = new SessionManager(getApplicationContext());
		
		mTextView = (TextView) findViewById(R.id.temporal);
		
		HashMap<String, String> currenta_user_details = sessionManager.getUserDetails();
		mTextView.setText(currenta_user_details.get(SessionManager.KEY_NAME));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public void onBackPressed(){
		new AlertDialog.Builder(this)
			.setMessage(R.string.dialog_confirm_logout)
			.setCancelable(false)
			.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id) {
					sessionManager.logoutUser();
				}
			})
			.setNegativeButton(R.string.no, null)
			.show();
	}
}
