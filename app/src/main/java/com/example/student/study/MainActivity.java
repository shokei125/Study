package com.example.student.study;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private ListView mListStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListStudy = (ListView) findViewById(R.id.listStudy);//listener

        List<String> studyLst = new ArrayList<String>();
        studyLst.add("ライフサイクル");
        studyLst.add("Service");
        studyLst.add("ContentProvider");
        studyLst.add("Sqlite3");

        StudySampleAdappter adapter = new StudySampleAdappter(this);
        adapter.addList(studyLst);
        //Viewに渡す
//        ListAdapter adapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, studyLst);

        //set
        mListStudy.setAdapter(adapter);

    }

    public class StudySampleAdappter extends BaseAdapter {

        private LayoutInflater _layoutInflater;
        private Context _context;
        private List<String> _list;

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
        public void add(String data) {
            _list.add(data);
            notifyDataSetChanged();
        }

        /**
         * リストデータの追加(リスト一式)
         *
         * @param dataList
         */
        public void addList(List<String> dataList) {
            for (String data : dataList) {
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
            String title = (String) getItem(position);

            //データの設定
            holder.txtTitle.setText(title);
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
