package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cxhll.huifenq.Adapters.Adapt;
import com.example.cxhll.huifenq.Adapters.RoleAdapter;
import com.example.cxhll.huifenq.Adapters.UserAdapter;
import com.example.cxhll.huifenq.Adapters.WaresAdapter;
import com.example.cxhll.huifenq.Adapters.orderAdapt;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.Message;
import com.example.cxhll.huifenq.item.OrderListItem;
import com.example.cxhll.huifenq.item.RoleItem;
import com.example.cxhll.huifenq.item.UserItem;
import com.example.cxhll.huifenq.item.WaresItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SeachActivity extends AppCompatActivity {
    String TAG = "SeachActivity.class";
    Intent intent;
    private ArrayList<OrderListItem> orderlist = new ArrayList<>();
    private ArrayList<Message> messagelist = new ArrayList<>();
    private ArrayList<WaresItem> warelist = new ArrayList<>();
    private ArrayList<RoleItem> rolelist = new ArrayList<>();
    private ArrayList<UserItem> userlist = new ArrayList<>();
    private ListView lisview;
    private orderAdapt orderadapt;
    private Adapt messadapt;
    private WaresAdapter waresadapt;
    private RoleAdapter roleadapt;
    private HuifqDbHelper dbHelper;
    private UserAdapter useradapt;
    private SearchView seach;
    private ImageView seachback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);

        dbHelper = new HuifqDbHelper(SeachActivity.this, "huifenqi.db", null, 5);
        lisview = (ListView) findViewById(R.id.seach_list);
        intent = getIntent();
        seachback = (ImageView) findViewById(R.id.seach_back);
        seachback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        seach = (SearchView) findViewById(R.id.search_view);
        seach.setIconifiedByDefault(false);
        seach.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                try {
                    Log.d(TAG, "onQueryTextSubmit: 传过来在值为" + intent.getStringExtra("info"));
                    if (intent.getStringExtra("info").equals("1")) {
                        Log.d(TAG, "onQueryTextSubmit: 1?");
                        setOrderList();
                    }
                    if (intent.getStringExtra("info").equals("2")) {
                        Log.d(TAG, "onQueryTextSubmit: 2?");
                        setUserList();
                    }
                    if (intent.getStringExtra("info").equals("3")) {
                        Log.d(TAG, "onQueryTextSubmit: 3?");
                        setMessage();
                    }
                    if (intent.getStringExtra("info").equals("4")) {
                        Log.d(TAG, "onQueryTextSubmit: 4?");
                    }
                    if (intent.getStringExtra("role").equals("5")) {
                        setRole();
                        Log.d(TAG, "onQueryTextSubmit: 5?");
                    }
                    if (intent.getStringExtra("info").equals("6")) {
                        Log.d(TAG, "onQueryTextSubmit: 6?");
                        try {
                            seach.setQueryHint("搜索您想要的商品");
                            Log.d(TAG, "onQueryTextSubmit: 6?1");
                            setWare();
                            Log.d(TAG, "onQueryTextChange: 搜索商品");
                        } catch (NullPointerException e) {
                        }
                    }
                } catch (NullPointerException e) {
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange: " + seach.getQuery());
                try {
                    if (intent.getStringExtra("info").equals("1")) {
                        setOrderList();
                    }
                    if (intent.getStringExtra("info").equals("2")) {
                        setUserList();
                    }
                    if (intent.getStringExtra("info").equals("3")) {
                        setMessage();
                    }
                    if (intent.getStringExtra("info").equals("4")) {

                    }
                    if (intent.getStringExtra("info").equals("5")) {
                        setRole();
                    }
                    if (intent.getStringExtra("info").equals("6")) {
                        try {
                            setWare();
                            Log.d(TAG, "onQueryTextChange: 搜索商品");
                        } catch (NullPointerException e) {
                        }
                    }
                } catch (NullPointerException e) {
                }
                return false;
            }
        });

    }

    public void setOrderList() {

        try {

            orderlist = dbHelper.select_orders(dbHelper, "1", seach.getQuery().toString());

        } catch (NullPointerException e) {

        }
        orderadapt = new orderAdapt(SeachActivity.this, R.layout.orderlist, orderlist);
        lisview.setAdapter(orderadapt);
        orderadapt.notifyDataSetInvalidated();
    }

    public void setUserList() {
        try {

            userlist = dbHelper.select_useridList(dbHelper, seach.getQuery().toString());

        } catch (NullPointerException e) {
        }

        useradapt = new UserAdapter(this, R.layout.user_item, userlist);
        lisview.setAdapter(orderadapt);
        orderadapt.notifyDataSetInvalidated();
    }

    public void setMessage() {
        try {
            messagelist = dbHelper.select_messseach(dbHelper, seach.getQuery().toString());
        } catch (NullPointerException e) {

        }
        messadapt = new Adapt(this, R.layout.messitem_layout, messagelist);
        lisview.setAdapter(messadapt);

    }

    public void setRole() {
        try {
            rolelist = dbHelper.select_idrolelist(dbHelper, seach.getQuery().toString());
        } catch (NullPointerException e) {
        }
        roleadapt = new RoleAdapter(this, R.layout.rm_items, rolelist);
        lisview.setAdapter(roleadapt);
    }

    public void setWare() {
        Log.d(TAG, "onQueryTextSubmit: 6?ware");
        try {
            Log.d(TAG, "onQueryTextSubmit: 6?try");
            warelist = dbHelper.checkWaresp(seach.getQuery().toString(), dbHelper);
        } catch (NullPointerException e) {

        }
        waresadapt = new WaresAdapter(this, R.layout.mc_item, warelist);
        lisview.setAdapter(waresadapt);
    }
}
