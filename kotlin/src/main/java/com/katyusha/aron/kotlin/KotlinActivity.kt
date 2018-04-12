package com.katyusha.aron.kotlin

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.katyusha.aron.kotlin.databinding.ActivityKotlinBinding
import com.katyusha.aron.kotlin.practice.TableActivity
import com.katyusha.aron.library.constant.PagePath

@Route(path = PagePath.Kotlin)
class KotlinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKotlinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kotlin)
        init()
    }

    private fun init() {
        binding.textView9.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                ARouter.getInstance().build(PagePath.ProductList).navigation()
            }

        })
        //lamda表达式写法
        binding.textView10.setOnClickListener {
            ARouter.getInstance().build(PagePath.BIZ_LIST).navigation()
        }

        binding.textView11.setOnClickListener {
            var intent = Intent(this, TableActivity::class.java)
            startActivity(intent)
        }
    }

}
