package com.cyb.protclsb;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import com.cyb.protclsb.common.TipsEnum;
import com.cyb.protclsb.R;
import com.cyb.protclsb.ui.option.OptionController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private static  String TAG =  MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.setStatusBarColor(Color.BLUE);//改变状态栏颜色（可以和应用的标题颜色一样）
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //隐藏标题栏
        //getSupportActionBar().hide();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            Log.e(TAG,"Set actionBar success");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }else{
            Log.e(TAG,"Set actionBar failed");
        }

        setContentView(R.layout.activity_main);

        //设置底部导航栏
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_notifications,
                R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    /**
     * 设置头部按钮单击
     * @param item
     * @return
     */
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Context context = getApplicationContext();
        OptionController optionController = new OptionController(context);
        switch (item.getItemId()) {
            case R.id.navigation_logout:
                optionController.logout();
                break;
            case R.id.navigation_setting:
                Log.e(TAG, "setting");
                break;
            default:
                Toast.makeText(MainActivity.this, TipsEnum.SELECT_ERROR.getMsg(), Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /**
     * 设置头部下拉选
     * @param menu
     * @return
     */
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("MainActivity","onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/
}
