package com.example.mayazure.launchertest.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mayazure.launchertest.R;

/**
 * Created by Mayazure on 4/11/2017.
 */

public class PageContent extends Fragment {

    private View view;

    public static PageContent createPageContent(){
        PageContent pageContent = new PageContent();
//        Bundle paras = new Bundle();
//        paras.putSerializable("page", page);
//        pageContent.setArguments(paras);
        return pageContent;
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
        view = inflater.inflate(R.layout.page_content_fragment, container, false);
//        TextView title = (TextView)view.findViewById(R.id.pagetitle);
//        title.setText("Page "+String.valueOf(page.getId()));
        return view;
    }

    public View mGetView(){
        return view;
    }
}
