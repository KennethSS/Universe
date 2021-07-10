package com.solar.universe.extension

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.FontRes
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar

//region Permission

fun Context.hasLocationPermission(): Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PermissionChecker.PERMISSION_GRANTED
    } else true

fun Context.isPermissionGranted(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

//endregion

/** Widget **/
fun Context.toast(
    msg: Any,
    length: Int = Toast.LENGTH_SHORT
) {
    if (msg is String) toast(msg)
    else if (msg is Int) toast(getString(msg, length))
}

fun Context.toast(
    msg: String,
    length: Int = Toast.LENGTH_SHORT
) {
    try {
        if (isOnMainThread()) {
            Toast.makeText(this, msg, length).show()
        } else {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(this, msg, length).show()
            }
        }
    } catch (e: Exception) {

    }
}

fun Snackbar.action(
    action: String,
    color: Int? = null,
    listener: (View) -> Unit
) {
    setAction(action, listener)
    color?.let { setActionTextColor(androidx.core.content.ContextCompat.getColor(context, color)) }
}

/** Intent **/
fun Context.goToAppPlayStore() {
    val appPackageName = packageName
    val marketUri = "market://details?id="
    val playStoreUri = "https://play.google.com/store/apps/details?id="

    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(marketUri + appPackageName)))
    } catch (anfe: android.content.ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(playStoreUri + appPackageName)))
    }
}

fun Context.goToNotificationSetting(
    notificationChannelId: String,
    osRowMsg: String = "버전이 너무 낮습니다."
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startActivity(Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            putExtra(Settings.EXTRA_CHANNEL_ID, notificationChannelId)
        })
    } else {
        toast(osRowMsg)
    }
}

/** isCheck **/
fun Context.isInstallApp(pkgName: String): Boolean {
    return try {
        packageManager.getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Context.getJsonFromAsset(name: String): String {
    val inputStream = resources.assets.open(name)
    return inputStream.bufferedReader().use { it.readText() }
}

fun Context.isLongResolution(): Boolean {
    val width = resources.displayMetrics.widthPixels.toFloat()
    val height = resources.displayMetrics.heightPixels.toFloat()
    //(displayMetrics.heightPixels / 16) * 10 > displayMetrics.widthPixels
    return (height / width) > 1.8979
}

/** Resource **/
fun Context.resColor(resId: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) resources.getColor(resId, null)
    else resources.getColor(resId)
}

fun Context.drawable(resId: Int): Drawable? = run {
    ContextCompat.getDrawable(this, resId)
}

fun Context.dimenToInt(id: Int): Int = run {
    resources.getDimension(id).toInt()
}

fun Context.inflater(): LayoutInflater = run {
    (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
}

fun Context.dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

/** Hardware **/
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = cm.activeNetwork ?: return false
        val actNw = cm.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {
        return cm.activeNetworkInfo?.isConnected ?: false
    }
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context?.isOnline(): Boolean {
    this?.apply {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
    return false
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isWiFiConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = cm.activeNetwork ?: return false
        return cm.getNetworkCapabilities(nw)?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
    } else {
        return cm.activeNetworkInfo?.type == ConnectivityManager.TYPE_WIFI
    }
}

fun Context.getFontCompat(@FontRes fontId: Int): Typeface? =
    runCatching { ResourcesCompat.getFont(this, fontId) }.getOrNull()

fun Context.getHtmlVariable(
    res: Int,
    vararg: Any
): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(getString(res, vararg), Html.FROM_HTML_MODE_COMPACT)
    } else {
        return Html.fromHtml(getString(res, vararg))
    }
}

/** Permission **/
fun Context.isGranted(permission: String): Boolean =
    ActivityCompat.checkSelfPermission(this, permission) == PermissionChecker.PERMISSION_GRANTED

fun Context.isGpsEnabled(): Boolean =
    (getSystemService(Context.LOCATION_SERVICE) as LocationManager).isProviderEnabled(
        LocationManager.GPS_PROVIDER
    )

/** Dialog **/
fun Context.showAlertDialog(
    title: String = "",
    msg: String = "",
    positiveText: String = getString(android.R.string.ok),
    negativeText: String = getString(android.R.string.cancel),
    positive: DialogInterface.OnClickListener,
    cancelable: Boolean = true
) {
    AlertDialog.Builder(ContextThemeWrapper(this, android.R.style.Theme_DeviceDefault_Light))
        .apply {
            if (title.isNotEmpty()) setTitle(title)
            if (positiveText.isNotEmpty()) setPositiveButton(positiveText, positive)
            if (negativeText.isNotEmpty()) setNegativeButton(negativeText, null)
            if (msg.isNotEmpty()) setMessage(msg)
            setCancelable(cancelable)
            create()
            show()
        }
}

fun Context.showDialog(
    title: Any? = null,
    msg: Any,
    cancelable: Boolean = true,
    positive: Any = "확인",
    negative: Any = "취소",
    positiveClick: (DialogInterface, Int) -> Unit,
    negativeClick: (DialogInterface, Int) -> Unit = { _, _ -> }
) {
    val builder = AlertDialog.Builder(this).apply {
        setCancelable(cancelable)
    }

    when (title) {
        is String -> builder.setTitle(title)
        is Int -> builder.setTitle(title)
    }

    when (msg) {
        is String -> builder.setMessage(msg)
        is Int -> builder.setMessage(msg)
    }

    when (positive) {
        is String -> builder.setPositiveButton(positive, positiveClick)
        is Int -> builder.setPositiveButton(positive, positiveClick)
    }

    when (negative) {
        is String -> builder.setNegativeButton(negative, negativeClick)
        is Int -> builder.setNegativeButton(negative, negativeClick)
    }

    builder.create()
    builder.show()
}

fun Context.showAppNotificationSettings() {
    val intent = Intent()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
        intent.putExtra("app_package", packageName)
        intent.putExtra("app_uid", applicationInfo.uid)
    } else {
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.data = Uri.parse("package:" + packageName)
    }
    startActivity(intent)
}

fun isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()