package com.ramonaharrison.dev.dreamteamnow;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;


public class MainActivity extends FragmentActivity {

    FragmentManager fragmentManager;
    FrameLayout mainFrameLayout;
    FrameLayout detailFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        mainFrameLayout = (FrameLayout) findViewById(R.id.main_content_frame);
        detailFrameLayout = (FrameLayout) findViewById(R.id.detail_content_frame);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.main_content_frame, mainFragment);
        fragmentTransaction.commit();

        if(findViewById(R.id.detail_content_frame) != null){
            FragmentTransaction detailFragTransaction = fragmentManager.beginTransaction();
            PlaceHolderFragment placeHolderFragment = new PlaceHolderFragment();
            detailFragTransaction.replace(R.id.detail_content_frame, placeHolderFragment);
            detailFragTransaction.commit();
        }

    }

    public void replaceFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(fragment instanceof PlaceHolderFragment && findViewById(R.id.detail_content_frame) == null){
            fragment = new MainFragment();
        }

        if(findViewById(R.id.detail_content_frame) == null) {
            mainFrameLayout.removeAllViews();
            fragmentTransaction.replace(R.id.main_content_frame, fragment).commit();
        }else{
            detailFrameLayout.removeAllViews();
            fragmentTransaction.replace(R.id.detail_content_frame, fragment).commit();
        }
    }

    public void refreshMainFragment(){
        mainFrameLayout.removeAllViews();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content_frame, new MainFragment()).commit();
    }

}
