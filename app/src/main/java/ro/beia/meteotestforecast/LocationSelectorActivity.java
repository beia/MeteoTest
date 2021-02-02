package ro.beia.meteotestforecast;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ro.beia.meteotestforecast.utils.ApiLocation;

public class LocationSelectorActivity extends AppCompatActivity {

    int city_list_id = R.array.forecast_locations;
    int location_spinner_id = R.id.location_select_spinner;

    class locationSelectSpinnerListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                List<ApiLocation> locations = parseLocationOptions(city_list_id);
                Intent intent = new Intent(getBaseContext(), ForecastShowActivity.class);
                ApiLocation selected_location = locations.get(position);

                intent.putExtra(getString(R.string.intent_city_key),
                        selected_location.getCity());

                intent.putExtra(getString(R.string.intent_api_url_key),
                        selected_location.getApi_url());

                startActivity(intent);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_selector);

        // Setup spinner options
        Spinner spinner = findViewById(location_spinner_id);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);

        for (ApiLocation location : parseLocationOptions(city_list_id)) {
            adapter.add(location.getCity());
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Enable spinner listener
        spinner.setOnItemSelectedListener(new locationSelectSpinnerListener());
    }

    private List<ApiLocation> parseLocationOptions(int stringArrayResourceId) {

        String[] stringArray = getResources().getStringArray(stringArrayResourceId);
        ArrayList<ApiLocation> outputArray = new ArrayList<>(stringArray.length);
        for (String locationString: stringArray) {
            String[] splitResult = locationString.split("\\|", 2);
            outputArray.add(new ApiLocation(splitResult[0], splitResult[1]));
        }
        return outputArray;
    }
}
