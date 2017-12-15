package com.katyusha.aron.demo.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.andview.refreshview.XRefreshView;
import com.katyusha.aron.demo.R;
import com.katyusha.aron.demo.adapter.BhRecycleAdapter;
import com.katyusha.aron.demo.bean.HomeBean;
import com.katyusha.aron.demo.databinding.ActivityMainBinding;
import com.katyusha.aron.demo.event.BhClickEvent;
import com.katyusha.aron.demo.event.HomeEvent;
import com.katyusha.aron.demo.viewmodel.HomeVM;
import com.katyusha.aron.library.basic.BaseActivity;
import com.katyusha.aron.library.constant.PagePath;
import com.katyusha.aron.library.widget.SmileyHeaderView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

@Route(path = PagePath.Main)
public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private LinearLayoutManager layoutManager;
    private final int PinnedTime = 200;
    private List<HomeBean> floors = new ArrayList<>();
    private BhRecycleAdapter adapter;

    @Override
    protected void init() {
        viewModel = new HomeVM(this);
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.blHomeContent.setLayoutManager(layoutManager);

        binding.blHomeRefresh.setCustomHeaderView(new SmileyHeaderView(this));
        binding.blHomeRefresh.setMoveForHorizontal(true);
        binding.blHomeRefresh.setPinnedTime(PinnedTime);
        binding.blHomeRefresh.startRefresh();
        binding.blHomeRefresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                ((HomeVM) viewModel).requestHomeData();
            }
        });
        adapter = new BhRecycleAdapter(this, floors);
        binding.blHomeContent.setAdapter(adapter);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHomeEvent(HomeEvent homeEvent) {
        binding.blHomeRefresh.stopRefresh();
        if (homeEvent.isSuccess()) {
            floors.clear();
            floors.addAll(homeEvent.getResponse().getData());
            adapter.notifyDataSetChanged();
        } else {
            if (homeEvent.getErrorInfo() != null) {
                showShortText(homeEvent.getErrorInfo().getErrorMsg());
            } else {
                showShortText("网络连接失败");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHomeClickEvent(BhClickEvent clickEvent) {
        if (clickEvent != null) {
            ARouter.getInstance().build(PagePath.Goods)
                    .withString("url", clickEvent.getParams())
                    .navigation(this, new NavCallback() {
                        @Override
                        public void onArrival(Postcard postcard) {
                            Log.e("Arouter", "到达目的地");
                        }

                        @Override
                        public void onInterrupt(Postcard postcard) {
                            Log.e("Arouter", "被拦截了！");
                        }
                    });
            showShortText("jump to goods!");
        }
    }
}
