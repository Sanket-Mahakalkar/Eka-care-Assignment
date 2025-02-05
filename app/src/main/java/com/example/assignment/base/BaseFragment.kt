package com.example.assignment.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding
import com.example.assignment.R

abstract class BaseFragment<VB: ViewBinding, VM: BaseViewModel<*>> (
    private val layout: Int
): Fragment() {

    abstract val viewModel: VM

    private lateinit var _binding: VB

    protected val binding: VB
        get() = _binding

    private var dialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,layout, container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoadingDialog()
    }

    private fun setupLoadingDialog() {
        viewModel.showLoadingDialog.observe(viewLifecycleOwner){ loading->
            if(loading){
                showLoadingDialog()
            }else{
                hideLoadingDialog()
            }

        }
    }

    private fun hideLoadingDialog() {
        if(dialog != null){
            dialog?.dismiss()
            dialog = null
        }
    }

    private fun showLoadingDialog() {
        try {
            if(dialog == null){
                val builder = AlertDialog.Builder(requireActivity())
                val view = LayoutInflater.from(requireActivity()).inflate(R.layout.layout_progress_bar, null)
                builder.setView(view)
                dialog = builder.create()
            }
            dialog?.setCanceledOnTouchOutside(false)
            dialog?.show()
        }catch (e: Exception){}
    }

    protected fun showToastMessage(message: String?){
        if(isAdded && !isDetached){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}