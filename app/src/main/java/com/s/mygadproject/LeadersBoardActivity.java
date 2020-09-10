package com.s.mygadproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class LeadersBoardActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Button sumbmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializer();
        sumbmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeadersBoardActivity.this,SubmitActivity.class);
                startActivity(intent);
            }
        });
        setupViewPager (viewPager);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.getTabAt(0).setIcon(R.drawable.submit_tab);
//        tabLayout.getTabAt(1).setText("Skill IQ Leaders");
    }


    private void intializer() {
        viewPager = (ViewPager) findViewById(R.id.slideView);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        sumbmitBtn=(Button) findViewById(R.id.submit_btn);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter sectionPageAdapter =new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(LearningLeaderFragment.newInstance(), "Learning Leaders");
        sectionPageAdapter.addFragment(SkillIQLeaderFragment.newInstance(), "Skill IQ Leaders");
        viewPager.setAdapter(sectionPageAdapter);

    }



    private class SectionPageAdapter extends FragmentPagerAdapter{
        private  final List<Fragment> fragments = new ArrayList<>();
        private final List<String> stringOne= new ArrayList<>();

        public SectionPageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        void addFragment(Fragment fm, String title){
            fragments.add(fm);
            stringOne.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return (CharSequence) stringOne.get(position);
        }
    }
}