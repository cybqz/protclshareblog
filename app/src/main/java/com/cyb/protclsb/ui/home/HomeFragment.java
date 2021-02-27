package com.cyb.protclsb.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.cyb.protclsb.MainActivity;
import com.cyb.protclsb.common.RequestConstant;
import com.cyb.protclsb.ui.login.LoginFragment;
import com.cyb.protclsb.util.HttpUtil;
import com.cyb.protclsb.R;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private static  String TAG = MainActivity.class.getCanonicalName();
    private LoginFragment loginFragment;
    private JSONObject requestPostMap = new JSONObject();
    private View root;
    private TextView textView;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        System.out.println("----------------------HomeFragment");
        return root;
    }



    Runnable postRun = new Runnable() {

        @Override
        public void run() {
            requestPostMap = HttpUtil.requestPost(TAG, RequestConstant.LOGINED_USER_URL, null);
        }
    };

}