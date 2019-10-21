package io.rendecano.moneytreelight.transactions.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import io.rendecano.moneytreelight.R
import io.rendecano.moneytreelight.databinding.ListItemTransactionBinding
import io.rendecano.moneytreelight.transactions.domain.model.Transaction

class TransactionsListAdapter(private val bindingLifecycleOwner: LifecycleOwner) :
        RecyclerView.Adapter<TransactionsListAdapter.TransactionViewHolder>() {

    private val transactionList = ArrayList<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TransactionViewHolder(ListItemTransactionBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.binding.apply {
            lifecycleOwner = bindingLifecycleOwner
            transaction = transactionList[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class TransactionViewHolder(val binding: ListItemTransactionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return R.layout.list_item_account
    }

    fun setData(accountListData: ArrayList<Transaction>) {
        transactionList.clear()
        transactionList.addAll(accountListData)
        notifyDataSetChanged()
    }
}