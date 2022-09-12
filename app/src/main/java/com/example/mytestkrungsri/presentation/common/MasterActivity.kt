package com.example.mytestkrungsri.presentation.common


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mytestkrungsri.R
import com.example.mytestkrungsri.clazz.OnClickListener
import com.example.mytestkrungsri.utils.DialogUtils
import com.example.mytestkrungsri.utils.ProgressDialog
import com.example.mytestkrungsri.utils.UserInteractionAwareCallback
import kotlinx.android.synthetic.main.app_bar.*

open class MasterActivity : AppCompatActivity() {

    private var isBack: Boolean = true
    private var isNavbar: Boolean = true
    private var textTitle: TextView? = null

    private var progress: Dialog? = null
    private var dialogMsg: AlertDialog? = null
    private var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DialogUtils.dialogMsg = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    fun initMaster() {
        if (isBack) {
            icBack.visibility = View.VISIBLE
            icBack.setOnClickListener(object : OnClickListener() {
                override fun onClicked(v: View) {
                    onBackPressed()
                }
            })
        } else {
            icBack.visibility = View.GONE
        }
    }

    fun initDialog(_activity: Activity) {
        activity = _activity
        progress = ProgressDialog.progressDialog(activity!!)
    }

    fun initDialog(_activity: Activity, textStatus: String) {
        activity = _activity
        progress = ProgressDialog.progressDialog(activity!!, textStatus)
    }

    fun setTitle(titleName: String) {
        if (textTitle != null) {
            return
        }

        textTitle = textTitleLeft
        textTitleLeft!!.visibility = View.VISIBLE
        textTitleCenter!!.visibility = View.GONE
        textTitleRight!!.visibility = View.GONE
        textTitle!!.text = titleName
    }

    fun setBackButtonVisible(value: Boolean) {
        isBack = value
    }

    fun setTitle(
        titleName: String,
        align: Paint.Align,
        color: Int = ContextCompat.getColor(this, R.color.colorDarkLight),
        backgroundColor: Int? = null
    ) {
        if (textTitle != null) {
            return
        }

        textTitleLeft.visibility = View.GONE
        textTitleCenter.visibility = View.GONE
        textTitleRight.visibility = View.GONE
        when (align) {
            Paint.Align.LEFT -> {
                textTitle = textTitleLeft
                textTitleLeft.visibility = View.VISIBLE
            }
            Paint.Align.CENTER -> {
                textTitle = textTitleCenter
                textTitleCenter.visibility = View.VISIBLE
            }
            Paint.Align.RIGHT -> {
                textTitle = textTitleRight
                textTitleRight.visibility = View.VISIBLE
            }
        }

        if (backgroundColor != null) {
            toolbar.setBackgroundResource(backgroundColor)
        }

        textTitle!!.text = titleName
        textTitle!!.setTextColor(color)
    }

    fun nextPage(intent: Intent) {
        nextPage(intent, null)
    }

    fun nextPage(intent: Intent, subtitle: String?) {
        startActivity(intent)
    }

    fun nextPage(clazz: Class<*>) {
        nextPage(clazz, null)
    }

    fun nextPage(clazz: Class<*>, subtitle: String?) {
        val intent = Intent(this@MasterActivity, clazz)
        startActivity(intent)
    }

    fun nextPageForResult(intent: Intent, requestCode: Int) {
        nextPageForResult(intent, requestCode, null)
    }

    fun nextPageForResult(intent: Intent, requestCode: Int, subtitle: String?) {

        startActivityForResult(intent, requestCode)
    }

    fun nextPageForResult(clazz: Class<*>, requestCode: Int) {
        nextPageForResult(clazz, requestCode, null)
    }

    fun nextPageForResult(clazz: Class<*>, requestCode: Int, subtitle: String?) {
        var intent = Intent(this@MasterActivity, clazz)
        startActivityForResult(intent, requestCode)
    }

    open fun onSettingButton() {}

    open fun onBackButton() {}


    fun dismissDialog() {
        if (dialogMsg != null && dialogMsg!!.isShowing) {
            dialogMsg?.dismiss()
        }
    }

    private fun initDialogMaster() {
        if (activity == null) {
            initDialog(this@MasterActivity)
        }
    }

    fun initDialogMaster(_activity: Activity) {
        if (activity == null) {
            initDialog(_activity)
        }
    }

    fun isLoading(): Boolean {
        return progress?.isShowing != false
    }

    fun showLoading() {
        if (progress?.isShowing == false)
            progress?.show()
    }

    fun closeLoading() {
        if (progress?.isShowing == true)
            progress?.dismiss()
    }

    private fun setDialogHideNavBar(dialog: Dialog?) {
        if (!isNavbar && dialog != null) {
            setWindowUIVisibility(dialog.window)
        }
    }

    private fun hideNavbar() {
        if (!isNavbar) {
            setWindowUIVisibility(window)
        }
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setWindowUIVisibility(window: Window?) {
        window!!.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

}