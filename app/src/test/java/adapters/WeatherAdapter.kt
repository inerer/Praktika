package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import date.Weather
import android.content.Context
import android.widget.TextView
import com.example.praktika.R


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
        if(p1==null){
            view = this.mInflator.inflate(R.layout.item_for_weather, p2, false)
            vh = ListRowHolder(view)
            view.tag = vh
        }
        else{
            view = p1
            vh = view.tag as ListRowHolder
        }
        vh.date.text = sList[p0].date.toString()
        vh.tempC.text = sList[p0].temperatureC.toString()
        vh.tempF.text = sList[p0].temperatureF.toString()
        vh.summary.text = sList[p0].summary
        return view
    }

    private class ListRowHolder(row:View?){
        val date:TextView
        val tempC:TextView
        val tempF:TextView
        val summary:TextView

        init {
            this.date = row?.findViewById(R.id.dateTextView) as TextView
            this.tempC = row?.findViewById(R.id.tempCTextView) as TextView
            this.tempF = row?.findViewById(R.id.tempFTextView) as TextView
            this.summary = row?.findViewById(R.id.summaryTextView) as TextView
        }
    }

    public fun refreshAllData(xList:ArrayList<Weather>){
        sList = xList
        notifyDataSetChanged()
    }
}