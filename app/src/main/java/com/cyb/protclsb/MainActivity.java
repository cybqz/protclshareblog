package com.cyb.protclsb;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.cyb.protclsb.ui.me.MeFragment;
import com.cyb.protclsb.ui.pro.ProFragment;
import com.cyb.protclsb.ui.tcl.TclFragment;
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
        final BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        initBottomNavigationView(bottomNavigationView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pro,
                R.id.navigation_pro_detail,
                R.id.navigation_tcl,
                R.id.navigation_me).build();

        NavController navController = Navigation.findNavController(this, R.id.activity_main_id);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //设置导航栏菜单项Item选中监听
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment targetFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_pro:
                        targetFragment = new ProFragment();
                        break;
                    case R.id.navigation_tcl:
                        targetFragment = new TclFragment();
                        break;
                    case R.id.navigation_me:
                        targetFragment = new MeFragment();
                        break;
                    default:
                        targetFragment = new ProFragment();
                }

                //切换内容
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_main_id, targetFragment);
                transaction.commit();
                return true;
            }
        });
    }

    private void initBottomNavigationView(BottomNavigationView bottomNavigationView){
        Drawable itemBackgroundDrawable = new ColorDrawable();
        itemBackgroundDrawable.setTint(Color.WHITE);
        bottomNavigationView.setItemBackground(itemBackgroundDrawable);

        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_checked};
        states[1] = new int[]{-android.R.attr.state_checked};
        ColorStateList colorStateList = new ColorStateList(states,new int[]{Color.BLUE,Color.GRAY});
        bottomNavigationView.setItemTextColor(colorStateList);
        bottomNavigationView.setItemIconTintList(colorStateList);
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
