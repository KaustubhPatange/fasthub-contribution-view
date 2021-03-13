package com.kpstv.github_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kpstv.github.GraphModel
import com.kpstv.github_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by viewBinding(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val parseData: List<GraphModel> = Gson().fromJson(weekString2, object : TypeToken<List<GraphModel>>() {}.type)
        binding.graphView.graphData = parseData
    }
}

inline fun<VB: ViewBinding> ComponentActivity.viewBinding(
    crossinline inflater: (LayoutInflater) -> VB
) = lazy { inflater.invoke(layoutInflater) }