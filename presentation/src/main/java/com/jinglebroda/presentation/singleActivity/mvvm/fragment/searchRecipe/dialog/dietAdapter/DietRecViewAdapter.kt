package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dietAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jinglebroda.presentation.databinding.DietItemviewBinding
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dietAdapter.dietToggleButtonData.DietToggleButtonData
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dietAdapter.viewHolder.DietViewHolder

class DietRecViewAdapter(
    private val nameDietList:Array<String>,
    private val dietId:Array<String>,
    private val oldSelectedDiet:MutableList<String>? = null
):RecyclerView.Adapter<DietViewHolder>(), View.OnClickListener  {
    private val selectedDiet = oldSelectedDiet ?: mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder =
        DietViewHolder(
            DietItemviewBinding.inflate(LayoutInflater.from(parent.context)),
            this
        )

    override fun onBindViewHolder(holder: DietViewHolder, position: Int){
        if (oldSelectedDiet != null) {
            for(i in oldSelectedDiet){
                if(i == dietId[position]){
                    holder.bind(nameDietList[position], dietId[position], true)
                    break
                }
                else{
                    if(i == oldSelectedDiet.last()){
                        holder.bind(nameDietList[position], dietId[position])
                    }
                }
            }
        }
        else{
            holder.bind(nameDietList[position], dietId[position])
        }
    }


    override fun getItemCount(): Int = nameDietList.size

    fun getSelectedDiet():List<String> = selectedDiet

    override fun onClick(v: View) {
        val data = v.tag as DietToggleButtonData
        if(data.prSelected){
            selectedDiet.add(data.nameId)
        }
        else{
            selectedDiet.remove(data.nameId)
        }
        Log.d("testDialog", selectedDiet.toString())
    }
}