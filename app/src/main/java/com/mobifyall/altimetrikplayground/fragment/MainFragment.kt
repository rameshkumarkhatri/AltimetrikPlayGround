package com.mobifyall.music

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.mobifyall.altimetricplayground.model.Model
import com.mobifyall.altimetricplayground.view.MainActivityVIew
import com.mobifyall.altimetrikplayground.R
import com.mobifyall.altimetrikplayground.SharedData
import com.mobifyall.altimetrikplayground.databinding.MainFragmentBinding
import com.mobifyall.altimetrikplayground.fragment.VehicalDetailFragment
import com.mobifyall.altimetrikplayground.view.MainFragmentView
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), View.OnClickListener, MainFragmentView {
    override fun showResult(result: Model.Vehical?) {
        Log.e("API data", result.toString())
//        val intent = Intent()
//        intent.action = "changeFragmentBackStack"
//        intent.putExtra("fragment", "carDetail")
//        var b = Bundle()
//        b.putSerializable("data", result)
//        intent.putExtras(b)
//        activity?.sendBroadcast(intent)
        val gson = Gson()
        val model = gson.toJson(result)
        context?.let {
            SharedData.saveData(it, model, SharedData.desc)
            SharedData.saveData(it, binding.etSearch.text.toString(), SharedData.id)
        }
        val fragment = VehicalDetailFragment.newInstance(result)
        activityInterface.changeFragment(fragment)
    }

    override fun showApiError(error: Throwable?) {
        Log.e("API data", error.toString())
    }

    override fun showEmptyError() {
        binding.etSearch.setError("Please provide ID")

    }

    override fun onClick(v: View?) {
        when (v) {
            binding.tvSearch -> {
                viewModel.search(et_search.text.toString());
            }
        }
    }

    companion object {
        fun newInstance(activity: MainActivityVIew): MainFragment {
            val f = MainFragment()
            f.activityInterface = activity
            return f
        }

    }


    private lateinit var activityInterface: MainActivityVIew
    lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.etSearch.setText("1234567890ABCD1234")
        addClickListeners()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.view = this
    }


    override fun addClickListeners() {
        binding.tvSearch.setOnClickListener(this)

    }

}
