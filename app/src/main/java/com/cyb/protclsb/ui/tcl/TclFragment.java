package com.cyb.protclsb.ui.tcl;

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
import com.cyb.protclsb.R;

public class TclFragment extends Fragment {

    private TclViewModel tclViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tclViewModel = ViewModelProviders.of(this).get(TclViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tcl, container, false);
        final TextView textView = root.findViewById(R.id.text_tcl);
        tclViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        System.out.println("----------------------NotificationsFragment");
        return root;
    }
}