package germanleonzapks.proyecto_pizarra_android;

import models.UserProfile;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import bd.DAOFactory;
import bd.UserProfileDAO;

public class RegistrationActivity extends Activity {

	private static final String TAG = "RegistrationActivity";
	
	private UserRegistrationTask mRegistrationTask = null;
	
	private String mUsername;
	private String mPassword;
	private String mRepeatPassword;
	private String mFirstName;
	private String mLastName;
	private String mEmail;
	private String mPhone;
	
	private EditText mUsernameView;
	private EditText mPasswordView;
	private EditText mRepeatPasswordView;
	private EditText mFirstNameView;
	private EditText mLastNameView;
	private EditText mEmailView;
	private EditText mPhoneView;
	
	private View mRegistrationFormView;
	private View mRegistrationStatusView;
	private TextView mRegistrationStatusMessageView;
	
	private Button mCreateAccountButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		mUsernameView = (EditText) findViewById(R.id.et_username);
		mPasswordView = (EditText) findViewById(R.id.et_password);
		mRepeatPasswordView = (EditText) findViewById(R.id.et_repeat_password);
		mFirstNameView = (EditText) findViewById(R.id.et_first_name);
		mLastNameView = (EditText) findViewById(R.id.et_last_name);
		mEmailView = (EditText) findViewById(R.id.et_email);
		mPhoneView = (EditText) findViewById(R.id.et_phone);
		
		mCreateAccountButton = (Button) findViewById(R.id.button_crear_cuenta);
		mCreateAccountButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (v.getId() == R.id.button_crear_cuenta) {
							attemptUserCreation();
						}
					}
				});
		
		mRegistrationFormView = findViewById(R.id.registration_form);
		mRegistrationStatusView = findViewById(R.id.registration_status);
		mRegistrationStatusMessageView = (TextView) findViewById(R.id.registration_status_message);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}
	
	private void attemptUserCreation() {
		// Store values at the time of the login attempt.
		mUsername= mUsernameView.getText().toString();
		mPassword = mPasswordView.getText().toString();
		mRepeatPassword = mRepeatPasswordView.getText().toString();
		mFirstName = mFirstNameView.getText().toString();
		mLastName = mLastNameView.getText().toString();
		mEmail = mEmailView.getText().toString();
		mPhone = mPhoneView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		//	Check for a valid phone number
		if (TextUtils.isEmpty(mPhone)) {
			mPhoneView.setError(getString(R.string.error_field_required));
			focusView = mPhoneView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} 

		//	Check for a valid phone number
		if (TextUtils.isEmpty(mLastName)) {
			mLastNameView.setError(getString(R.string.error_field_required));
			focusView = mLastNameView;
			cancel = true;
		}

		// Check for a valid first name.
		if (TextUtils.isEmpty(mFirstName)) {
			mFirstNameView.setError(getString(R.string.error_field_required));
			focusView = mFirstNameView;
			cancel = true;
		} 

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 6) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		} else if (!TextUtils.equals(mPassword, mRepeatPassword)) {
			mPasswordView.setError(getString(R.string.error_passwords_dont_match));
			focusView = mPasswordView;
			cancel = true;
		} 

		//	Check for a valid username
		if(TextUtils.isEmpty(mUsername)){
			mUsernameView.setError(getString(R.string.error_field_required));
			focusView = mUsernameView;
			cancel = true;
		}
		
		if (cancel) {
			// There was an error; don't attempt registration and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mRegistrationStatusMessageView.setText(R.string.registration_progress_creating_user);
			showProgress(true);
			mRegistrationTask = new UserRegistrationTask();
			mRegistrationTask.execute((Void) null);
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

			mRegistrationStatusView.setVisibility(View.VISIBLE);
			mRegistrationStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mRegistrationStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mRegistrationFormView.setVisibility(View.VISIBLE);
			mRegistrationFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mRegistrationFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mRegistrationStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mRegistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	
	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserRegistrationTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			DAOFactory mDAOFActory = DAOFactory.getDAOFactory(DAOFactory.REST);
			UserProfileDAO upDAO = mDAOFActory.getUserProfileDAO();
			
			UserProfile nuevo_up = new UserProfile(mUsername, mPassword, 
					mFirstName, mLastName, mEmail, mPhone);
			
			boolean usuario_creado = upDAO.crearUsuario(nuevo_up);
			return usuario_creado;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mRegistrationTask = null;
			showProgress(false);

			if (success) {
				String text = getString(R.string.user_registered_successfully);
				int duration = Toast.LENGTH_SHORT;
				
				Toast successToast = Toast.makeText(getApplicationContext(), text, duration);
				successToast.show();
				
				Intent i;
				i = new Intent(RegistrationActivity.this, LoginActivity.class);
				startActivity(i);
			} else {
				mUsernameView
					.setError(getString(R.string.error_username_already_registered));
				mUsernameView.requestFocus();
				Log.d(TAG, "El nombre de usuario ya estaba registrado");
			}
		}

		@Override
		protected void onCancelled() {
			mRegistrationTask = null;
			showProgress(false);
		}
	}
}
