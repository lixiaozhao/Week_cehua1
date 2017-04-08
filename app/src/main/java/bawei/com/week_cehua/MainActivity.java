package bawei.com.week_cehua;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> titles = new ArrayList<>();
    private ImageView iv;
    private ListView lv;
    private List<String> datas = new ArrayList<>();
    private SlidingMenu menu;
    private TabLayout tab;
    private ViewPager vp;
    private LayoutInflater inflater;
    private List<View> views = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SlidingMenu slidingMenu = new SlidingMenu(this);
        //从左边滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置整个屏幕都可以滑出
        // slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置整个屏幕都不可以滑出菜单
        // slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置主屏幕滑出的宽度
        int widthPixels = this.getResources().getDisplayMetrics().widthPixels;
        slidingMenu.setBehindOffset(widthPixels/3*2);
        slidingMenu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.menu);
        //   slidingMenu.toggle();
        ImageView iv = (ImageView) findViewById(R.id.iv);
        ListView lv = (ListView) findViewById(R.id.lv);

        //侧滑页面数据源
        for (int i = 0; i < 10; i++) {
            datas.add("条目：" + i);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        lv.setAdapter(adapter);
        //侧滑页面条目点击
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "侧滑里的条目点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        //点击图片显示侧滑
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggle();
            }
        });
        //首页内容
        inflater = getLayoutInflater().from(this);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        //添加页卡标题
        titles.add("NO:1");
        titles.add("NO:2");
        titles.add("NO:3");
        titles.add("NO:4");
        titles.add("NO:5");
        View view1 = inflater.inflate(R.layout.page, null);
        View view2 = inflater.inflate(R.layout.page, null);
        View view3 = inflater.inflate(R.layout.page, null);
        View view4 = inflater.inflate(R.layout.page, null);
        View view5 = inflater.inflate(R.layout.page, null);
        //添加页卡视图
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);

        //设置tab模式 当前为系统默认模式
        tab.setTabMode(TabLayout.MODE_FIXED);
        //添加选项卡
        tab.addTab(tab.newTab().setText(titles.get(0)));
        tab.addTab(tab.newTab().setText(titles.get(1)));
        tab.addTab(tab.newTab().setText(titles.get(2)));
        tab.addTab(tab.newTab().setText(titles.get(3)));
        tab.addTab(tab.newTab().setText(titles.get(4)));
        MyPagerAdapter adapter1 = new MyPagerAdapter();
        //给ViewPager设置适配器
        vp.setAdapter(adapter1);
        //将tablayout和viewpager关联起来
        tab.setupWithViewPager(vp);
        tab.setTabsFromPagerAdapter(adapter1);

    }

    //viewpager适配器
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //添加页卡
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //删除页卡
            container.removeView(views.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
