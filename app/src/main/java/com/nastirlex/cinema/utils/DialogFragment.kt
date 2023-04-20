package com.nastirlex.cinema.utils

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.nastirlex.cinema.R

class PurchaseConfirmationDialogFragment(private val message: Int) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(message))
            .setPositiveButton(getString(R.string.ok)) { _,_ -> }
            .create()


    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}