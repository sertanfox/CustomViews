package com.sertanfox.customviewexample

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView

class ButtonWithProgressBar : FrameLayout {

    private lateinit var rlRootLayout: FrameLayout
    private lateinit var tvText: TextView
    private lateinit var pbLoader: ProgressBar
    private var text = ""
    private var size = ButtonWithProgressBarSize.DEFAULT
    private var isButtonEnabled = true
    private var backgroundColor = 0
    private var textColor = 0
    private var showingLoader = false

    constructor(context: Context) : super(context) {
        initKButtonWithProgressBar(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getStuffFromXML(attrs, context)
        initKButtonWithProgressBar(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        getStuffFromXML(attrs, context)
        initKButtonWithProgressBar(context)
    }

    private fun initKButtonWithProgressBar(context: Context) {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        LayoutInflater.from(context).inflate(
            R.layout.button_with_progressbar,
            this,
            true
        )

        rlRootLayout = findViewById(R.id.rl_root_layout)
        tvText = findViewById(R.id.tv_text)
        pbLoader = findViewById(R.id.pb_loader)

        if (text.isNotEmpty()) {
            tvText.text = text
        }

        minimumWidth = resources.getDimension(R.dimen.button_mini_width).toInt()

        if(ButtonWithProgressBarSize.MINI == size){
            setMeasuredDimension(
                measuredWidth,
                resources.getDimension(R.dimen.button_mini_height).toInt()
            )

            rlRootLayout.layoutParams.height = resources.getDimension(R.dimen.button_mini_height).toInt()
        }
        else if(ButtonWithProgressBarSize.LARGE == size){
            setMeasuredDimension(
                measuredWidth,
                resources.getDimension(R.dimen.button_large_height).toInt()
            )

            rlRootLayout.layoutParams.height = resources.getDimension(R.dimen.button_large_height).toInt()
        }
        else if(ButtonWithProgressBarSize.LARGEST == size){
            setMeasuredDimension(
                measuredWidth,
                resources.getDimension(R.dimen.button_largest_height).toInt()
            )

            rlRootLayout.layoutParams.height = resources.getDimension(R.dimen.button_largest_height).toInt()
        }
        else{
            setMeasuredDimension(
                measuredWidth,
                resources.getDimension(R.dimen.button_largest_height).toInt()
            )

            rlRootLayout.layoutParams.height = resources.getDimension(R.dimen.button_largest_height).toInt()
        }

        rlRootLayout.setPadding(
            resources.getDimension(R.dimen.margin_10).toInt(),
            0,
            resources.getDimension(R.dimen.margin_10).toInt(),
            0
        )

        val colorDrawable = ColorDrawable(backgroundColor)
        background = colorDrawable

        tvText.setTextColor(textColor)

        refreshDrawableState()
    }

    fun setText(text:String){
        tvText.text = text
    }

    fun showLoader(){
        showingLoader = true
        tvText.visibility = View.GONE
        pbLoader.visibility = View.VISIBLE
        this.isEnabled = false
    }

    fun hideLoader(){
        showingLoader = false
        tvText.visibility = View.VISIBLE
        pbLoader.visibility = View.GONE
        this.isEnabled = true
    }

    enum class ButtonWithProgressBarSize {
        DEFAULT, MINI, LARGE, LARGEST
    }

    private fun getStuffFromXML(attrs: AttributeSet?, context: Context) {

        val data = context.obtainStyledAttributes(attrs, R.styleable.ButtonWithProgressBar)
        text = data.getString(R.styleable.ButtonWithProgressBar_text).toString()
        isButtonEnabled = data.getBoolean(R.styleable.ButtonWithProgressBar_enabled, true)
        backgroundColor = data.getColor(
            R.styleable.ButtonWithProgressBar_backgroundColor,
            context.resources.getColor(R.color.blue)
        )

        textColor = data.getColor(
            R.styleable.ButtonWithProgressBar_custom_text_color,
            context.resources.getColor(R.color.white)
        )

        when (data.getInt(
            R.styleable.ButtonWithProgressBar_size,
            0
        )) {
            0 -> size = ButtonWithProgressBarSize.DEFAULT
            1 -> size = ButtonWithProgressBarSize.MINI
            2 -> size = ButtonWithProgressBarSize.LARGE
            3 -> size = ButtonWithProgressBarSize.LARGEST
        }
        data.recycle()
    }
}