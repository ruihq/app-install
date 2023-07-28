package com.mina.app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	
	private Spinner appSpinner;
	private Button downloadButton;
	
	// Replace these arrays with your own app names and download URLs
	private final String[] appNames = {"Gangster Town", "Bhop Pro", "Stay Awake"};
	private final String[] appDownloadUrls = {
		"https://github.com/ruihq/app-install/releases/tag/gt_town", "https://github.com/ruihq/app-install/releases/tag/bhop", "https://github.com/ruihq/app-install/releases/tag/stay_awake"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		appSpinner = findViewById(R.id.appSpinner);
		downloadButton = findViewById(R.id.downloadButton);
		
		// Set up the Spinner with the app names
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
		this, android.R.layout.simple_spinner_item, appNames);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		appSpinner.setAdapter(spinnerAdapter);
		
		// Set an item selection listener to enable the download button
		appSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				downloadButton.setEnabled(true);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				downloadButton.setEnabled(false);
			}
		});
		
		downloadButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				downloadSelectedApp();
			}
		});
	}
	
	private void downloadSelectedApp() {
		int selectedPosition = appSpinner.getSelectedItemPosition();
		if (selectedPosition >= 0 && selectedPosition < appDownloadUrls.length) {
			String downloadUrl = appDownloadUrls[selectedPosition];
			Uri uri = Uri.parse(downloadUrl);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			try {
				startActivity(intent);
				} catch (ActivityNotFoundException e) {
				// Handle the case when no activity can handle the intent (e.g., no web browser).
				// You can show a toast or a dialog to inform the user.
			}
		}
	}
}