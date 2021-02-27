package com.cyb.protclsb.ui.dashboard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.cyb.protclsb.R;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

public class DashboardFragment extends Fragment {

    private Context context;
    private TextView locationView;
    private LocationManager locationManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = getContext();
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        locationView = root.findViewById(R.id.text_location);
        locationManager = (LocationManager) getSystemService(getContext(), LocationManager.class);
        mHandler.postDelayed(r, 10000);

        System.out.println("----------------------DashboardFragment");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacks(r);
        System.out.println("Destroy----------------------DashboardFragment");
    }

    /**
     * 定时获取位置信息
     */
    Handler mHandler = new Handler();
    Runnable r = new Runnable() {

        @SuppressLint("WrongConstant")
        @Override
        public void run() {

            int accessFineLocation = checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
            int accessCoarseLocation = checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
            if (accessFineLocation == PackageManager.PERMISSION_GRANTED && accessCoarseLocation == PackageManager.PERMISSION_GRANTED) {

                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                double lat = location.getLatitude();//获取纬度
                double lng = location.getLongitude();//获取经度
                locationView.setText(lat+";"+lng);
            }
            mHandler.postDelayed(this, 10000);
        }
    };
}