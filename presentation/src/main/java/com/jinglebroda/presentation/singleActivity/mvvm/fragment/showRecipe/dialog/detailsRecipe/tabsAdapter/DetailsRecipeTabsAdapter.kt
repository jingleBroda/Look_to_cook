package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe.tabsAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DetailsRecipeTabsAdapter(
    fm: FragmentManager,
    private val fragmentList: List<Fragment>,
    private val titleList: List<String>) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    // Возвращает фрагмент для отображения на конкретной позиции в ViewPager
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    // Возвращает количество элементов в списке фрагментов
    override fun getCount(): Int {
        return fragmentList.size
    }

    // Возвращает заголовок для отображения на конкретной позиции в TabLayout
    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}