package com.xiaoxin.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xiaoxin.demo.R;
import com.xiaoxin.demo.adapter.LeftMenuAdapter;
import com.xiaoxin.demo.base.BaseActivity;
import com.xiaoxin.demo.global.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.bnb_main)
    BottomNavigationBar mBnbMain;
    //    @BindView(R.id.navigation)
//    NavigationView mNavigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.rv_left)
    RecyclerView mRecyclerView;
    private HomeFragment mHomeFragment;
    private BookFragment mBookFragment;
    private MusicFragment mMusicFragment;
    private TvFragment mTvFragment;
    private GameFragment mGameFragment;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
//        setDrawerLayout();
        mList.add("94电影年");
        mList.add("96黄金一代");
        mList.add("03白金一代");

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final LeftMenuAdapter menuAdapter = new LeftMenuAdapter(this, mList);
        //设置菜单栏
        mRecyclerView.setAdapter(menuAdapter);

        setBottomNavigationBar();

        setDefaultFragment();
        mBnbMain.setTabSelectedListener(this);

        mToolbar.setTitle("111");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.menu);
        //点击按钮弹出左边侧滑栏
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //点击每个菜单条目
        menuAdapter.setOnMenuClickListener(new LeftMenuAdapter.OnMenuClickListener() {
            @Override
            public void onClick(int position) {
                menuAdapter.updateItem(position);
                Intent intent = new Intent(Constant.ACTION.UPDATE_LIST);
                intent.putExtra(Constant.TYPE, position);
                sendBroadcast(intent);
                mDrawerLayout.closeDrawer(GravityCompat.START);//关闭菜单栏
            }
        });
    }

    private void setDrawerLayout() {
        //设置DrawerLayout
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
        };
        drawerToggle.syncState();
        mDrawerLayout.setDrawerListener(drawerToggle);
        //获取头布局内部的控件.在旧版本中，假如你要获得 NavigationView 中的文本控件，你可以在 activity 中直接调用 findViewById() 方法来获得。
        // 但是在 Support Library 23.1.0 版本之后，这个不再适用，在我使用的 Support Library 23.1.1 版本中，
        // 可以直接调用 getHeaderView()方法先得到 Header,然后在通过header来获得头部内的控件。
//        View header = mNavigation.getHeaderView(0);
//        TextView userName = (TextView) header.findViewById(R.id.tv_username);
//        TextView email = (TextView) header.findViewById(R.id.tv_email);
//        userName.setText("Chris Ching");
//        email.setText("chris_ching@163.com");

        //处理菜单列表种图标不显示原始颜色的问题.  当你设置完图标后,会发现无论图标的原始颜色是什么,都会全部变成灰色.
        // 此时,你可以通过app:itemIconTint="@color/..."属性为图标设置统一的颜色.当然,如果你需要图标显示自己原始的颜色,
        // 可以调用NavigationView的setItemIconTintList(ColorStateList tint)方法,参数传为null即可.
//        mNavigation.setItemIconTintList(null);
        //设置菜单列表的点击事件。
