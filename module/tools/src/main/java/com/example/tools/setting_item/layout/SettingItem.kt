package com.example.tools.setting_item.layout

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import com.example.tools.R
import com.xiangxiongfly.common.utils.dp2px
import com.xiangxiongfly.common.utils.sp2px

class SettingItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        val DEFAULT_TEXT_SIZE = sp2px(14)
        val DEFAULT_TEXT_COLOR = Color.parseColor("#99000000")
        const val DEFAULT_DRAWABLE_SIZE = 0
        val DEFAULT_DRAWABLE_PADDING = dp2px(0)
        const val DEFAULT_LINE_WIDTH = 1
        val DEFAULT_LINE_COLOR = Color.parseColor("#0D000000")
    }

    private val mContainerLayout by lazy {
        LinearLayout(context).apply {
            layoutParams =
                FrameLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_VERTICAL
                )
        }
    }

    private val mLeftTextView by lazy {
        TextView(context).apply {
            val leftLayoutParams =
                LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1F)
            leftLayoutParams.gravity = Gravity.CENTER_VERTICAL
            layoutParams = leftLayoutParams
            isSingleLine = true
            ellipsize = TextUtils.TruncateAt.END
            setPaddingRelative(dp2px(15), dp2px(12), dp2px(15), dp2px(12))
            gravity = Gravity.START or Gravity.CENTER_VERTICAL
        }
    }

    private val mRightTextView by lazy {
        TextView(context).apply {
            val rightLayoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            rightLayoutParams.gravity = Gravity.CENTER_VERTICAL
            layoutParams = rightLayoutParams
            isSingleLine = true
            ellipsize = TextUtils.TruncateAt.END
            setPaddingRelative(dp2px(15), dp2px(12), dp2px(15), dp2px(12))
            gravity = Gravity.END or Gravity.CENTER_VERTICAL
        }
    }

    private val mLineView by lazy {
        View(context).apply {
            layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM)
        }
    }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.SettingItem)

        // 设置左边
        mLeftTextView.apply {
            // 设置左边文本
            if (a.hasValue(R.styleable.SettingItem_item_leftText)) {
                text = a.getString(R.styleable.SettingItem_item_leftText)
            }
            // 设置左边文字大小
            val leftTextSize =
                a.getDimensionPixelSize(
                    R.styleable.SettingItem_item_leftTextSize,
                    DEFAULT_TEXT_SIZE
                ).toFloat()
            setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize)
            // 设置左边文字颜色
            val leftTextColor =
                a.getColor(R.styleable.SettingItem_item_leftTextColor, DEFAULT_TEXT_COLOR)
            setTextColor(leftTextColor)
            // 设置左边图片
            val leftDrawable = a.getDrawable(R.styleable.SettingItem_item_leftDrawable)
//            setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null)
            // 设置左边图片大小
            val leftDrawableSize = a.getDimensionPixelSize(
                R.styleable.SettingItem_item_leftDrawableSize,
                DEFAULT_DRAWABLE_SIZE
            )
            if (leftDrawable != null) {
                if (leftDrawableSize > 0) {
                    leftDrawable.setBounds(0, 0, leftDrawableSize, leftDrawableSize)
                } else {
                    leftDrawable.setBounds(
                        0,
                        0,
                        leftDrawable.intrinsicWidth,
                        leftDrawable.intrinsicHeight
                    )
                }
                mLeftTextView.setCompoundDrawables(leftDrawable, null, null, null)
            }
            // 设置左边文字和图片之间的间距
            val leftDrawablePadding = a.getDimensionPixelSize(
                R.styleable.SettingItem_item_leftDrawablePadding,
                DEFAULT_DRAWABLE_PADDING
            )
            compoundDrawablePadding = leftDrawablePadding
        }

        // 设置右边
        mRightTextView.apply {
            // 设置右边文本
            if (a.hasValue(R.styleable.SettingItem_item_rightText)) {
                text = a.getString(R.styleable.SettingItem_item_rightText)
            }
            // 设置右边文字大小
            val rightTextSize =
                a.getDimensionPixelSize(
                    R.styleable.SettingItem_item_rightTextSize,
                    DEFAULT_TEXT_SIZE
                ).toFloat()
            setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize)
            // 设置右边文字颜色
            val rightTextColor =
                a.getColor(R.styleable.SettingItem_item_rightTextColor, DEFAULT_TEXT_COLOR)
            setTextColor(rightTextColor)
            // 设置右边图片
            val rightDrawable = a.getDrawable(R.styleable.SettingItem_item_rightDrawable)
            setCompoundDrawablesWithIntrinsicBounds(rightDrawable, null, null, null)
            // 设置右边图片大小
            val rightDrawableSize = a.getDimensionPixelSize(
                R.styleable.SettingItem_item_rightDrawableSize,
                DEFAULT_DRAWABLE_SIZE
            )
            if (rightDrawable != null) {
                if (rightDrawableSize > 0) {
                    rightDrawable.setBounds(0, 0, rightDrawableSize, rightDrawableSize)
                } else {
                    rightDrawable.setBounds(
                        0,
                        0,
                        rightDrawable.intrinsicWidth,
                        rightDrawable.intrinsicHeight
                    )
                }
                mRightTextView.setCompoundDrawables(null, null, rightDrawable, null)
            }
            // 设置右边文字和图片之间的间距
            val rightDrawablePadding = a.getDimensionPixelSize(
                R.styleable.SettingItem_item_rightDrawablePadding,
                DEFAULT_DRAWABLE_PADDING
            )
            compoundDrawablePadding = rightDrawablePadding
        }

        // 设置下划线
        mLineView.apply {
            // 设置是否显示下划线
            val visible = a.getBoolean(R.styleable.SettingItem_item_lineVisible, false)
            visibility = if (visible) View.VISIBLE else View.GONE
            val params = layoutParams as FrameLayout.LayoutParams
            // 设置下划线宽度
            val lineWidth =
                a.getDimensionPixelSize(R.styleable.SettingItem_item_lineWidth, DEFAULT_LINE_WIDTH)
            params.height = lineWidth
            // 设置下换线左右边距
            val lineMargin = a.getDimensionPixelSize(R.styleable.SettingItem_item_lineMargin, 0)
            params.leftMargin = lineMargin
            params.rightMargin = lineMargin
            layoutParams = params
            // 设置下划线颜色
            mLineView.background =
                if (a.hasValue(R.styleable.SettingItem_item_lineDrawable)) {
                    a.getDrawable(R.styleable.SettingItem_item_lineDrawable)
                } else {
                    ColorDrawable(Color.parseColor("#FFECECEC"))
                }
        }

        if (background == null) {
            val drawable = StateListDrawable()
            drawable.addState(
                intArrayOf(android.R.attr.state_pressed),
                ColorDrawable(DEFAULT_LINE_COLOR)
            )
            drawable.addState(
                intArrayOf(android.R.attr.state_selected),
                ColorDrawable(DEFAULT_LINE_COLOR)
            )
            drawable.addState(
                intArrayOf(android.R.attr.state_focused),
                ColorDrawable(DEFAULT_LINE_COLOR)
            )
            drawable.addState(
                intArrayOf(),
                ColorDrawable(ContextCompat.getColor(getContext(), R.color.white))
            )
            background = drawable

            // 必须要设置可点击，否则点击屏幕任何角落都会触发按压事件
            isFocusable = true
            isClickable = true
        }

        a.recycle()

        mContainerLayout.addView(mLeftTextView)
        mContainerLayout.addView(mRightTextView)

        addView(mContainerLayout, 0)
        addView(mLineView, 1)
    }
}