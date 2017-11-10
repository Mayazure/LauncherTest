package com.example.mayazure.launchertest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
//import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;

public class DrawerActivity extends Activity {

    private List<ResolveInfo> apps;
    private GridView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        initWindow();
        getAppInfo();
        initViews();
    }

    private void initWindow(){
        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    private void initViews(){
        list = (GridView)findViewById(R.id.list);
        list.setAdapter(new AppsAdapter());
        list.setOnItemClickListener(listener);
    }

    private void getAppInfo(){
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        new ImageView(DrawerActivity.this);
        apps = getPackageManager().queryIntentActivities(intent, 0);
    }

    private OnItemClickListener listener = new OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ResolveInfo info = apps.get(i);
            //package name of that app
            String pkg = info.activityInfo.packageName;
            //main activity of that app
            String cls = info.activityInfo.name;
            ComponentName component = new ComponentName(pkg, cls);

            Intent intent = new Intent();
            intent.setComponent(component);
            startActivity(intent);
            DrawerActivity.this.finish();
        }
    };

    public class AppsAdapter extends BaseAdapter {

        public AppsAdapter(){
        }

        @Override
        public int getCount() {
            return apps.size();
        }

        @Override
        public Object getItem(int i) {
            return apps.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView iv;

            if(view == null){
                iv = new ImageView(DrawerActivity.this);
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setLayoutParams(new GridView.LayoutParams(200, 200));
            } else {
                iv = (ImageView) view;
            }
            ResolveInfo info = apps.get(i);
            iv.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
            return iv;
        }
    }
}
