package models;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Pizarra {

	private String id;
	private String nombre;
	private String descripcion;
	private String fecha_creacion;
	private String fecha_final;
	private String login_dueno;
	private boolean is_active;
	
	public Pizarra(String id, String nombre, String descripcion,
			String fecha_creacion, String fecha_final, String login_dueno,
			boolean is_active) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_creacion = fecha_creacion;
		this.fecha_final = fecha_final;
		this.login_dueno = login_dueno;
		this.is_active = is_active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}

	public String getLogin_dueno() {
		return login_dueno;
	}

	public void setLogin_dueno(String login_dueno) {
		this.login_dueno = login_dueno;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
	@Override
	public String toString() {
		JSONObject temp = new JSONObject();
		try {
			temp.put("idpiz", getId());
			temp.put("nombrepiz", getNombre());
			temp.put("descripcionpiz", getDescripcion());
			temp.put("fechacreacion", getFecha_creacion());
			temp.put("fechafinal", getFecha_final());
			temp.put("logindueno", getLogin_dueno());
			temp.put("is_active", isIs_active());
		} catch (JSONException je) {
			System.out.println("toString : Error manejando data JSON");
		}
		
		// TODO Auto-generated method stub
		return temp.toString();
	}
}
