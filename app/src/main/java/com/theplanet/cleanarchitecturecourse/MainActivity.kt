package com.theplanet.cleanarchitecturecourse

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import cleanarchitecture.presentation.factory.ViewModelFactory
import cleanarchitecture.presentation.model.Resource
import cleanarchitecture.presentation.model.Status
import cleanarchitecture.presentation.model.UserInfoPresentationModel
import cleanarchitecture.presentation.viewmodels.UserInfoVM
import com.theplanet.cleanarchitecturecourse.utils.MyAppLog
import com.theplanet.cleanarchitecturecourse.utils.toCurrency
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var userInfoVM: UserInfoVM
    private var userInfoModel: UserInfoPresentationModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userInfoVM = ViewModelProviders.of(this, viewModelFactory).get(UserInfoVM::class.java)
        userInfoVM.userInfoResource.observe(this, androidx.lifecycle.Observer { handleResource(it) })

    }

    private fun handleResource(resource: Resource<UserInfoPresentationModel>) {
        MyAppLog.log(message = "observed")
        when (resource.status) {
            Status.LOADING -> {
                MyAppLog.log(message = "LOADING...")
                showLoader()
            }
            Status.ERROR -> {
                MyAppLog.log(message = "ERROR")
                hideLoader()
            }
            Status.SUCCESS -> {
                MyAppLog.log(message = "SUCCESS")
                hideLoader()
                resource.data?.let { userData->displayUserData(userData) }
            }
        }
    }

    private fun displayUserData(it: UserInfoPresentationModel) {
        tvHomeUserName.text = it.displayName
        tvHomeAccountNumber.text =
            String.format(
                getString(R.string.display_account_num),
                it.accountNumber
            )
        tvHomeBalance.text =
            String.format(
                getString(R.string.display_account_balance),
                it.accountBalance.toCurrency()
            )

        tvHomeAccountType.text = it.accountType.capitalize()

        userInfoModel= it
    }

    private fun showLoader() {
        pbHomeLoader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        pbHomeLoader.visibility = View.GONE
    }
}
