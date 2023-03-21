package com.example.signupapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class mainActivity extends Activity {
    private Spinner countrySpinner;
    private Spinner citySpinner;
    EditText title,fname,lname,suffix,address,staddress,addl21,addl22,email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        countrySpinner = findViewById(R.id.countryspinner);
        citySpinner = findViewById(R.id.cityspinner);
        title=findViewById(R.id.title2);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        suffix=findViewById(R.id.suffix);
        address=findViewById(R.id.address);
        staddress=findViewById(R.id.staddress);
        addl21=findViewById(R.id.addressl21);
        addl22=findViewById(R.id.addressl22);
        email=findViewById(R.id.email);

        // Set the adapter for the country spinner
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);

        // Set the initial adapter for the city spinner
        ArrayAdapter<CharSequence> initialCityAdapter = ArrayAdapter.createFromResource(this, R.array.usa_cities, android.R.layout.simple_spinner_item);
        initialCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(initialCityAdapter);
        countrySpinner.setSelection(0);
        citySpinner.setSelection(0);

        // Set the onItemSelectedListener for the country spinner
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected country
                String selectedCountry = parent.getItemAtPosition(position).toString();

                // Update the city spinner with the cities from the selected country
                updateCitySpinner(selectedCountry);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
    private void updateCitySpinner(String selectedCountry) {
        int cityArrayResourceId = getResources().getIdentifier(selectedCountry.toLowerCase() + "_cities", "array", getPackageName());
        String[] cityArray = getResources().getStringArray(cityArrayResourceId);


        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityArray);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
    }

    public void submit(View v){
        String tmessage="";
        Boolean flag=true;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(title.getText().toString().trim().length() == 0){
            tmessage=tmessage+"Please update Title\n";
            flag=false;
        }
        if(fname.getText().toString().trim().length() == 0){
            tmessage=tmessage+"Please update First name\n";
            flag=false;
        }
        if(lname.getText().toString().trim().length() == 0){
            tmessage=tmessage+"Please update Last name\n";
            flag=false;
        }
        if(suffix.getText().toString().trim().length() == 0){
            tmessage=tmessage+"Please update suffix\n";
            flag=false;
        }
        if(address.getText().toString().trim().length() == 0){
            tmessage=tmessage+"Please update Address\n";
            flag=false;
        }


        if(!(email.getText().toString().matches(emailPattern))){
            tmessage=tmessage+"Please provide proper email.\n";
            flag=false;
        }
        String selectedCountry = countrySpinner.getSelectedItem().toString();
        String selectedCity = citySpinner.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(),tmessage,Toast.LENGTH_LONG).show();
        Log.d("Success","value: "+tmessage+selectedCity+selectedCountry);

        if(flag==true){
            Intent i=new Intent(mainActivity.this,successRegistration.class);
            String message="Name: "+title.getText().toString()+" "+fname.getText().toString()+" "+lname.getText().toString()+" "+suffix.getText().toString()+"\n"+"Address: "+address.getText().toString()+", "+staddress.getText().toString()+", "+addl21.getText().toString()+", "+addl22.getText().toString()+"\n"+"Country: "+selectedCountry+"\n"+"City: "+selectedCity+"\n"+"Email: "+email.getText().toString();
            Log.d("Success","Value: "+message);
            i.putExtra("msg",message);
            startActivity(i);
        }

    }
}
