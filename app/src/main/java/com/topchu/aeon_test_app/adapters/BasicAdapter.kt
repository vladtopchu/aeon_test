package com.topchu.aeon_test_app.adapters

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.topchu.aeon_test_app.data.remote.models.PaymentModel
import com.topchu.aeon_test_app.databinding.ItemPaymentBinding
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

class BasicAdapter(): RecyclerView.Adapter<BasicAdapter.BasicViewHolder>() {

    private var listData: List<PaymentModel>? = null

    fun setPayments(listData: List<PaymentModel>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasicViewHolder {
        val binding = ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null) return 0
        return listData?.size!!
    }

    class BasicViewHolder(private val binding: ItemPaymentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(payment: PaymentModel){
            binding.description.text = payment.desc
            binding.amount.text = payment.amount.toString()
            binding.currency.text = payment.currency
            binding.createdAt.text = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ROOT).format(Date(payment.created!! * 1000))
        }
    }
}