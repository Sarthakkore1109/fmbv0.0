package com.blizaerts.fmbv00;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blizaerts.fmbv00.Data.AddBusDetail;
import com.blizaerts.fmbv00.Data.AddBusNo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class BusDetailAdd extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabaseBusno;
    DatabaseReference firebaseDatabaseBusDetail;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail_add);
        progressDialog = new ProgressDialog(this);

        final EditText editTextbus_no = findViewById(R.id.editText_BusNo);
        final EditText editTextstation_index = findViewById(R.id.editText_StationInd);
        final EditText editTextstation_name = findViewById(R.id.editText_StationName);
        final EditText editTextloc_lat = findViewById(R.id.editText_latitude);
        final EditText editTextloc_longi = findViewById(R.id.editText_Longitude);
        Button reg_businfo = findViewById(R.id.button_regInfo);

        reg_businfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busno = editTextbus_no.getText().toString().trim();
                String station_index = editTextstation_index.getText().toString().trim();
                String station_name = editTextstation_name.getText().toString().trim();
                String loc_lat = editTextloc_lat.getText().toString().trim();
                String loc_longi = editTextloc_longi.getText().toString().trim();

                double lat = Double.parseDouble(loc_lat);
                double longi = Double.parseDouble(loc_longi);

                AddBusNo addBusNo = new AddBusNo(busno);
                AddBusDetail addBusDetail = new AddBusDetail(station_name, lat, longi);




                if (!busno.equalsIgnoreCase("")) {
                    if (!station_index.equalsIgnoreCase("")) {
                        if (!station_name.equalsIgnoreCase("")) {
                            if (!loc_lat.equalsIgnoreCase("")) {
                                if (!loc_longi.equalsIgnoreCase("")) {

                                    progressDialog.setMessage("Saving, Please Wait.....");
                                    progressDialog.show();

                                    int xyz = Integer.parseInt(busno);
                                    int staind = Integer.parseInt(station_index);

                                    firebaseDatabaseBusno = FirebaseDatabase.getInstance().getReference();
                                    Map<String, Object> busdetail_updater = new HashMap<>();
                                    busdetail_updater.put("BUSINFO/" + xyz + "/" + staind, addBusDetail);

                                    firebaseDatabaseBusno.updateChildren(busdetail_updater).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressDialog.dismiss();
                                            if (task.isSuccessful()) {
                                                Log.d("1", "Succesfull");
                                                Toast.makeText(BusDetailAdd.this, "Data registered successfully", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Log.d("1", "Fail");
                                                Toast.makeText(BusDetailAdd.this, "Failed to register data", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                                } else {
                                    Toast.makeText(BusDetailAdd.this, "Please enter a valid longitude", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(BusDetailAdd.this, "Please enter a valid latitude", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(BusDetailAdd.this, "Please enter a valid station name", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(BusDetailAdd.this, "Please enter a valid station index", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BusDetailAdd.this, "Please enter a valid bus number", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
