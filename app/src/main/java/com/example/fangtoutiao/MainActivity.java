package com.example.fangtoutiao;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fangtoutiao.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private String[] titles = {"娱乐", "军事", "教育", "文化", "健康", "财经", "体育", "汽车", "科技"};
    private List<TitleInfo> titles = new ArrayList<>();
    private TabLayout tab_layout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化title数据
        titles.add(new TitleInfo("推荐","top"));
        titles.add(new TitleInfo("国内","guonei"));
        titles.add(new TitleInfo("国际","guoji"));
        titles.add(new TitleInfo("娱乐","yule"));
        titles.add(new TitleInfo("体育","tiyu"));
        titles.add(new TitleInfo("军事","junshi"));
        titles.add(new TitleInfo("科技","keji"));
        titles.add(new TitleInfo("财经","caijing"));
        titles.add(new TitleInfo("游戏","youxi"));
        titles.add(new TitleInfo("汽车","qiche"));
        titles.add(new TitleInfo("健康","jiankang"));
        //初始化控件
        tab_layout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);

        //viewPager需要设置adapter
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                String title = titles.get(position).getPy_title();
                TabNewsFragment tabNewsFragment = TabNewsFragment.newInstance(title);


                return tabNewsFragment;
            }

            @Override
            public int getItemCount() {

                return titles.size();
            }
        });

        //tab_layout点击事件
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置viewPager选中当前页
                viewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //tab_layout和viewPager关联在一起
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab_layout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position).getTitle());
            }
        });

        tabLayoutMediator.attach();

    }


}