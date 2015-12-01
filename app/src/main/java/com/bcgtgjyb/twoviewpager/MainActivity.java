package com.bcgtgjyb.twoviewpager;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OutSideViewPager mOutSideViewPager;
    private LinearLayout mLinearLayout;
    private ViewPager inViewPager;
    private List<View> outList=new ArrayList<View>();
    private List<View> inList=new ArrayList<View>();
    private MyPagerAdapter outPagerAdapter;
    private MyPagerAdapter inPagerAdapter;
    private View mChildPager;
    private ViewGroup linear;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    private View tab1;
    private View tab2;

    private View main_tab1;
    private View main_tab2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOutSideViewPager=(OutSideViewPager)findViewById(R.id.outside_viewpager);
        linear= (ViewGroup)LayoutInflater.from(this).inflate(R.layout.linear, null);
        textView1=(TextView)linear.findViewById(R.id.textView1);
        textView2=(TextView)linear.findViewById(R.id.textView2);
        textView3=(TextView)linear.findViewById(R.id.textView3);
        main_tab1=findViewById(R.id.main_tab1);
        main_tab2=findViewById(R.id.main_tab2);
        linear.removeAllViews();
        mLinearLayout=(LinearLayout)LayoutInflater.from(this).inflate(R.layout.view_pager1, null);
        inViewPager=(ViewPager)mLinearLayout.findViewById(R.id.viewpager);
        tab1=mLinearLayout.findViewById(R.id.tab1);
        tab2=mLinearLayout.findViewById(R.id.tab2);
        checkoutTab(0);
        checkoutMainTab(0);

        inList.add(textView1);
        inList.add(textView2);
        inPagerAdapter=new MyPagerAdapter(inList);
        inViewPager.setAdapter(inPagerAdapter);

        outList.add(mLinearLayout);
        outList.add(textView3);

        outPagerAdapter=new MyPagerAdapter(outList);
        mOutSideViewPager.setViewPager(inViewPager);
        mOutSideViewPager.setAdapter(outPagerAdapter);


        inViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                checkoutTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mOutSideViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                checkoutMainTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });







    }

    private void checkoutTab(int position){
        if(position==0){
            tab1.setBackgroundColor(Color.parseColor("#42bd41"));
            tab2.setBackgroundColor(Color.parseColor("#ffffff"));
        }else if(position==1){
            tab1.setBackgroundColor(Color.parseColor("#ffffff"));
            tab2.setBackgroundColor(Color.parseColor("#42bd41"));
        }
    }
    private void checkoutMainTab(int position){
        if(position==0){
            main_tab1.setBackgroundColor(Color.parseColor("#42bd41"));
            main_tab2.setBackgroundColor(Color.parseColor("#ffffff"));
        }else if(position==1){
            main_tab1.setBackgroundColor(Color.parseColor("#ffffff"));
            main_tab2.setBackgroundColor(Color.parseColor("#42bd41"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyPagerAdapter extends PagerAdapter{
        List<View> list;
        public MyPagerAdapter(List<View> l) {
            this.list=l;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
