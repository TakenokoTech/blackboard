package takenoko.tech.blackboardapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import takenoko.tech.blackboardapp.R;

/**
 * Created by たけのこ on 2017/05/05.
 */

public class DebugFragment extends Fragment {

    String log = "----DebugFragment----";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(log, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_status, container, false);
        //inflater.inflate(R.layout.layout_debuger, null);
    }
}
