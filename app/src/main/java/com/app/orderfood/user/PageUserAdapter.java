package com.app.orderfood.user;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.app.orderfood.user.account.UserAccountFragment;
import com.app.orderfood.user.action.UserActionFragment;
import com.app.orderfood.user.home.UserHomeFragment;
import com.app.orderfood.user.message.UserMessageFragment;
import com.app.orderfood.user.payment.UserPaymentFragment;

public class PageUserAdapter extends FragmentPagerAdapter {

    public PageUserAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UserHomeFragment();
            case 1:
                return new UserActionFragment();
            case 2:
                return new UserPaymentFragment();
            case 3:
                return new UserMessageFragment();
            case 4:
                return new UserAccountFragment();
            default:
                return new UserHomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
