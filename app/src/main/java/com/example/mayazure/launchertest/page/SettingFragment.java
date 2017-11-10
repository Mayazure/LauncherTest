package com.example.mayazure.launchertest.page;

import android.content.Context;
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

import com.example.mayazure.launchertest.R;

/**
 * Created by Mayazure on 4/11/2017.
 */

public class SettingFragment extends Fragment {

    public static SettingFragment createPageContent(){
        SettingFragment settingFragment = new SettingFragment();
//        Bundle paras = new Bundle();
//        paras.putSerializable("page", page);
//        pageContent.setArguments(paras);
        return settingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        if(getArguments()!=null){
//            page = (Page)getArguments().getSerializable("page");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
//        TextView title = (TextView)view.findViewById(R.id.pagetitle);
//        title.setText("Page "+String.valueOf(page.getId()));
        GridView list = (GridView) view.findViewById(R.id.setting_fragment_list);
        list.setAdapter(new AppsAdapter());

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

    public class AppsAdapter extends BaseAdapter {

        public AppsAdapter(){
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int i) {
            return PageContent.createPageContent();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ImageView imageview; // 声明ImageView的对象

            if (view == null) {
                imageview = new ImageView(getActivity()); //
                imageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE); //

                WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
                DisplayMetrics dm = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(dm);
                int width = dm.widthPixels/2;         // 屏幕宽度（像素）
                int height = dm.heightPixels/2*8/10;       // 屏幕高度（像素）

                imageview.setLayoutParams(new GridView.LayoutParams(width, height));


//                imageview.setScaleType();
                int p = width/20;
                int p2 = height/60;
                imageview.setPadding(p,p2,p,p2); //
            } else {
                imageview = (ImageView) view;
            }
            imageview.setImageResource(R.drawable.page); //
            return imageview; // 返回ImageView
        }

    }
}