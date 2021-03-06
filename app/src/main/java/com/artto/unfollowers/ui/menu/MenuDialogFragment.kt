package com.artto.unfollowers.ui.menu

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.unfollowers.R
import com.artto.unfollowers.ui.base.BaseDialogFragment
import com.artto.unfollowers.ui.main.MainFragmentDirections
import com.artto.unfollowers.ui.statistic.StatisticDialogFragment
import com.artto.unfollowers.utils.createFragment
import kotlinx.android.synthetic.main.dialog_menu.*
import org.koin.android.ext.android.inject

class MenuDialogFragment : BaseDialogFragment(), MenuView {

    @InjectPresenter
    lateinit var presenter: MenuPresenter

    @ProvidePresenter
    fun providePresenter() = inject<MenuPresenter>().value

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_log_out.setOnClickListener { presenter.onLogOutClicked() }
        tv_statistic.setOnClickListener { presenter.onStatisticClicked() }
    }

    override fun onResume() {
        super.onResume()

        dialog?.window?.apply {
            setLayout(
                    (resources.displayMetrics.widthPixels * 0.9).toInt(),
                    attributes.height)
        }
    }

    override fun navigateToStatistic() {
        parentFragment?.childFragmentManager?.let { createFragment<StatisticDialogFragment>().show(it, null) }
        dismiss()
    }

    override fun navigateToLogin() = findNavController().navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())

    override fun getLayout() = R.layout.dialog_menu

}