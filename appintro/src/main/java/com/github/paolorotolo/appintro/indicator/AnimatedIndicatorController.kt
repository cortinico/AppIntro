package com.github.paolorotolo.appintro.indicator

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.rd.PageIndicatorView
import com.rd.animation.type.AnimationType

/**
 * An [IndicatorController] that shows a [ProgressBar] for express the number of
 * page that have been completed.
 * Use this when the number of page is higher and the [DotIndicatorController]
 * would not fit in the screen.
 */
class AnimatedIndicatorController @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : IndicatorController {

    lateinit var pageIndicatorView : PageIndicatorView

    override var selectedIndicatorColor = DEFAULT_COLOR
        set(value) {
            field = value
            pageIndicatorView.selectedColor = value
        }

    override var unselectedIndicatorColor = DEFAULT_COLOR
        set(value) {
            field = value
            pageIndicatorView.unselectedColor = value
        }

    override fun newInstance(context: Context) : View {
        pageIndicatorView = PageIndicatorView(context)
        pageIndicatorView.setAnimationType(AnimationType.THIN_WORM)

        val linearLayout = LinearLayout(context)
        val newLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
        newLayoutParams.gravity = Gravity.CENTER_VERTICAL
        linearLayout.layoutParams = newLayoutParams
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.addView(pageIndicatorView)
        return linearLayout
    }

    override fun initialize(slideCount: Int) {
        pageIndicatorView.count = slideCount
        selectPosition(0)
    }

    override fun selectPosition(index: Int) {
        pageIndicatorView.selection = index
    }
}
