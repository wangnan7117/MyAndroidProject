package com.example.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xiangxiongfly.common.base.BaseFragment
import com.xiangxiongfly.common.base.KEY_TITLE

class SettingFragment : BaseFragment() {
    private lateinit var tvTitle: TextView

    private var mTitle: String? = null

    companion object {
        @JvmStatic
        fun newInstance(title: String) =
            SettingFragment().apply {
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
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle = view.findViewById(R.id.tv_title)
        tvTitle.text = mTitle
        tvTitle.setOnClickListener {
            tvTitle.text = mTitle + "改变了"
        }
    }
}