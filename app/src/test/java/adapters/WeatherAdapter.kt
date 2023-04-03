package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import date.Weather
import android.content.Context


class WeatherAdapter(context:Context, weatherList:ArrayList<Weather>): BaseAdapter() {
    private var sList:ArrayList<Weather>
    private  val mInflator: LayoutInflater

    init{
        sList = weatherList
        mInflator = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return sList.size
    }

    override fun getItem(index: Int): Any {
        return  sList[index]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view:View
        val vh:ListRowHolder

    }
    private class ListRowHolder(row)


}