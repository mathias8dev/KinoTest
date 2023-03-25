package com.mathias8dev.kinotest.domain.viewModel

import androidx.lifecycle.ViewModel
import com.mathias8dev.kinotest.R
import com.mathias8dev.kinotest.domain.data.Strings

class StartupScreenViewModel: ViewModel() {
    private val _messages = arrayListOf(
        Strings.howDoesItWork,
        Strings.weShowOurVehicleList,
        Strings.youClickToSeeTheirId
    )

    private val _illustrations = arrayListOf<Int>(
        R.drawable.undraw_faq,
        R.drawable.undraw_survey_05s5,
        R.drawable.undraw_data
    )

    fun getMessageFromPageIndex(pageIndex: Int): String {
        return if (pageIndex > _messages.size) Strings.howDoesItWork
        else _messages[pageIndex]
    }

    fun getIllustrationFromPageIndex(pageIndex: Int): Int {
        return if (pageIndex > _illustrations.size) _illustrations[0]
        else _illustrations[pageIndex]
    }
}