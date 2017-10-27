package com.katyusha.aron.demo.activity;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.katyusha.aron.demo.R;
import com.katyusha.aron.demo.basic.BaseActivity;
import com.katyusha.aron.demo.databinding.ActivityLoginBinding;
import com.katyusha.aron.demo.event.LoginEvent;
import com.katyusha.aron.demo.viewmodel.LoginVM;
import com.katyusha.aron.library.constant.PagePath;
import com.katyusha.aron.library.utils.MD5Utils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Created by aron on 2017/7/13.
 */

@Route(path = PagePath.Login)
public class LoginActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    private ActivityLoginBinding binding;
    public static final int RC_WRITE = 100;

    @Override
    protected void init() {
        requestNeedPermissions();
        viewModel = new LoginVM(this);
    }

    @AfterPermissionGranted(RC_WRITE)
    private void requestNeedPermissions() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {

        } else {
            EasyPermissions.requestPermissions(this, "BLDemo运行必要的权限", RC_WRITE, perms);
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginVM)viewModel).requestLogin("admin", MD5Utils.md5("123"), "admin", "1");
            }
        });
        binding.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginVM)viewModel).requestMessage();
            }
        });
        binding.toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(PagePath.Main).navigation();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event){
        if (event.isSuccess()) {

        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
