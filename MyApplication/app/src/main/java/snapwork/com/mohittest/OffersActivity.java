package snapwork.com.mohittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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

        Offer offer = (Offer) getIntent().getExtras().getSerializable(OFFER_OBJECT);
        loadOfferData(offer);
    }

    private void loadOfferData(Offer offer) {
        TextView tvOfferId = (TextView) findViewById(R.id.tvOfferIdValue);
        TextView tvTitleValue = (TextView) findViewById(R.id.tvTitleValue);
        TextView tvAddressValue = (TextView) findViewById(R.id.tvAddressValue);
        TextView tvLatValue = (TextView) findViewById(R.id.tvLatValue);
        TextView tvLonValue = (TextView) findViewById(R.id.tvLonValue);

        tvOfferId.setText("" + offer.getOFFER_ID());
        tvTitleValue.setText(offer.getName());
        tvAddressValue.setText(offer.getAddress());
        tvLatValue.setText("" + offer.getLAT());
        tvLonValue.setText("" + offer.getLON());
    }
}
