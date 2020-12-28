package com.example.appwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

public class Menu extends AppCompatActivity {
    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private ViewPagerAdapter adapter;

    private View viewEndAnimation;
    private ImageView viewAnimation;
    private int mCountProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        viewEndAnimation = findViewById(R.id.view_end_animation);
        viewAnimation = findViewById(R.id.view_animation);

        ahBottomNavigation= findViewById(R.id.AHBottomNavigation);
        ahBottomNavigationViewPager=findViewById(R.id.AHBottomNavigationViewPager);

        adapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ahBottomNavigationViewPager.setAdapter(adapter);
        ahBottomNavigationViewPager.setPagingEnabled(true);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_product, R.color.color_tab1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_cart, R.color.color_tab2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_notice, R.color.color_tab3);

        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                ahBottomNavigationViewPager.setCurrentItem(position);
                return true;
            }
        });

        ahBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ahBottomNavigation.setCurrentItem(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    public View getViewEndAnimation(){
        return viewEndAnimation;

    }
    public ImageView getViewAnimation(){

        return viewAnimation;
    }

    public void setCountProductInCart(int count) {
        mCountProduct = count;
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(Menu.this, R.color.red))
                .setTextColor(ContextCompat.getColor(Menu.this, R.color.white))
                .build();
        ahBottomNavigation.setNotification(notification, 1);
    }
    public int getmCountProduct(){
        return mCountProduct;
    }
}