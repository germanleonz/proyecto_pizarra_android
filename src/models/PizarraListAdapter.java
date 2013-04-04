package models;

import germanleonzapks.proyecto_pizarra_android.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PizarraListAdapter extends ArrayAdapter<Pizarra> {
	
	private Context mContext;
	private ArrayList<Pizarra> values;
	private int elementResourceId;
	
	public PizarraListAdapter(Context context, int elementResourceId, ArrayList<Pizarra> values) {
		super(context, elementResourceId, values);
		this.mContext = context;
		this.values = values;
		this.elementResourceId = elementResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		
		if (row == null) {
			LayoutInflater layoutInflater = 
					((Activity) mContext).getLayoutInflater();
			row = layoutInflater.inflate(elementResourceId, null);
		}
		
		Pizarra elem = values.get(position);
		
		if (elem != null) {
			TextView tv_nombre_pizarra = 
					(TextView) row.findViewById(R.id.lv_pizarra_element_name);
			TextView tv_descripcion_pizarra = 
					(TextView) row.findViewById(R.id.lv_pizarra_element_descripcion);
			
			if (tv_nombre_pizarra != null) {
				tv_nombre_pizarra.setText(elem.getNombre());
			} 
			if (tv_descripcion_pizarra != null) {
				tv_descripcion_pizarra.setText(elem.getDescripcion());
			}
		}
		return row;
	}
}
