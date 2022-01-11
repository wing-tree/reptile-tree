package com.wing.tree.reptile.tree.presentation.view.alarm

import android.content.Context
import android.icu.util.Calendar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.wing.tree.reptile.tree.presentation.R
import com.wing.tree.reptile.tree.presentation.constant.BLANK
import com.wing.tree.reptile.tree.presentation.databinding.DayViewBinding
import com.wing.tree.reptile.tree.presentation.databinding.WeekViewBinding
import com.wing.tree.reptile.tree.presentation.view.base.LinearLayoutManagerWrapper

class WeekView : RecyclerView {
    private lateinit var viewBinding: WeekViewBinding

    private val currentArray by lazy {
        arrayOf(
            Item(Calendar.SUNDAY, false),
            Item(Calendar.MONDAY, false),
            Item(Calendar.TUESDAY, false),
            Item(Calendar.WEDNESDAY, false),
            Item(Calendar.THURSDAY, false),
            Item(Calendar.FRIDAY, false),
            Item(Calendar.SATURDAY, false)
        )
    }

    private val shortWeekdays = hashMapOf(
        Calendar.SUNDAY to getString(R.string.sun),
        Calendar.MONDAY to getString(R.string.mon),
        Calendar.TUESDAY to getString(R.string.tue),
        Calendar.WEDNESDAY to getString(R.string.wed),
        Calendar.THURSDAY to getString(R.string.thu),
        Calendar.FRIDAY to getString(R.string.fri),
        Calendar.SATURDAY to getString(R.string.sat)
    )

    constructor(context: Context): super(context) {
        inflate()
        bind()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        inflate()
        bind()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        inflate()
        bind()
    }

    private fun inflate() {
        viewBinding = WeekViewBinding.inflate(LayoutInflater.from(context), this)
    }

    private fun bind() {
        with(viewBinding) {
            root.apply {
                adapter = Adapter()
                layoutManager = LinearLayoutManagerWrapper(context, HORIZONTAL)
            }
        }
    }

    private fun getString(@StringRes resId: Int) = context.getString(resId)

    fun setSelectedDayOfWeekList(vararg list: Int) {
        list.forEach {
            currentArray[it.dec()].isSelected = true
        }
    }

    inner class Item(
        val dayOfWeek: Int,
        var isSelected: Boolean
    ) {
        val shortWeekday: String
            get() = shortWeekdays[dayOfWeek] ?: BLANK
    }

    inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
        inner class ViewHolder(private val viewBinding: DayViewBinding): RecyclerView.ViewHolder(viewBinding.root) {
            fun bind(item: Item) {
                @ColorInt
                val textColor = when(item.dayOfWeek) {
                    Calendar.SUNDAY -> getColor(R.color.red_500)
                    Calendar.SATURDAY -> getColor(R.color.blue_500)
                    else -> getColor(R.color.high_emphasis)
                }

                with(viewBinding) {
                    textView.setTextColor(textColor)
                    textView.text = item.shortWeekday
                }
            }

            private fun getColor(@ColorRes id: Int) = context.getColor(id)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val viewBinding = DayViewBinding.inflate(LayoutInflater.from(context), parent, false)

            return ViewHolder(viewBinding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(currentArray[position])
        }

        override fun getItemCount(): Int = currentArray.count()
    }
}