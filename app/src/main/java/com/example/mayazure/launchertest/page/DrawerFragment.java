package com.example.mayazure.launchertest.page;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;

import com.example.mayazure.launchertest.DrawerActivity;
import com.example.mayazure.launchertest.MainActivity;
import com.example.mayazure.launchertest.R;

import java.util.List;

/**
 * Created by Mayazure on 4/11/2017.
 */

public class DrawerFragment extends Fragment {

    public static DrawerFragment createDrwaerFragment(){
        DrawerFragment drawerFragment = new DrawerFragment();
//        Bundle paras = new Bundle();
//        paras.putSerializable("page", page);
//        pageContent.setArguments(paras);
        return drawerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getAppInfo();
//        if(getArguments()!=null){
//            page = (Page)getArguments().getSerializable("page");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.drawer_fragment, container, false);
        list = (GridView)view.findViewById(R.id.drawer_fragment_list);
        list.setAdapter(new AppsAdapter());
//        list.setOnItemClickListener(listener);


        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Space spaceDown = (Space)view.findViewById(R.id.spaceDown);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)spaceDown.getLayoutParams();
        layoutParams.height = height;
        spaceDown.setLayoutParams(layoutParams);

        resourceId = resources.getIdentifier("status_bar_height","dimen", "android");
        height = resources.getDimensionPixelSize(resourceId);
        Space spaceUp = (Space)view.findViewById(R.id.spaceUp);
        layoutParams = (LinearLayout.LayoutParams)spaceUp.getLayoutParams();
        layoutParams.height = height;
        spaceUp.setLayoutParams(layoutParams);

        return view;
    }


    private List<ResolveInfo> apps;
    private GridView list;

    private void getAppInfo(){
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        new ImageView(getActivity());
        apps = getActivity().getPackageManager().queryIntentActivities(intent, 0);
    }

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
                iv = new ImageView(getActivity());
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);

                WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
                DisplayMetrics dm = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(dm);
                int width = dm.widthPixels/4;         // 屏幕宽度（像素）
                int height = dm.heightPixels/2*8/10;       // 屏幕高度（像素）

                iv.setLayoutParams(new GridView.LayoutParams(width, 200));
            } else {
                iv = (ImageView) view;
            }
            ResolveInfo info = apps.get(i);
            iv.setImageDrawable(info.activityInfo.loadIcon(getActivity().getPackageManager()));
            return iv;
        }
    }

}
