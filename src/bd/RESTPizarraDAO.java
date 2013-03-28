package bd;

import java.util.ArrayList;

import models.Pizarra;

public class RESTPizarraDAO implements PizarraDAO {

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
	public ArrayList<Pizarra> buscarTodas() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Pizarra> buscarPorIdPizarra(String id_pizarra) {
		// TODO Auto-generated method stub
		return null;
	}
}
