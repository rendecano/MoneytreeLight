package io.rendecano.moneytreelight.accounts.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.rendecano.moneytreelight.R
import io.rendecano.moneytreelight.accounts.presentation.adapter.AccountsListAdapter
import io.rendecano.moneytreelight.accounts.presentation.viewmodel.AccountsListViewModel
import io.rendecano.moneytreelight.common.di.Injectable
import io.rendecano.moneytreelight.databinding.FragmentAccountsListBinding
import javax.inject.Inject

class AccountsListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AccountsListViewModel
    private lateinit var mAdapter: AccountsListAdapter
    private lateinit var viewBinding: FragmentAccountsListBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_accounts_list, container, false)
        viewBinding.lifecycleOwner = this
        return viewBinding.root
    }

    private val selectListener = object : AccountsListAdapter.OnSelectListener {
        override fun onSelect(accountId: Long) {
            
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initModel()

        mAdapter = AccountsListAdapter(this, selectListener)
        val mLayoutManager = LinearLayoutManager(activity)
        viewBinding.recyclerViewAccount.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        viewBinding.recyclerViewAccount.layoutManager = mLayoutManager
        viewBinding.recyclerViewAccount.adapter = mAdapter
    }

    private fun initModel() {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AccountsListViewModel::class.java)

        viewModel.accountsList.observe { list ->
            list.let {
                viewBinding.loading = list?.isNullOrEmpty()
                mAdapter.setData(ArrayList(it ?: listOf()))
            }
        }

        viewModel.accountsTotal.observe {
            viewBinding.accountTotal = it
        }

        viewModel.error.observe {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe {
            viewBinding.loading = it
        }

        // Invoke get accounts
        viewModel.getAccounts()
    }

    private fun <T> LiveData<T>.observe(observe: (T?) -> Unit) =
            observe(activity!!, Observer { observe(it) })
}