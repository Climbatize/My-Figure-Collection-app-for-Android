package net.myfigurecollection.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octo.android.robospice.persistence.DurationInMillis;

import net.myfigurecollection.MainActivity;
import net.myfigurecollection.R;
import net.myfigurecollection.api.CollectionMode;
import net.myfigurecollection.api.GalleryMode;
import net.myfigurecollection.api.SearchMode;
import net.myfigurecollection.api.UserMode;
import net.myfigurecollection.api.request.CollectionRequest;
import net.myfigurecollection.api.request.GalleryRequest;
import net.myfigurecollection.api.request.SearchRequest;
import net.myfigurecollection.api.request.UserRequest;
import net.myfigurecollection.api.request.listener.MFCRequestListener;
import net.myfigurecollection.widgets.SpiceListFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends SpiceListFragment {


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PlaceholderFragment.this.getActivity().setProgressBarIndeterminateVisibility(true);


        CollectionRequest request = new CollectionRequest("Climbatize", "0", "0", "0");
        SearchRequest request1 = new SearchRequest("Saber");
        GalleryRequest request2 = new GalleryRequest("Climbatize", "0");
        UserRequest request3 = new UserRequest("Climbatize");

        spiceManager.execute(request, request.createCacheKey(), DurationInMillis.ALWAYS_EXPIRED, new MFCRequestListener<CollectionMode>(this));
        spiceManager.execute(request1, request.createCacheKey(), DurationInMillis.ALWAYS_EXPIRED, new MFCRequestListener<SearchMode>(this));
        spiceManager.execute(request2, request.createCacheKey(), DurationInMillis.ALWAYS_EXPIRED, new MFCRequestListener<GalleryMode>(this));
        spiceManager.execute(request3, request.createCacheKey(), DurationInMillis.ALWAYS_EXPIRED, new MFCRequestListener<UserMode>(this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
       /* TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}