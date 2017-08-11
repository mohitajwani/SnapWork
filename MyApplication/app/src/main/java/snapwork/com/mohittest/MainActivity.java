package snapwork.com.mohittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Offer> offersList;
    private GoogleMap map;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        loadOffers();
    }

    private void loadOffers() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Offer>> call = apiService.getAllOffers();
        call.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>>call, Response<List<Offer>> response) {
                offersList = response.body();
                Log.e(TAG, "Size of List=" + offersList.size());
                plotOffers(map);
            }

            @Override
            public void onFailure(Call<List<Offer>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void plotOffers(GoogleMap googleMap) {
        if (googleMap != null && offersList != null) {
            for (Offer offer : offersList) {
                googleMap.addMarker(new MarkerOptions().title(offer.getName()).position(new LatLng(offer.getLAT(), offer.getLON())));
            }
        }
        if (listFragment != null) {
            listFragment.loadData();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        listFragment = ListFragment.newInstance();
        adapter.addFragment(listFragment, getString(R.string.list_tab));
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        supportMapFragment.getMapAsync(this);
        adapter.addFragment(supportMapFragment, getString(R.string.maps_tab));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        plotOffers(googleMap);
        //MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(19.0760,72.8777)).title("Test");
        //googleMap.addMarker(markerOptions);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public List<Offer> getOffersList() {
        return offersList;
    }

    public void openOfferDetails(Offer offer) {
        Intent intent = new Intent(this, OffersActivity.class);
        intent.putExtra(OffersActivity.OFFER_OBJECT, offer);
        startActivity(intent);
    }
}
