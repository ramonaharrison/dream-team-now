package com.ramonaharrison.dev.dreamteamnow;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;


public class MainActivity extends FragmentActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.main_content_frame, mainFragment).commit();

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
            fragmentTransaction.replace(R.id.main_content_frame, fragment).addToBackStack(null);

        }else{
            fragmentTransaction.replace(R.id.detail_content_frame, fragment);
        }
        fragmentTransaction.commit();
    }

    public void refreshMainFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content_frame, new MainFragment()).commit();
    }

    @Override
    public void onBackPressed() {

        if(findViewById(R.id.detail_content_frame) == null){
            fragmentManager.popBackStack();
        }else{
            super.onBackPressed();
        }

    }
}
