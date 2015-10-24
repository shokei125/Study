package com.example.student.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class MainActivity extends ActionBarActivity {

    private ListView mListStudy;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListStudy = (ListView) findViewById(R.id.listStudy);//listener
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeListStudy);//listener

        final List<StudyItem> studyLst = new ArrayList<>();

        studyLst.add(new StudyItem("ライフサイクル",LifecycleActivity.class));
        studyLst.add(new StudyItem("1231231",null));
        studyLst.add(new StudyItem("alskdjf;lakj",null));
        studyLst.add(new StudyItem(";lkj;123kj;lk21j3",null));
//        studyLst.add("Service");
//        studyLst.add("ContentProvider");
//        studyLst.add("Sqlite3");

        StudySampleAdappter adapter = new StudySampleAdappter(this);
        adapter.addList(studyLst);
        //Viewに渡す
//        ListAdapter adapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, studyLst);

        //set
        mListStudy.setAdapter(adapter);
        mListStudy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudyItem item = studyLst.get(position);

                Intent intent = new Intent(MainActivity.this,item.getActivityClass());
                startActivity(intent);
            }
        });
    }

    class StudyItem{
        private String _title;
        private Class<?> _activityClass;

        public String getTitle(){
            return _title;
        }

        public Class<?> getActivityClass(){
            return  _activityClass;
        }

        public StudyItem(String title,Class<?> activityClass){
            _title = title;
            _activityClass = activityClass;
        }
    }

    public class StudySampleAdappter extends BaseAdapter {

        private LayoutInflater _layoutInflater;
        private Context _context;
        private List<StudyItem> _list;



        /**
         * コンストラクタ
         *
         * @param context
         */
        public StudySampleAdappter(Context context) {
            _context = context;
            _list = new ArrayList();
            _layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        /**
         * リストデータの追加(単)
         *
         * @param data
         */
        public void add(StudyItem data) {
            _list.add(data);
            notifyDataSetChanged();
        }

        /**
         * リストデータの追加(リスト一式)
         *
         * @param dataList
         */
        public void addList(List<StudyItem> dataList) {
            for (StudyItem data : dataList) {
                add(data);
            }
        }

        /**
         * リストの個数
         *
         * @return
         */
        @Override
        public int getCount() {
            return _list.size();
        }

        /**
         * 指定位置のアイテムを返す
         *
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return _list.get(position);
        }

        /**
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 画面の表示情報を返す
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
           //インスタンスの取得
            if(convertView == null) {
                convertView = _layoutInflater.inflate(R.layout.study_list_item, null);
                holder = new Holder();
                holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
            }
            //データの取得
            StudyItem item = (StudyItem) getItem(position);

            //データの設定
            holder.txtTitle.setText(item._title);
            return convertView;
        }

        private class Holder{
            TextView txtTitle;
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
}
