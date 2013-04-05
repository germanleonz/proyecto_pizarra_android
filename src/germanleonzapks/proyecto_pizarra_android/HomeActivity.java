package germanleonzapks.proyecto_pizarra_android;

import java.util.ArrayList;
import java.util.HashMap;

import models.Pizarra;
import models.PizarraListAdapter;
import models.SessionManager;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import bd.DAOFactory;
import bd.PizarraDAO;

public class HomeActivity extends Activity {

	private Context mContext;
	
	private static final String TAG = "HomeActivity";
	
	private TextView mTextView;
	
	private SessionManager sessionManager;
	
	private String mNombreUsuario;
	private ArrayList<Pizarra> mPizarrasUsuario;
	
	private View mInitializedView;
	private View mInitializationStatusView;
//	private TextView mInitializationStatusMessageView;
	
	private PizarraListAdapter mPizarraListAdapter;
	private ListView mPizarraListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		mContext = this;
		
		mInitializedView = findViewById(R.id.home_page);
		mInitializationStatusView = findViewById(R.id.initialization_status_view);
//		mInitializationStatusMessageView = (TextView) findViewById(R.id.tv_initialization_status_message);
		
		// Inicializamos el manejador de sesiones
		sessionManager = new SessionManager(getApplicationContext());
		sessionManager.checkLogin();
		
		//	Conseguimos datos del usuario que esta conectado
		HashMap<String, String> current_user_details = sessionManager.getUserDetails();
		mNombreUsuario = current_user_details.get(SessionManager.KEY_NAME);
		
		//	Conseguimos las referencias a las vistas de la ListView
		mPizarraListView = (ListView) findViewById(R.id.lv_pizarras);
		
		mPizarraListView.setEmptyView(findViewById(R.id.emptyView));
		
		mPizarraListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Pizarra temp = mPizarrasUsuario.get(position);
				
				Intent i = new Intent(HomeActivity.this, PizarraDetailsActivity.class);
				i.putExtra("nombre_pizarra", temp.getNombre());
				i.putExtra("descripcion_pizarra", temp.getDescripcion());
				i.putExtra("fechainicio_pizarra", temp.getFecha_creacion());
				i.putExtra("fechafinal_pizarra", temp.getFecha_final());
				startActivity(i);
			}
		});
		
		//	Buscamos datos del usuario Ej: Pizarras, etc
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
			
			mPizarrasUsuario = mPizarraDAO.buscarPizarrasDeUsuario(mNombreUsuario); 
			
			return mPizarrasUsuario != null;
		}
		
		@Override
		protected void onPostExecute(final Boolean success) {
			// TODO Auto-generated method stub
			if (success) {
				//	Inicializamos la lista de pizarras
				mPizarraListAdapter = new PizarraListAdapter(mContext, R.layout.lv_pizarra_element, 
						mPizarrasUsuario);
				
				mPizarraListView.setAdapter(mPizarraListAdapter);
				
				showProgress(false);
			} else {
				mTextView.setText("No hay pizarras");
			}
		}
	}
	
	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mInitializationStatusView.setVisibility(View.VISIBLE);
			mInitializationStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mInitializationStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mInitializedView.setVisibility(View.VISIBLE);
			mInitializedView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mInitializedView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mInitializationStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mInitializedView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
}
