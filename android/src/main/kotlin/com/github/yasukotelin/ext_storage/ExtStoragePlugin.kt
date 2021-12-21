package com.github.yasukotelin.ext_storage

import android.os.Environment
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class ExtStoragePlugin: MethodCallHandler, FlutterPlugin {
  override fun onMethodCall(call: MethodCall, result: Result) {
    when (call.method) {
      "getExternalStorageDirectory" ->
        result.success(Environment.getExternalStorageDirectory().toString());
      "getExternalStoragePublicDirectory" -> {
        val type = call.argument<String>("type")
        result.success(Environment.getExternalStoragePublicDirectory(type).toString());
      }
      else -> result.notImplemented()
    }
  }

  override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    val channel = MethodChannel(binding.binaryMessenger, "ext_storage")
    channel.setMethodCallHandler(this)
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
  }
}
