package com.katyusha.aron.kotlin.practice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.katyusha.aron.kotlin.R
import com.rmondjone.locktableview.LockTableView
import java.util.ArrayList

/**
 * Created by jixiaolong on 2018/3/23.
 */
class TableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        var llTestTable = findViewById(R.id.test) as LinearLayout

        var mTableDatas = ArrayList<ArrayList<String>>()
        var mfristData = ArrayList<String>()
        mfristData.add("标题")
        (0..9).mapTo(mfristData) { "标题" + it }
        mTableDatas.add(mfristData)
        for (i in 0..19) {
            var mRowDatas = ArrayList<String>()
            mRowDatas.add("标题" + i)
            (0..9).mapTo(mRowDatas) { "数据" + it }
            mTableDatas.add(mRowDatas)
        }

        val mLockTableView = LockTableView(this, llTestTable, mTableDatas)
        mLockTableView.setLockFristColumn(true)
                .setLockFristRow(true)
                .setMaxColumnWidth(80) //列最大宽度
                .setMinColumnWidth(60) //列最小宽度
                .setColumnWidth(1,30)
                .setMinRowHeight(20)//行最小高度
                .setMaxRowHeight(30)//行最大高度
                .setTextViewSize(14) //单元格字体大小
                .setFristRowBackGroudColor(R.color.bg_grey)//表头背景色
                .setTableHeadTextColor(R.color.black)//表头字体颜色
                .setTableContentTextColor(R.color.black)//单元格字体颜色
                .setOnItemSeletor(R.color.white)//设置Item被选中颜色
                .show()
    }
}