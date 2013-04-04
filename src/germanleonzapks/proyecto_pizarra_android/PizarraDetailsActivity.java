package germanleonzapks.proyecto_pizarra_android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class PizarraDetailsActivity extends Activity {

	TextView mNombrePizarraView;
	TextView mDescripcionPizarraView;
	TextView mFechaInicioPizarraView;
	TextView mFechaFinalTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pizarra_details);
		
		mNombrePizarraView = (TextView) findViewById(R.id.tv_nombre_pizarra_value);
		mDescripcionPizarraView = (TextView) findViewById(R.id.tv_descripcion_pizarra_value);
		mFechaInicioPizarraView = (TextView) findViewById(R.id.tv_fechainicio_value);
		mFechaFinalTextView = (TextView) findViewById(R.id.tv_fechafinal_value);
		
		Bundle bundle = getIntent().getExtras();
		mNombrePizarraView.setText(bundle.getString("nombre_pizarra"));
		mDescripcionPizarraView.setText(bundle.getString("descripcion_pizarra"));
		mFechaInicioPizarraView.setText(bundle.getString("fechainicio_pizarra"));
		mFechaFinalTextView.setText(bundle.getString("fechafinal_pizarra"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pizarra_details, menu);
		return true;
	}

}
