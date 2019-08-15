package muchbeer.raum.com.gcertprofessioncode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import muchbeer.raum.com.gcertprofessioncode.tracking.Tracker;

public class TrackerActivity extends AppCompatActivity {

    protected Tracker mTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        mTracker=new Tracker(this);
    }
}
