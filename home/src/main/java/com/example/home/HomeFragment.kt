package com.example.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.home.bottom_sheet.BottomSheetActivity
import com.example.home.drawable.DrawableActivity
import com.example.home.expandable_listview.ExpandableListViewActivity
import com.example.home.immersion.ImmersionActivity
import com.example.home.listview.ListViewActivity
import com.example.home.recyclerview.RecyclerViewActivity
import com.example.home.span.SpannableStringActivity
import com.example.home.tablayout.TabLayoutActivity
import com.example.home.viewpager.ViewPagerActivity
import com.example.home.viewpager2.ViewPager2Activity
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.BaseFragment
import com.xiangxiongfly.common.base.KEY_TITLE
import kotlin.reflect.KClass

class HomeFragment : BaseFragment() {
    private lateinit var flexboxLayout: FlexboxLayout

    private var mTitle: String? = null

    companion object {
        @JvmStatic
        fun newInstance(title: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mTitle = it.getString(KEY_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        addElements()
    }

    private fun initView(view: View) {
        flexboxLayout = view.findViewById(R.id.flexboxLayout)
    }

    private fun addElements() {
        flexboxLayout.removeAllViews()
        addElement("ViewPager", ViewPagerActivity::class)
        addElement("ViewPager2", ViewPager2Activity::class)
        addElement("BottomSheet", BottomSheetActivity::class)
        addElement("沉浸式状态栏", ImmersionActivity::class)
        addElement("ListView", ListViewActivity::class)
        addElement("ExpandableListView", ExpandableListViewActivity::class)
        addElement("RecyclerView", RecyclerViewActivity::class)
        addElement("SpannableString", SpannableStringActivity::class)
        addElement("Drawable", DrawableActivity::class)
        addElement("TabLayout", TabLayoutActivity::class)
    }

    private fun addElement(title: String, activityClass: KClass<out BaseActivity>) {
        flexboxLayout.addView(
            TextView(context).apply {
                background = ContextCompat.getDrawable(mContext, R.drawable.home_shape)
                setTextColor(Color.BLACK)
                text = title
                isAllCaps = false
                textSize = 18F
                setOnClickListener {
                    startActivity(Intent(context, activityClass.java).apply {
                        putExtra(KEY_TITLE, title)
                    })
                }
            }
        )
    }
}