package com.example.mayazure.launchertest.page;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import com.example.mayazure.launchertest.MainActivity;
import com.example.mayazure.launchertest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayazure on 3/11/2017.
 */

public class PageFragment extends Fragment {

    private Page page;
    private View view;

    private List<PageContent> contentList;
    private ViewPager vpPage;
    ScrollView sv;
    int mheight;

    public ViewPager getVpPage(){
        return vpPage;
    }

    public static PageFragment createPageFragment(Page page){
        PageFragment pageFragment = new PageFragment();
        Bundle paras = new Bundle();
        paras.putSerializable("page", page);
        pageFragment.setArguments(paras);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            page = (Page)getArguments().getSerializable("page");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.page_fragment, container, false);
//        TextView title = (TextView)view.findViewById(R.id.pagetitle);
//        title.setText("Page "+String.valueOf(page.getId()));

        PageAdapter pageAdapter = new PageAdapter(getChildFragmentManager());
        vpPage = (ViewPager) view.findViewById(R.id.vpPageContent);
        vpPage.setAdapter(pageAdapter);
        vpPage.setCurrentItem(2);



        Resources resources0 = this.getResources();
        int resourceId0 = resources0.getIdentifier("status_bar_height","dimen", "android");
        int status_height = resources0.getDimensionPixelSize(resourceId0);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
//            int width = dm.widthPixels/2;         // 屏幕宽度（像素）
        int height0 = dm.heightPixels-status_height;       // 屏幕高度（像素）

        LinearLayout.LayoutParams layoutParams0 = (LinearLayout.LayoutParams)vpPage.getLayoutParams();
        layoutParams0.height = height0;
        vpPage.setLayoutParams(layoutParams0);

        sv = (ScrollView)view.findViewById(R.id.homescroll);
        sv.scrollTo(0, -height0-100);
        sv.setVerticalScrollBarEnabled(false);
        System.out.println("Heightttttttttt:"+sv.getHeight());



        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Space spaceDown = (Space)view.findViewById(R.id.spaceDown);
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)spaceDown.getLayoutParams();
//        layoutParams.height = height;
//        spaceDown.setLayoutParams(layoutParams);

        resourceId = resources.getIdentifier("status_bar_height","dimen", "android");
        height = resources.getDimensionPixelSize(resourceId);
        Space spaceUp = (Space)view.findViewById(R.id.spaceUp);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)spaceUp.getLayoutParams();
        layoutParams.height = height;
        spaceUp.setLayoutParams(layoutParams);

        mheight = height0-height;

        //debug use
        Button rota = (Button)view.findViewById(R.id.rota);
        rota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("-----------------------height:"+PageFragment.this.sv.getHeight());
                sv.scrollTo(0,sv.getHeight());
            }
        });

        sv.setOnTouchListener(new View.OnTouchListener() {
            float mPosX=0;
            float mPosY=0;
            float curX=0;
            float curY=0;
            boolean on = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                System.out.println("--------ATouch;");
                System.out.println(event.getAction());
                ScrollView svv = (ScrollView)v;
                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        mPosX = event.getX();
//                        mPosY = event.getY();
//                        System.out.println("--------ADown;");
//                        System.out.println("down----"+mPosX+";"+curX+";"+mPosY+";"+curY);
//                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(!on){
                            mPosY=event.getY();
                            mPosX=event.getX();
                            on = true;
                        }
                        curX = event.getX();
                        curY = event.getY();
//                        svv.smoothScrollTo(0,(int)curY);
//                        System.out.prin tln("--------AMove;");
                        break;
                    case MotionEvent.ACTION_UP:
//                        System.out.println("--------AUp;"+Math.abs(curX-mPosX));
//                            System.out.println(mPosX+";"+curX+";"+mPosY+";"+curY);
                        if(Math.abs(curX-mPosX)<10000){

                            if(curY-mPosY>0&&Math.abs(curY-mPosY)>25){
                                //scroll down
                                svv.smoothScrollTo(0,0);
//                                System.out.println("--------Down;"+Math.abs(curY-mPosY));
                            }
                            else if(curY-mPosY<0&&Math.abs(curY-mPosY)>25){
                                //scroll up
//                                svv.setScrollY();
//                                svv.scrollTo(0,v.getHeight());
                                svv.smoothScrollTo(0,v.getHeight());
//                                svv.postInvalidate();
//                                System.out.println("--------Up;"+Math.abs(curY-mPosY));
                            }
                        }
                        on=false;

                }
                return true;
            }
        });

        return view;
    }

    private void initViewPager() {
//        contentList = new ArrayList<>();
//        contentList.add(new Page(1, "Page1"));
//        contentList.add(new Page(2, "Page2"));
//        contentList.add(new Page(3, "Page3"));

//        PageAdapter pageAdapter = new PageAdapter(getChildFragmentManager());
//        vpPage = (ViewPager) view.findViewById(R.id.vpPageContent);
//        vpPage.setAdapter(pageAdapter);
    }

    private class PageAdapter extends FragmentStatePagerAdapter {
//        List<Page> pageList;

        private PageAdapter(FragmentManager fm) {
            super(fm);
//            this.pageList = pageList;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Fragment getItem(int position) {
            return PageContent.createPageContent();
        }
    }


}
