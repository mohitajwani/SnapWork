package snapwork.com.mohittest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snap34 on 11/8/17.
 */

public class ListFragment extends Fragment {
    public static final String TAG = ListFragment.class.getSimpleName();

    private List<Offer> offerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OffersAdapter mAdapter;

    public static ListFragment newInstance() {
        Bundle args = new Bundle();
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_list, null);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        if (offerList != null) {
            initializeAdapter();
        }
    }

    private void initializeAdapter() {
        Log.d(TAG,"initializeAdapter mAdapter = " + mAdapter);
        mAdapter = new OffersAdapter(offerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        loadData();
    }

    public void loadData() {
        Log.d(TAG,"loadData mAdapter = " + mAdapter + " offerList = " + offerList);
        offerList = ((MainActivity) getActivity()).getOffersList();
        if (offerList != null && !offerList.isEmpty()) {
            mAdapter.notifyDataSetChanged();
        }
    }
}