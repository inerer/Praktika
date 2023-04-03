package date

import com.google.gson.annotations.SerializedName
import java.util.Calendar

class Weather {
    @SerializedName("date")
    var date = Calendar.getInstance().time
    @SerializedName("temperatureC")
    var temperatureC = 0
    @SerializedName("temperatureF")
    var temperatureF = 0
    @SerializedName("summary")
    var summary = ""
}