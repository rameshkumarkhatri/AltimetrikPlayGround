package com.mobifyall.altimetrikplayground.fragment

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.mobifyall.altimetricplayground.model.Model
import com.mobifyall.altimetrikplayground.R
import com.mobifyall.altimetrikplayground.SharedData
import com.mobifyall.altimetrikplayground.databinding.VehicalDetailFragmentBinding
import com.mobifyall.altimetrikplayground.view.DetailFragmentView
import com.mobifyall.altimetrikplayground.viewmodel.VehicalDetailViewModel
import com.squareup.picasso.Picasso

class VehicalDetailFragment : Fragment(), DetailFragmentView {
    override fun showStatusData(status: String) {
        binding.tvStatus.setText(Html.fromHtml(status))
    }

    override fun saveStatus(result: JsonArray?) {
        context?.let {
            SharedData.saveData(it, result.toString(), SharedData.status)
        }
    }

    override fun saveFuel(result: Model.FuelCharge?) {
        val gson = Gson()
        val model = gson.toJson(result)
        context?.let {
            SharedData.saveData(it, model, SharedData.fuel)
        }
    }

    override fun showFuel(model: Model.FuelCharge?) {
        if (model?.unit != null)
            binding.tvFuel.setText(
                "Unit : " + (model?.unit) + "\n"
                        + "Value : " + (model?.value) + "\n"
                        + "Retrival Status : " + (model?.retrievalstatus) + "\n"
                        + "Time : " + (model?.timestamp) + "\n"
            )
    }

    override fun showFuel(model: Model.Charge?) {
        if (model?.unit != null)
            binding.tvFuel.setText(
                "Unit : " + (model?.unit) + "\n"
                        + "Value : " + (model?.value) + "\n"
                        + "Retrival Status : " + (model?.retrievalstatus) + "\n"
                        + "Time : " + (model?.timestamp) + "\n"
            )
    }

    override fun saveImages(image: Model.VehicalImage) {
        val gson = Gson()
        val model = gson.toJson(image)
        context?.let {
            SharedData.saveData(it, model, SharedData.image)
        }
    }

    override fun showErrorImage(error: Throwable) {
        Log.e("Images error", error.message);
    }

    override fun showImages(image: Model.VehicalImage) {

        Log.e("Images error", image.toString());
        Picasso.get().load(image.vehicle.INT1.url).into(binding.iv1)
        Picasso.get().load(image.vehicle.EXT020.url).into(binding.iv2)
    }

    companion object {
        fun newInstance(result: Model.Vehical?): VehicalDetailFragment {
            val f = VehicalDetailFragment()
            val b = Bundle()
            b.putSerializable("model", result)
            f.arguments = b
            return f
        }
    }


    private lateinit var viewModel: VehicalDetailViewModel
    private lateinit var model: Model.Vehical

    private lateinit var binding: VehicalDetailFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.vehical_detail_fragment, container, false)
        model = arguments?.getSerializable("model") as Model.Vehical
        binding.tvDetails.setText(
            "Color name : " + model.colorname + "\n" +
                    "Finorvin  : " + model.finorvin + "\n" +
                    "Fuel Type : " + model.fueltype + "\n" +
                    "Model Year : " + model.modelyear + "\n" +
                    "License Plate : " + model.licenseplate + "\n" +
                    "Number of Doors : " + model.numberofdoors + "\n" +
                    "Number of Seats : " + model.numberofseats + "\n" +
                    "Power HP: " + model.powerhp + "\n" +
                    "Power KW: " + model.powerkw + "\n" +
                    "Sales Designation : " + model.salesdesignation
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VehicalDetailViewModel::class.java)
        viewModel.view = this
        context?.let { viewModel.getImagesFromServer(it, model.id, model.finorvin) }
    }


}
