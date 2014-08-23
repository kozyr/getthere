package kozyrenko.com.ctacatcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import kozyrenko.com.ctacatcher.model.Arrival;
import kozyrenko.com.ctacatcher.model.TrainEta;
import kozyrenko.com.ctacatcher.service.CTAService;
import kozyrenko.com.ctacatcher.service.TrainLocator;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class Main extends Activity {

    private static final String TAG = "Main";

    private TextView arrivalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        arrivalView = (TextView) findViewById(R.id.arrival);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        // Intent intent = new Intent(this, CTAService.class);
        // startService(intent);

        TrainLocator locator = new TrainLocator(this);
        locator.getNextArrival()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Arrival>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Could not get arrivals", e);
                    }

                    @Override
                    public void onNext(Arrival arrival) {
                        showNextArrival(arrival);
                    }
                });

        super.onStart();
    }

    private void showNextArrival(Arrival arrival) {
        Log.i(TAG, "Arrival: " + arrival);
        StringBuilder sb = new StringBuilder();
        List<TrainEta> etas = arrival.getEtas();

        for (TrainEta eta : etas) {
            String message = "Train to " + eta.getDestinationName() + " in " + eta.until() + " minutes";
            sb.append(message + "\n");
        }

        arrivalView.setText(sb.toString());
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, CTAService.class);
        stopService(intent);

        super.onDestroy();
    }
}
