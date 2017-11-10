package com.example.mayazure.launchertest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.example.mayazure.launchertest.page.DrawerFragment;
import com.example.mayazure.launchertest.page.Page;
import com.example.mayazure.launchertest.page.PageFragment;
import com.example.mayazure.launchertest.page.SettingFragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public class MainActivity extends AppCompatActivity {

//    private TextView console;
//    private Button drawerButton;

    //Test ViewPager
    private List<Page> pageList;
    private ViewPager vpPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWindow();
//        getAppInfo();
        initViews();
        initViewPager();

//        getNavHeight();
    }

    private void initViewPager() {
        pageList = new ArrayList<>();
        pageList.add(new Page(1, "Page1"));
        pageList.add(new Page(2, "Page2"));
        pageList.add(new Page(3, "Page3"));

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), pageList);
        vpPage = (ViewPager) findViewById(R.id.vpPage);
        vpPage.setAdapter(pageAdapter);
        vpPage.setCurrentItem(1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == 2) {
            //openStatusBar();
        }

        String eventString = String.valueOf(action);

//        console.setText(eventString);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initWindow() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setNavigationBarColor(Color.TRANSPARENT);
//        View decorView = window.getDecorView();
//        int option = SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//        decorView.setSystemUiVisibility(option);

    }

    public ViewPager getVpPage() {
        return vpPage;
    }

    private void initViews() {
//        console = (TextView) findViewById(R.id.console);
//        drawerButton = (Button) findViewById(R.id.drawerButton);
//
//        drawerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, DrawerActivity.class);
//                MainActivity.this.startActivity(intent);
////                MainActivity.this.finish();
//            }
//        });
    }

    //get statusbar using reflection since it's hiden in API
    private void openStatusBar() {
        Object service = getSystemService("statusbar");
        try {
            Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
            Method expand = statusbarManager.getMethod("expandNotificationsPanel");
            expand.invoke(service);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private class PageAdapter extends FragmentStatePagerAdapter {
        List<Page> pageList;

        private PageAdapter(FragmentManager fm, List<Page> pageList) {
            super(fm);
            this.pageList = pageList;
        }

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return SettingFragment.createPageContent();
            }
            else if(position == 2){
                return DrawerFragment.createDrwaerFragment();
            }

            return PageFragment.createPageFragment(pageList.get(position));

//            Resources resources = MainActivity.this.getResources();
//            int resourceId = resources.getIdentifier("status_bar_height","dimen", "android");
//            int status_height = resources.getDimensionPixelSize(resourceId);
//            WindowManager wm = (WindowManager) MainActivity.this.getSystemService(Context.WINDOW_SERVICE);
//            DisplayMetrics dm = new DisplayMetrics();
//            wm.getDefaultDisplay().getMetrics(dm);
////            int width = dm.widthPixels/2;         // 屏幕宽度（像素）
//            int height = dm.heightPixels-status_height;       // 屏幕高度（像素）
//
//            PageFragment pf = PageFragment.createPageFragment(pageList.get(position));
//            ViewPager vp = pf.getVpPage();
//
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)vp.getLayoutParams();
//            layoutParams.height = height;
//            vp.setLayoutParams(layoutParams);
//
//            return pf;
        }
    }

    //app list operation

//    private List<ResolveInfo> apps;
//    private GridView list;
//
//    private void getAppInfo(){
//        Intent intent = new Intent(Intent.ACTION_MAIN, null);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        new ImageView(MainActivity.this);
//        apps = getPackageManager().queryIntentActivities(intent, 0);
//    }
//
//    public class AppsAdapter extends BaseAdapter {
//
//        public AppsAdapter(){
//        }
//
//        @Override
//        public int getCount() {
//            return apps.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return apps.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            ImageView iv;
//
//            if(view == null){
//                iv = new ImageView(MainActivity.this);
//                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                iv.setLayoutParams(new GridView.LayoutParams(200, 200));
//            } else {
//                iv = (ImageView) view;
//            }
//            ResolveInfo info = apps.get(i);
//            iv.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
//            return iv;
//        }
//    }
}