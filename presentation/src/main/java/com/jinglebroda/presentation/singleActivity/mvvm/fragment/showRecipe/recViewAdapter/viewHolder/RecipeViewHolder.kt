package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.recViewAdapter.viewHolder

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jinglebroda.domain.model.searchRecipeModel.Recipe
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.RecipeItemViewV2Binding
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.recViewAdapter.viewHolder.ingredientsAdapter.IngredientsListViewAdapter


class RecipeViewHolder(
    private val binding:RecipeItemViewV2Binding,
    private val insideOnCLickListener:View.OnClickListener
    ):RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private val frameImgCreator = RecipeImgFrameCreator.Base(binding.root.context)

    @SuppressLint("SetTextI18n")
    fun bind(recipe: Recipe){
        with(binding){
            //1. имя рецепта
            nameRecipe.text = recipe.label
            //2. число продуктов для приготовления рецепта
            countIngredient.text = recipe.ingredients.size.toString()
            //3. число калорий
            countKcal.text = (recipe.calories/recipe.yield).toInt().toString()
            //4. кнопка открытия рецепта
            showRecipeButton.setOnClickListener{
                it.tag = recipe.url
                Log.d("urlRecipe", it.tag.toString())
                onClick(it)
            }
            //5. картинка рецепта
            recipePreviewImg.layoutParams = frameImgCreator.createFrame(recipePreviewImg)
            Glide.with(binding.root.context)
                .load(recipe.image)
                .centerCrop()
                .placeholder(com.bumptech.glide.R.drawable.abc_ab_share_pack_mtrl_alpha) //временно
                .into(recipePreviewImg)
            //6. протеин жиры углеводы
            proteinCount.text = recipe.totalNutrients["PROCNT"]?.quantity?.toInt().toString()+
                    recipe.totalNutrients["PROCNT"]?.unit
            fatCount.text = recipe.totalNutrients["FAT"]?.quantity?.toInt().toString()+
                    recipe.totalNutrients["FAT"]?.unit
            carbCount.text = recipe.totalNutrients["CHOCDF"]?.quantity?.toInt().toString()+
                    recipe.totalNutrients["CHOCDF"]?.unit
            //7. ингредиенты
            val ingredientsName = mutableListOf<String>()
            recipe.ingredients.forEach { elem->
                ingredientsName.add(elem.food?:"")
            }

            ingredientsRecView.adapter = IngredientsListViewAdapter(
                binding.root.context,
                ingredientsName
            )
            //8. кнопка "подробнее"
            deatilsConcreteRecipe.setOnClickListener{
                it.tag = recipe
                onClick(it)
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.showRecipeButton->{
                //TODO и тут тоже ошибка error: android.os.TransactionTooLargeException
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(v.context, Uri.parse(v.tag as String))
            }

            R.id.deatilsConcreteRecipe-> insideOnCLickListener.onClick(v)
        }
    }
}


/*
val builder = CustomTabsIntent.Builder()
val colorSchemeParams = CustomTabColorSchemeParams.Builder()
    .setToolbarColor(ContextCompat.getColor(v.context, R.color.white))
    .build()
builder.setDefaultColorSchemeParams(colorSchemeParams)
val customTabsIntent = builder.build()
customTabsIntent.launchUrl(v.context, Uri.parse(v.tag.toString()))
*/
