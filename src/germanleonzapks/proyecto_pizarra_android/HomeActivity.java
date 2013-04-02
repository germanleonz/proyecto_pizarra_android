package germanleonzapks.proyecto_pizarra_android;

import java.util.ArrayList;
import java.util.HashMap;

import models.Pizarra;
import models.SessionManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import bd.DAOFactory;
import bd.PizarraDAO;

public class HomeActivity extends Activity {

	private static final String TAG = "HomeActivity";
	
	private TextView mTextView;
	
	private SessionManager sessionManager;
	
	private String mNombreUsuario;
	private ArrayList<Pizarra> mPizarrasUsuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		// Inicializamos el manejador de sesiones
		sessionManager = new SessionManager(getApplicationContext());
		sessionManager.checkLogin();
		
		mTextView = (TextView) findViewById(R.id.temporal);
		
		//	Conseguimos datos del usuario que esta conectado
		HashMap<String, String> current_user_details = sessionManager.getUserDetails();
		
		mNombreUsuario = current_user_details.get(SessionManager.KEY_NAME);
		
		HomeInitializationTask hia = new HomeInitializationTask();
		hia.execute((Void) null);
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
	
	public class HomeInitializationTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			//	Mostramos informacion para cada una de las pizarras del usuario
			DAOFactory mDAOFactory = DAOFactory.getDAOFactory(DAOFactory.REST);
			PizarraDAO mPizarraDAO = mDAOFactory.getPizarraDAO();
			
			ArrayList<Pizarra> pizarras_usuario = 
					mPizarraDAO.buscarPizarrasDeUsuario(mNombreUsuario); 
			
			mPizarrasUsuario = pizarras_usuario;
			
			return mPizarrasUsuario != null;
		}
		
		@Override
		protected void onPostExecute(final Boolean success) {
			// TODO Auto-generated method stub
			if (success) {
				StringBuffer temporal = new StringBuffer();
			
				for (Pizarra piz: mPizarrasUsuario) {
					temporal.append(piz.getNombre());
				}
				
				mTextView.setText(temporal.toString());
			} else {
				mTextView.setText("No hay pizarras");
			}
		}
	}
}
