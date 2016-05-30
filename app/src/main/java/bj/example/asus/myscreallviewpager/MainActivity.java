package bj.example.asus.myscreallviewpager;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int msg_show_footer_view = 1;
    private int[] imageRes = new int[]{R.mipmap.icon_1, R.mipmap.icon_2,
            R.mipmap.icon_3, R.mipmap.icon_4};

    private LinearLayout ll_top_news_viewpager;
    private CycleViewPager mPager;
    private TextView tv_top_news_title;
    private LinearLayout ll_dots;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
//    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        ll_top_news_viewpager = (LinearLayout) findViewById(R.id.ll_top_news_viewpager);
        mPager = (CycleViewPager) findViewById(R.id.viewpager);
        tv_top_news_title = (TextView) findViewById(R.id.tv_top_news_title);
        mPager = (CycleViewPager) findViewById(R.id.viewpager);
        final MyAdapter adapter = new MyAdapter();
        mPager.setAdapter(adapter);


        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);
        addPoints();
        mPager.setOnPageChangeListener(new SimpleOnMyPageChangeListener());

    }


    private final class SimpleOnMyPageChangeListener extends ViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            //当轮播图切换时,需要改变标题和点
//            tv_top_news_title.setText(topTitles.get(position));
            //点
            for (int i = 0; i < dots.size(); i++) {
                if(i == position-1){
//                    Log.i("NewsItemPager", "切换点:"+position);
                    dots.get(i).setEnabled(false);
                }else{
                    dots.get(i).setEnabled(true);
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://bj.example.asus.myscreallviewpager/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://bj.example.asus.myscreallviewpager/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageRes.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getApplicationContext());
            container.addView(imageView);
            imageView.setImageResource(imageRes[position]);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    //点图片的集合
    private List<ImageView> dots = new ArrayList<ImageView>();

    /**
     * 给ll_dots添加点
     */
    private void addPoints() {
        ll_dots.removeAllViews();//防止parseJson执行两次
        dots.clear();
        for (int i = 0; i < imageRes.length; i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(R.drawable.dot_selector);
            //默认选择第一个点
            if (i == 0) {
                imageView.setEnabled(false);
            }
            //添加到线性布局中
            //添加到线性布局中
            ViewGroup.LayoutParams params  = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.width=25;

            ll_dots.addView(imageView, params);
            dots.add(imageView);
        }

    }

}
