package bd;

import java.io.IOException;
import java.util.ArrayList;

import models.Pizarra;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RESTPizarraDAO implements PizarraDAO {

	private static final String TAG = "RESTPizarraDAO";
	
	@Override
	public int crearPizara(String nombre_usuario, String contrasena,
			String nombre, String apellido, String correo, String telefono) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean eliminar_pizarra(String nombre_usuario) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public ArrayList<Pizarra> buscarPizarrasDeUsuario(String nombre_usuario) {
		// TODO Auto-generated method stub
		ArrayList<Pizarra> result = new ArrayList<Pizarra>();
		String response = null;
		StringBuffer sb = new StringBuffer(RESTDAOFactory.DJANGO_URL);
		
		sb.append("/pizarras/rest_pizarras_usuario/");
		sb.append(nombre_usuario + "/.json");

		try {
			response = RESTDAOFactory.downloadUrl(sb.toString());			
			//	Parseamos la data JSON
			
			JSONArray pizarrasJSON = new JSONArray(response);
			int n = pizarrasJSON.length();
			
			Pizarra aux; 
			for (int i = 0; i < n; i++) {
				JSONObject json_aux = pizarrasJSON.getJSONObject(i);
				aux = new Pizarra(
						json_aux.getString("idpiz"),
						json_aux.getString("nombrepiz"),
						json_aux.getString("descripcionpiz"),
						json_aux.getString("fechacreacion"),
						json_aux.getString("fechafinal"),
						json_aux.getString("logindueno"),
						json_aux.getBoolean("is_active"));
				Log.d(TAG, "pizarra i=" + i + ", " + aux.toString());
				result.add(aux);
			}
		} catch (JSONException je) {
			System.out.println("Error parseando json" + je.getMessage());
		} catch (IOException ioe) {
			System.out.println("Error leyendo data " + ioe.getMessage());
		}
		return result;
	}
	
	@Override
	public ArrayList<Pizarra> buscarPorIdPizarra(String id_pizarra) {
		// TODO Auto-generated method stub
		return null;
	}
}
