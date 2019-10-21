package io.rendecano.moneytreelight.transactions.presentation.ui

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
import io.rendecano.moneytreelight.accounts.presentation.ui.ACCOUNT_ID_KEY
import io.rendecano.moneytreelight.common.di.Injectable
import io.rendecano.moneytreelight.databinding.FragmentTransactionsListBinding
import io.rendecano.moneytreelight.transactions.presentation.adapter.TransactionsListAdapter
import io.rendecano.moneytreelight.transactions.presentation.viewmodel.TransactionListViewModel
import javax.inject.Inject

class TransactionListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TransactionListViewModel
    private lateinit var mAdapter: TransactionsListAdapter
    private lateinit var viewBinding: FragmentTransactionsListBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_transactions_list, container, false)
        viewBinding.lifecycleOwner = this
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAdapter = TransactionsListAdapter(this)
        val mLayoutManager = LinearLayoutManager(activity)
        viewBinding.recyclerViewTransactions.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        viewBinding.recyclerViewTransactions.layoutManager = mLayoutManager
        viewBinding.recyclerViewTransactions.adapter = mAdapter

        initModel()
    }

    private fun initModel() {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TransactionListViewModel::class.java)

        viewModel.transactions.observe { list ->
            list.let {
                viewBinding.loading = list?.isNullOrEmpty()
                mAdapter.setData(ArrayList(it ?: listOf()))
            }
        }

        viewModel.error.observe {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe {
            viewBinding.loading = it
        }

        // Invoke get accounts
        val accountId = arguments?.getLong(ACCOUNT_ID_KEY) ?: 0
        viewModel.getTransactions(accountId)
    }

    private fun <T> LiveData<T>.observe(observe: (T?) -> Unit) =
            observe(activity!!, Observer { observe(it) })
}