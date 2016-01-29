package com.example.ktds.myapplication;
import android.app.ActionBar.*;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by KTDS on 2016-01-26.
 * by gahee.choi
 */
public class MainMenuTabActivity extends ActionBarActivity implements ActionBar.TabListener {
    //SectionsPagerAdapter : 특정 Page or Section, Tab  등 해당 fragment Return 해주는 library
    SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);


        // Set up the action bar : 화면 상단의 탭 선택 영역을 위한 액션바를 생성
        final ActionBar actionBar = getSupportActionBar();
        //액션바의 네비게이션 모드 중 탭을 표시
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three : SectionsPagerAdapter 생성자로 할당
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.: 어뎁터 연결해주기
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //ViewPager에 의해 특정 다른 page 선택 시 상단 탭도 함께 선택되게 연결 시키는 작업을 처리
        //
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            //ActionBar에 해당하는 Position's Tab 선택 되게 함.
            @Override
             public void onPageSelected(int position){
                actionBar.setSelectedNavigationItem(position);
            }
        });

        //mSectionsPagerAdapter에 save된  Section 수 만큼 액션바 Tab 생성 후 각 Tab 내용 해당 Page Title(숫자)로 채운다.
        for(int i=0; i < mSectionsPagerAdapter.getCount(); i++){
            actionBar.addTab(actionBar.newTab().setText( mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this) );
        }
    }
    //Menu Key click 시 Menu 출력 위한 구현.
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // res/menu/menu.xml에 정의된 메뉴 출력. ActionBar에 Menu 추가 하는 것도 이곳에서 구현
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */

    //상단 Tab을 선택 시 해당 Page 출력 위해 onTabSelected Override
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //선택된 Tab에 해당하는 Page로 전환
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    //상위에 선언된 SectionsPagerAdapter 클래스의 구현. FragmentPagerAdapter 상속받아 생성.
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        //각 page fragment 초기화 시 사용
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            Bundle args = null;
            switch (position){
                case 0:
                    fragment = new SectionsFragment1();
                    args = new Bundle();
                    //return SectionsFragment1.newInstance(position + 1);
                    break;
                case 1:
                    fragment = new SectionsFragment2();
                    args = new Bundle();
                    break;
                case 2:
                    fragment = new SectionsFragment3();
                    args = new Bundle();
                    break;
            }
            return fragment;
        }

        //page 갯수: 3개 선언
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_tab1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_tab2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_tab3).toUpperCase(l);
            }
            return null;
        }
    }

    //PlaceholderFragment  class: 각 page 실질적으로 채우는 fragment view 역할
    public static class PlaceholderFragment extends Fragment{
        private static final String ARG_SECTION_NUMBER = "section_number";

        //getItem()에서 탭에 보여질 page 생성&호출되는 newInstance() 정의.
        //fragment를 생성하고 bundle과 연계시켜 해당 fragment 반환
        public static  PlaceholderFragment newInstance(int sectionNumber){
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment(){}

        //fragment가 보여질 때 초기화하기 위해 호출되는 method
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            //R.layout.fragment_main에 정의된 TextView에 섹션 번호를 설정
            View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
            return rootView;
        }
    }

    public static class SectionsFragment1 extends Fragment {

        public SectionsFragment1() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.tab_menu_info, container, false);

            final ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("사과");
            arrayList.add("쥬스당");

            final ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);

            ListView lv = (ListView) rootView.findViewById(R.id.listView);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    String str = arrayList.get(position);
                    String a = str + " 선택";
                    Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();
                }
            });
            return rootView;
        }
    }


    public static class SectionsFragment2 extends Fragment {

        public SectionsFragment2() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tab_shop_info, container, false);
            TextView tv = (TextView) rootView.findViewById(R.id.section_label1);
            tv.setText("섹션2");
            return rootView;
        }
    }

    public static class SectionsFragment3 extends Fragment {

        public SectionsFragment3() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tab_review, container, false);
            TextView tv = (TextView) rootView.findViewById(R.id.section_label2);
            tv.setText("섹션3");
            return rootView;
        }
    }
}


