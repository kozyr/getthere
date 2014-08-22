package kozyrenko.com.ctacatcher;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by dev on 8/17/14.
 */
public class CatcherApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
