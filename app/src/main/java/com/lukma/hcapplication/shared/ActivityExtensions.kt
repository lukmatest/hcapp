package com.lukma.hcapplication.shared

import android.app.Activity
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackBar(message: String) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
}
