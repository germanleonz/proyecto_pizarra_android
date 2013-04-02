package models;

import germanleonzapks.proyecto_pizarra_android.LoginActivity;
import germanleonzapks.proyecto_pizarra_android.MainActivity;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	
	//	SharedPreferences
	SharedPreferences pref;
	
	//	Editor para las SharedPreferences
	Editor editor;
	
	//	Context
	Context mContext;

	int PRIVATE_MODE = 0;
	
	private static final String PREF_NAME = "ProyectoPizarraPref";
	
	private static final String IS_LOGGED_IN = "IsLoggedIn";
	
	public static final String KEY_NAME = "name";
	
	//	Constructor
	public SessionManager(Context context) {
		this.mContext = context;
		pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public void createLoginSession(String nombre_usuario) {
		editor.putBoolean(IS_LOGGED_IN, true);
		editor.putString(KEY_NAME, nombre_usuario);
		editor.commit();
	}
	
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user_details = new HashMap<String, String>();
		user_details.put(KEY_NAME, pref.getString(KEY_NAME, null));
		return user_details;
	}
	
	public void checkLogin() {
		if (!this.isLoggedIn()) {
			 // user is not logged in redirect him to MainActivity
            Intent i = new Intent(mContext, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 
            // Staring Login Activity
            mContext.startActivity(i);
		}
	}
	
	public void logoutUser() {
		//	Vaciamos los SharedPreferences
		editor.clear();
		editor.commit();
		
		Intent i = new Intent(mContext, MainActivity.class);
		
		//	Closing all activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		//	Agregamos otro flag para empezar una nueva actividad
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		mContext.startActivity(i);
	}
	
	public boolean isLoggedIn() {
		return pref.getBoolean(IS_LOGGED_IN, false);
	}
}
