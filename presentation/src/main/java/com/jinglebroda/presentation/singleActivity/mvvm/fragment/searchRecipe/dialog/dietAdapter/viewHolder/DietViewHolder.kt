package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dietAdapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.DietItemviewBinding
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dietAdapter.dietToggleButtonData.DietToggleButtonData

class DietViewHolder(
    private val binding:DietItemviewBinding,
    private val insideListener: View.OnClickListener
):RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    private lateinit var localDietId:String
    fun bind(nameDiet:String, dietId:String, selectStatus:Boolean = false){
        with(binding){
            if(selectStatus){
                dietToggleButton.isChecked = true
            }
            dietToggleButton.text = nameDiet
            dietToggleButton.textOn = nameDiet
            dietToggleButton.textOff = nameDiet
            dietToggleButton.setOnClickListener(this@DietViewHolder)
            localDietId = dietId
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.dietToggleButton->{
                if(binding.dietToggleButton.isChecked){
                    v.tag = DietToggleButtonData(
                        localDietId,
                        true
                    )
                }
                else{
                    v.tag = DietToggleButtonData(
                        localDietId,
                        false
                    )
                }
                insideListener.onClick(v)
            }
        }
    }
}