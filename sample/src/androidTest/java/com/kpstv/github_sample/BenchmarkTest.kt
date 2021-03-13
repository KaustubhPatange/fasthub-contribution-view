package com.kpstv.github_sample

import android.util.Log
import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kpstv.github.GraphModel
import com.kpstv.github.GraphView
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.system.measureNanoTime

@RunWith(AndroidJUnit4::class)
class BenchmarkTest {

    private val TAG = "BenchmarkTest"

    @Test
    fun sampleData() {
        runTest(weekString, 0)
    }

    @Test
    fun sampleData1() {
        runTest(weekString1, 1)
    }

    @Test
    fun sampleData2() {
        runTest(weekString2, 2)
    }

    @Test
    fun sampleData3() {
        runTest(weekString3, 3)
    }

    @Test
    fun sampleData4() {
        runTest(weekString4, 4)
    }

    @Test
    fun sampleData5() {
        runTest(weekString5, 5)
    }

    private fun runTest(weekString: String, number: Int) {
        Log.e(TAG, "Testing Sample Data $number")
        val models = parseModel(weekString)
        val nanoseconds = measureData(models)
        val milliseconds = nanoseconds.toDouble() / 1000000.0
        Log.e(TAG, "Total weeks: ${models.size}, inflation time: ($nanoseconds ns, $milliseconds) ms")
    }

    private fun parseModel(graphDataString: String): List<GraphModel> {
        return Gson().fromJson(graphDataString, object : TypeToken<List<GraphModel>>() {}.type)
    }

    private fun measureData(weeks: List<GraphModel>): Long {
        return computeMeasurement(weeks) / REPEATS
    }

    private fun computeMeasurement(weeks: List<GraphModel>) = measureNanoTime {
        for (i in 0 until REPEATS) {
            val context = getInstrumentation().targetContext
            GraphView(context).apply {
                measure(
                    View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                )
                layout(0,0, measuredWidth, measuredHeight)
                graphData = weeks
            }
        }
    }

    companion object {
        private const val REPEATS = 1000
    }
}