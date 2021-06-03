package org.bd.rosc.parse_json

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bd.rosc.parse_json.databinding.EmployeeBinding


/**
 * Created by Md.Tarikuzzaman on 03-Jun-2021 3:57 PM, ROSC-II MIS Cell, LGED.
 */
class RecyclerViewAdapter(private val employee: ArrayList<Employee>) :
  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  class ItemViewHolder(var viewBinding: EmployeeBinding) : RecyclerView.ViewHolder(viewBinding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val binding = EmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ItemViewHolder(binding)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val itemViewHolder = holder as ItemViewHolder
    itemViewHolder.viewBinding.employeeIdTextview.text = employee[position].employeeId
    itemViewHolder.viewBinding.employeeNameTextview.text = employee[position].employeeName
    itemViewHolder.viewBinding.employeeSalaryTextview.text = employee[position].employeeSalary
    itemViewHolder.viewBinding.employeeAgeTextview.text = employee[position].employeeAge
  }

  override fun getItemCount(): Int {
    return employee.size
  }
}