//        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.drawer_home:
//                        showToast(MainActivity.this, "home");
//                        mDrawerLayout.closeDrawers();    //点击条目后，关闭侧滑才菜单
//                        item.setChecked(true);          //设置点击过的item为选中状态，字体和图片的颜色会发生变化，颜色会变为和Toolbar的颜色一致。
//                        mToolbar.setTitle("Home");       //修改Toolbar显示的Title
//                        break;
//                    case R.id.drawer_favourite:
//                        showToast(MainActivity.this, "Favourite");
//                        mDrawerLayout.closeDrawers();
//                        item.setChecked(true);
//                        mToolbar.setTitle("Favourite");
//                        break;
//                    case R.id.drawer_download:
//                        showToast(MainActivity.this, "Download");
//                        mDrawerLayout.closeDrawers();
//                        item.setChecked(true);
//                        mToolbar.setTitle("Download");
//                        break;
//                    case R.id.drawer_more:
//                        showToast(MainActivity.this, "More");
//                        mDrawerLayout.closeDrawers();
//                        item.setChecked(true);
//                        mToolbar.setTitle("More");
//                        break;
//                    case R.id.drawer_settings:
//                        showToast(MainActivity.this, "Settings");
//                        mDrawerLayout.closeDrawers();
//                        item.setChecked(true);
//                        mToolbar.setTitle("Settings");
//                        break;
//                }
//                return false;
//            }
//        });
    }

    private void setBottomNavigationBar() {
        mBnbMain.setMode(BottomNavigationBar.MODE_FIXED);//设置底部栏显示模式，等分显示
        mBnbMain.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);//设置背景模式
        // 在BACKGROUND_STYLE_RIPPLE 模式下是底部导航栏背景色,在BACKGROUND_STYLE_STATIC 模式下颜色是图标和文字的颜色（选中/未选中）
        mBnbMain.setActiveColor(R.color.text_grayer)
                .setInActiveColor("#FFFFFF")//是图标和文字的颜色（选中/未选中）
                //在BACKGROUND_STYLE_STATIC 模式下颜色是底部导航栏背景色，BACKGROUND_STYLE_RIPPLE模式下是图标和文字的颜色（选中/未选中）
                .setBarBackgroundColor("#ECECEC");
        //添加标记
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.title_bg)
                .setText("5")
                .setHideOnSelect(true);
        //添加条目
        mBnbMain.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "Home").setActiveColorResource(R.color.orange).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "Books").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "Music").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, "Movies & TV").setActiveColorResource(R.color.brown))
                .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp, "Games").setActiveColorResource(R.color.grey))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = HomeFragment.newInstance("Home");
        transaction.add(R.id.fl_content, mHomeFragment);
        transaction.commit();

    }


    //每个tab选中后设置标题及展示fragment
    @Override
    public void onTabSelected(int position) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance("");
                    ft.add(R.id.fl_content, mHomeFragment);
                } else {
                    ft.show(mHomeFragment);
                }
                mToolbar.setTitle("111");
                break;
            case 1:
                if (mBookFragment == null) {
                    mBookFragment = BookFragment.newInstance("");
                    ft.add(R.id.fl_content, mBookFragment);
                } else {
                    ft.show(mBookFragment);
                }
                mToolbar.setTitle("222");
                break;
            case 2:
                if (mMusicFragment == null) {
                    mMusicFragment = MusicFragment.newInstance("");
                    ft.add(R.id.fl_content, mMusicFragment);
                } else {
                    ft.show(mMusicFragment);
                }
                mToolbar.setTitle("333");
                break;
            case 3:
                if (mTvFragment == null) {
                    mTvFragment = TvFragment.newInstance("");
                    ft.add(R.id.fl_content, mTvFragment);
                } else {
                    ft.show(mTvFragment);
                }
                mToolbar.setTitle("444");
                break;
            case 4:
                if (mGameFragment == null) {
                    mGameFragment = GameFragment.newInstance("");
                    ft.add(R.id.fl_content, mGameFragment);
                } else {
                    ft.show(mGameFragment);
                }
                mToolbar.setTitle("555");
                break;
        }
        ft.commitAllowingStateLoss();


    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mBookFragment != null) {
            transaction.hide(mBookFragment);
        }
        if (mMusicFragment != null) {
            transaction.hide(mMusicFragment);
        }
        if (mTvFragment != null) {
            transaction.hide(mTvFragment);
        }
        if (mGameFragment != null) {
            transaction.hide(mGameFragment);
        }
    }


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//添加menu布局

        //搜索的界面展示
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                showToast(MainActivity.this, "搜索");
                break;
            case R.id.action_add:
                showToast(MainActivity.this, "添加");
                break;
            case R.id.action_settings:
                showToast(MainActivity.this, "设置");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onBackPressed() {
        //重写返回键，当侧滑菜单处于打开状态，点击返回键就关闭侧滑菜单。
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawers();
        else
            super.onBackPressed();
    }
}
