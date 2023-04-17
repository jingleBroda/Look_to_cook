package com.jinglebroda.presentation.singleActivity.mvvm.utils.baseUiClasses

import android.view.View
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerFragment

abstract class BaseFragment(layoutId:Int):DaggerFragment(layoutId), View.OnClickListener
