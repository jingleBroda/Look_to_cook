package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.AdvancedSearchSettingsDialogLayoutBinding
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.closeDialogListener.AdvancedSearchSettingsDialogCloseListener
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dataAdvancedSearch.DataAdvancedSearch
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dietAdapter.DietRecViewAdapter
import com.jinglebroda.presentation.singleActivity.mvvm.utils.gridSpacingItemDecoration.GridSpacingItemDecoration

class AdvancedSearchSettingsDialog(
    private val closeDialogListener: AdvancedSearchSettingsDialogCloseListener,
    private val previousAdvancedSearch: DataAdvancedSearch? = null
): DialogFragment(R.layout.advanced_search_settings_dialog_layout) {
    private lateinit var binding:AdvancedSearchSettingsDialogLayoutBinding
    private lateinit var adapter:DietRecViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdvancedSearchSettingsDialogLayoutBinding.inflate(inflater)
        with(binding){
            //проверяем возможно сформированный старый запрос пользователя и восстанавливаем его в UI
            if(previousAdvancedSearch != null){
                if(previousAdvancedSearch.caloriesFirstLimit != null){
                    caloriesFirstLimitEditText.setText(
                        previousAdvancedSearch.caloriesFirstLimit.toString()
                    )
                }
                if(previousAdvancedSearch.caloriesLastLimit != null){
                    caloriesLastLimitEditText.setText(
                        previousAdvancedSearch.caloriesLastLimit.toString()
                    )
                }

                adapter = if(previousAdvancedSearch.diet != null){
                    DietRecViewAdapter(
                        requireContext().resources.getStringArray(R.array.diet_name_array),
                        requireContext().resources.getStringArray(R.array.diet_name_only_eng_array),
                        previousAdvancedSearch.diet as MutableList<String>
                    )
                } else{
                    DietRecViewAdapter(
                        requireContext().resources.getStringArray(R.array.diet_name_array),
                        requireContext().resources.getStringArray(R.array.diet_name_only_eng_array)
                    )
                }
            }
            else{
                adapter = DietRecViewAdapter(
                    requireContext().resources.getStringArray(R.array.diet_name_array),
                    requireContext().resources.getStringArray(R.array.diet_name_only_eng_array)
                )
            }


            saveButtonAdvancedSearch.setOnClickListener{
                dismiss()
            }
            clearRangeKcal.setOnClickListener{
                caloriesFirstLimitEditText.setText("")
                caloriesLastLimitEditText.setText("")
            }

            //поведение вводимого текста в EditText
            caloriesFirstLimitEditText.addTextChangedListener{editable->
                var changeString = editable.toString()
                if(changeString.isNotEmpty()){
                    if(changeString[0] == '0'){
                        // если первый символ не по формату
                        changeString = ""
                        caloriesFirstLimitEditText.setText(changeString)
                    }
                    else{
                        //если все гуд
                    }
                }
            }
            caloriesFirstLimitEditText.imeOptions = EditorInfo.IME_ACTION_DONE

            caloriesLastLimitEditText.addTextChangedListener{editable->
                var changeString = editable.toString()
                if(changeString.isNotEmpty()){
                    if(changeString[0] == '0'){
                        // если первый символ не по формату
                        changeString = ""
                        caloriesLastLimitEditText.setText(changeString)
                    }
                    else{
                        //если все гуд
                    }
                }
            }
            caloriesLastLimitEditText.imeOptions = EditorInfo.IME_ACTION_DONE

            //настройка RecyclerView
            dietListRecView.layoutManager = GridLayoutManager(context, 2)
            dietListRecView.addItemDecoration(
                GridSpacingItemDecoration(2,0,false)
            )
            dietListRecView.adapter = adapter
        }
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        //если хоть 1 параметр из range калорий пуст, то не записываем их
        if(
            (binding.caloriesFirstLimitEditText.text.toString() == "")||
            (binding.caloriesLastLimitEditText.text.toString() == "")
        ){
            closeDialogListener.closeDialog(
                DataAdvancedSearch(
                    null,
                    null,
                    (binding.dietListRecView.adapter as DietRecViewAdapter).getSelectedDiet().ifEmpty {
                        null
                    }
                )
            )
        }
        else{
            closeDialogListener.closeDialog(
                DataAdvancedSearch(
                    try {
                        binding.caloriesFirstLimitEditText.text.toString().toInt()
                    }
                    catch(e:NumberFormatException){
                        null
                    },
                    try {
                        binding.caloriesLastLimitEditText.text.toString().toInt()
                    }
                    catch(e:NumberFormatException){
                        null
                    },
                    (binding.dietListRecView.adapter as DietRecViewAdapter).getSelectedDiet().ifEmpty {
                        null
                    }
                )
            )
        }
    }
}