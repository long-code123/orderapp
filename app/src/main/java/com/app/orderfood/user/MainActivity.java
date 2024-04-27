package com.app.orderfood.user;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.app.orderfood.R;
import com.app.orderfood.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    PageUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new PageUserAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        binding.vpgMain.setAdapter(adapter);


        binding.vpgMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.bottomNavi.getMenu().findItem(R.id.user_home).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavi.getMenu().findItem(R.id.user_action).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNavi.getMenu().findItem(R.id.user_payment).setChecked(true);
                        break;
                    case 3:
                        binding.bottomNavi.getMenu().findItem(R.id.user_messes).setChecked(true);
                        break;
                    case 4:
                        binding.bottomNavi.getMenu().findItem(R.id.user_account).setChecked(true);
                        break;
                    default:
                        binding.bottomNavi.getMenu().findItem(R.id.user_home).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        binding.bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item == binding.bottomNavi.getMenu().getItem(0)) {
                    binding.vpgMain.setCurrentItem(0);
                }
                if (item == binding.bottomNavi.getMenu().getItem(1)) {
                    binding.vpgMain.setCurrentItem(1);
                }
                if (item == binding.bottomNavi.getMenu().getItem(2)) {
                    binding.vpgMain.setCurrentItem(2);
                }
                if (item == binding.bottomNavi.getMenu().getItem(3)) {
                    binding.vpgMain.setCurrentItem(3);
                }
                if (item == binding.bottomNavi.getMenu().getItem(4)) {
                    binding.vpgMain.setCurrentItem(4);
                }
                return true;
            }
        });


    }
}