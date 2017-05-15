package com.example.cxhll.huifenq.Activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cxhll.huifenq.Adapters.ChoseOverdueAdapter;
import com.example.cxhll.huifenq.Adapters.NewOrderWaresAdapter;
import com.example.cxhll.huifenq.Adapters.ShowOverdueAdapter;
import com.example.cxhll.huifenq.Adapters.UserAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.ChoseOverdueIem;
import com.example.cxhll.huifenq.item.ShowOverdueIem;
import com.example.cxhll.huifenq.item.UserItem;
import com.example.cxhll.huifenq.item.WaresItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CXHLL on 2017/1/18.
 */

public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public HuifqDbHelper dbHelper;
    String TAG = "BaseActivity.class";
    Intent intentX;//添加
    Intent intentY;//搜索
    int modle;
    MenuItem chose;
    String SQL = "";
    ListView chose_listView;
    ShowOverdueAdapter showOverdueAdapter_base;
    ChoseOverdueAdapter choseOverdueAdapter_base;
    ArrayList<ShowOverdueIem> showOverdueIemArrayList_base;
    ArrayList<ChoseOverdueIem> choseOverdueIemArrayList_base;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setDbHelper(BaseActivity.this);
        super.onCreate(savedInstanceState);
    }

    public void setBar(String title, int type) {
        /**
         * type 含义
         * 0.显示add，显示search，不显示reader
         * 1.显示search不显示add，不显示reader
         * 2.不显示search，不显示add，不显示reader
         * 3.显示reader，其余不显示
         * 4.只显示add
         * 5.只显示完成
         * 6.只显示设置
         * 7.开始选择
         * 8.选择结束
         */


        modle = type;
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void setDbHelper(Context context) {
        dbHelper = new HuifqDbHelper(context, "huifenqi.db", null, 5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.finshmenu, menu);
        MenuItem seach = menu.getItem(0);
        MenuItem address = menu.getItem(4);
        MenuItem add = menu.getItem(1);
        final MenuItem finish = menu.getItem(3);
        MenuItem reader = menu.getItem(2);
        chose = menu.getItem(5);

        if (modle == 0) {

            try {
                finish.setVisible(false);
                reader.setVisible(false);
                chose.setVisible(false);

                address.setVisible(false);
                add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        startActivity(intentX);

                        return true;

                    }
                });


                seach.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        startActivity(intentY);
                        Log.d(TAG, "onMenuItemClick: 点击了搜索");
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        } else if (modle == 1) {
            try {
                add.setVisible(false);
                reader.setVisible(false);
                finish.setVisible(false);
                address.setVisible(false);
                chose.setVisible(false);

                seach.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        startActivity(intentY);
                        return false;
                    }
                });
            } catch (Exception e) {
            }
        } else if (modle == 2) {
            try {
                add.setVisible(false);
                seach.setVisible(false);
                address.setVisible(false);
                chose.setVisible(false);

                finish.setVisible(false);
                reader.setVisible(false);
            } catch (Exception e) {
            }
        } else if (modle == 3) {
            try {
                address.setVisible(false);
                chose.setVisible(false);

                add.setVisible(false);
                seach.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        startActivity(intentY);
                        return false;
                    }
                });
                finish.setVisible(false);
                reader.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        startActivity(intentX);
                        return false;
                    }
                });
            } catch (Exception e) {
            }
        } else if (modle == 4) {
            try {
                add.setVisible(true);
                seach.setVisible(false);
                chose.setVisible(false);

                address.setVisible(false);
                finish.setVisible(false);
                reader.setVisible(false);
                add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        startActivity(intentX);
                        Log.d(TAG, "onMenuItemClick: add被点击 了");
                        return false;
                    }
                });
            } catch (Exception e) {
            }
        } else if (modle == 5) {
            try {
                add.setVisible(false);
                seach.setVisible(false);
                chose.setVisible(false);

                address.setVisible(false);
                finish.setVisible(true);
                reader.setVisible(false);
                finish.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        finish();

                        return false;
                    }
                });
            } catch (Exception e) {
            }
        } else if (modle == 6) {
            try {
                add.setVisible(false);
                seach.setVisible(false);
                chose.setVisible(false);

                finish.setVisible(false);
                reader.setVisible(false);
                address.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Log.d(TAG, "onMenuItemClick:我被点了，但是为什么不启动 ");
                        startActivity(intentY);

                        return false;
                    }
                });
            } catch (Exception e) {
            }
        } else if (modle == 7) {
            add.setVisible(false);
            seach.setVisible(false);
            address.setVisible(false);

            finish.setVisible(false);
            reader.setVisible(false);
            chose_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (chose.getTitle().toString().equals("选择")) {
                      //  ShowOverdueIem item = showOverdueIemArrayList_base.get(position);
                   //     intentX.putExtra("name", item.getName());
                        startActivity(intentX);
                    }
                }
            });
            chose.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Log.d(TAG, "onMenuItemClick:我被点了，但是为什么不启动 ");

                    if (chose.getTitle().toString().equals("选择")) {
                        chose_listView.setAdapter(choseOverdueAdapter_base);
                        chose.setTitle("分配");
                    } else if (chose.getTitle().toString().equals("分配")) {

                        setWareList();
                        chose.setTitle("完成");

                    } else {
                        showOverdueIemArrayList_base = dbHelper.selectShowOverList(dbHelper);

                        showOverdueAdapter_base = new ShowOverdueAdapter(BaseActivity.this, R.layout.check_ovverdue_custimes_item, showOverdueIemArrayList_base);
                        chose.setTitle("选择");
                        chose_listView.setAdapter(showOverdueAdapter_base);
                    }

                    return false;


                }
            });


        }


        return true;

    }

    public void setIntents(Intent intent, Intent intent1) {
        this.intentX = intent;
        this.intentY = intent1;
    }

    public void setWareList() {

        LayoutInflater inflater = getLayoutInflater();
        final View dia = inflater.inflate(R.layout.custolist, (ViewGroup) findViewById(R.id.custolist));
        final ListView textView = (ListView) dia.findViewById(R.id.custolist);
        ArrayList<WaresItem> lists;

        lists = new ArrayList<WaresItem>();

        ArrayList<UserItem> mUserinfos = new ArrayList<UserItem>();
        HashMap<Object, Object> infos = dbHelper.select_userList(dbHelper, "null");
        mUserinfos = (ArrayList<UserItem>) infos.get("userlist");
        UserAdapter userAdapter = new UserAdapter(BaseActivity.this, R.layout.user_item, mUserinfos);
        lists = dbHelper.selectWares("null", dbHelper);

        final TextView ifnote = (TextView) dia.findViewById(R.id.ifyounote);

        ifnote.setText("您的子账号列表为空");
        textView.setAdapter(userAdapter);

        final ArrayList<UserItem> finalMUserinfos = mUserinfos;
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserItem item = finalMUserinfos.get(position);
                String usdrs_id = item.getmUiname();
                SQL = usdrs_id;
            }
        });
        final AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                run(SQL);
            }
        });
        textView.setEmptyView(ifnote);
        builder.setTitle("负责人");
        builder.setView(dia);
        builder.show();
    }

    private void run(String name) {
        int a = 0;
        for (ChoseOverdueIem item : choseOverdueIemArrayList_base) {
            if (item.isCheck()) {
                a++;
                Log.d(TAG, "run: " + item.getName() + "被选中了");
                dbHelper.update_overdue(dbHelper, name, item.getId());
            }

        }
        Snackbar.make(toolbar, "分配完成", Snackbar.LENGTH_SHORT).show();
    }


}
