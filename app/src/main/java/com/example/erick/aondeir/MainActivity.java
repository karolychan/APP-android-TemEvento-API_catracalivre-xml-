package com.example.erick.aondeir;

import android.os.StrictMode;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
        import android.widget.TextView;

        import java.lang.String;
        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> cities = new ArrayList<String>();
    private DocList docs = new DocList();
    private CityList cityList = new CityList();
    private Spinner spnCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        spnCities = (Spinner) findViewById(R.id.spCities);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cityList.returnCitiesName());
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnCities.setAdapter(spinnerArrayAdapter);

        spnCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                cityList.returnGeoLocByCityName(String.valueOf(spnCities.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String geoLoc = cityList.returnGeoLocByCityName(String.valueOf(spnCities.getSelectedItem()));
    }

    public void onClickEventSearch(View view) {

        String setGeoLoc = cityList.returnGeoLocByCityName(String.valueOf(spnCities.getSelectedItem()));
        final Spinner spinnerEvent;
        final List<String> titles = docs.returnAllPost_Title();

            spinnerEvent = (Spinner) findViewById(R.id.chooseEvent);
            //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, titles);
            ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerEvent.setAdapter(spinnerArrayAdapter);

        spinnerEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //String title = String.valueOf(spinnerEvent.getSelectedItem());
                String place = docs.returnPlaceByTitle(String.valueOf(spinnerEvent.getSelectedItem()));
                TextView tvPlaceName = (TextView) findViewById(R.id.tvPlaceName);
                tvPlaceName.setText(place);

                String bairro = docs.returnNeighborhoodByTitle(String.valueOf(spinnerEvent.getSelectedItem()));
                TextView tvBairro = (TextView) findViewById(R.id.tvBairro);
                tvBairro.setText(bairro);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }
}
