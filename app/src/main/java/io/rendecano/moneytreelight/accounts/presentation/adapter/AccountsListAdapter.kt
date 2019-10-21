package io.rendecano.moneytreelight.accounts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import io.rendecano.moneytreelight.R
import io.rendecano.moneytreelight.accounts.domain.model.Account
import io.rendecano.moneytreelight.databinding.ListItemAccountBinding

class AccountsListAdapter(private val bindingLifecycleOwner: LifecycleOwner,
                          private val selectListener: OnSelectListener) :
        RecyclerView.Adapter<AccountsListAdapter.StockViewHolder>() {

    private val accountList = ArrayList<Account>()

    interface OnSelectListener {
        fun onSelect(accountId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StockViewHolder(ListItemAccountBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.binding.apply {
            lifecycleOwner = bindingLifecycleOwner
            account = accountList[position]
            executePendingBindings()
        }

        holder.binding.layoutInfo.setOnClickListener {
            selectListener.onSelect(accountList[position].id)
        }
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    class StockViewHolder(val binding: ListItemAccountBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return R.layout.list_item_account
    }

    fun setData(accountListData: ArrayList<Account>) {
        accountList.clear()
        accountList.addAll(accountListData)
        notifyDataSetChanged()
    }
}