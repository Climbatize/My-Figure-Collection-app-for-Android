package net.myfigurecollection.widgets;

import android.support.v4.app.ListFragment;

import com.octo.android.robospice.GsonGoogleHttpClientSpiceService;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by Climbatize on 19/11/13.
 */
public class SpiceListFragment extends ListFragment {

    protected SpiceManager spiceManager = new SpiceManager(GsonGoogleHttpClientSpiceService.class);

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(this.getActivity());
    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
}
