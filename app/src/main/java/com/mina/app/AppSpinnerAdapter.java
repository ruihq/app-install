package com.mina.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppSpinnerAdapter extends ArrayAdapter<String> {
	
	private Context context;
	private List<String> appNames;
	private List<Drawable> appIcons;
	
	public AppSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<String> appNames, @NonNull List<Drawable> appIcons) {
		super(context, resource, appNames);
		this.context = context;
		this.appNames = appNames;
		this.appIcons = appIcons;
	}
	
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}
	
	@Override
	public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}
	
	private View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.item_app_spinner, parent, false);
		}
		
		ImageView iconImageView = convertView.findViewById(R.id.iconImageView);
		TextView nameTextView = convertView.findViewById(R.id.nameTextView);
		
		iconImageView.setImageDrawable(appIcons.get(position));
		nameTextView.setText(appNames.get(position));
		
		return convertView;
	}
}