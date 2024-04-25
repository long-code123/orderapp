package com.app.orderfood.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.app.orderfood.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.vp2.setAdapter(new PageFragment(MainActivity.this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tlMenu, binding.vp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Home");
                        break;
                    case 1:
                        tab.setText("action");
                        break;
                    case 2:
                        tab.setText("payment");
                        break;
                    case 3:
                        tab.setText("message");
                        break;
                    case 4:
                        tab.setText("account");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
        binding.vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //BadgeDrawable badgeDrawable = binding.tlMenu.getTabAt(position).getOrCreateBadge();
                //badgeDrawable.setVisible(false);
            }
        });
        binding.tlMenu.getTabAt(1).select();

    }
}