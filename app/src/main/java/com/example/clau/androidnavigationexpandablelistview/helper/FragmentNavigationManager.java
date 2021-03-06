package com.example.clau.androidnavigationexpandablelistview.helper;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.clau.androidnavigationexpandablelistview.Fragments.FragmentContent;
import com.example.clau.androidnavigationexpandablelistview.Interface.NavigationManager;
import com.example.clau.androidnavigationexpandablelistview.MainActivity;

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager mInstance;
    private FragmentManager mFragmentManager;
    private MainActivity mainActivity;

    public static FragmentNavigationManager getmInstance(MainActivity mainActivity)
    {
        if(mInstance == null)
        {
            mInstance = new FragmentNavigationManager();
            mInstance.configure(mainActivity);
        }
        return mInstance;
    }

    private void configure(MainActivity mainActivity) {
        mainActivity = mainActivity;
        mFragmentManager = mainActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragment(String titulo) {

    }
}
   // @Override
//    public void showFragment(String titulo) {
//
//        showFragment(FragmentContent.newInstance(titulo),false);
//    }
//


