package snapwork.com.mohittest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Mohit Ajwani.
 */

public class OffersActivity extends AppCompatActivity {

    private static final String TAG = OffersActivity.class.getSimpleName();

    public static final String OFFER_OBJECT = "Offer";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getBundleExtra(OFFER_OBJECT);
        bundle.getSerializable(OFFER_OBJECT);
    }
}
