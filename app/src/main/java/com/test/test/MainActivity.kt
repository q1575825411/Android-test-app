package com.test.test

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.test.camera.PictureActivity
import com.test.test.databinding.ActivityMainBinding
import com.test.test.databinding.ActivityMainBinding.inflate
import com.test.test.utils.SystemUtils

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    //循环次数
    private var round = 1
    private val TAG = "tianYou_MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        initClick()
    }

    private fun initClick() {
        binding.button.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.button -> {
//                checkPermissionTOChangeLight()
                val list: List<String> = SystemUtils.getLanguages()
                for (i in list.indices) {
                    Log.i("支持的语言列表", list[i])
                    Toast.makeText(this, list[i], Toast.LENGTH_SHORT).show();
                }
            }
            binding.button2 -> {
                val intent = Intent(this, NFCActivity::class.java)
                startActivity(intent)
            }
            binding.button3 -> {
                val intent = Intent(this, LocationActivity::class.java)
                startActivity(intent)
            }
        }
    }


    /**
     * @param 循环修改亮度
     */
    private fun checkPermissionTOChangeLight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_WRITE_SETTINGS,
                    Uri.parse("package:$packageName")
                );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 200);
            } else {
                // 如果有权限做些什么

                for (i in 0 until round) {
                    setScreenBrightness(this, i)
                }
                setScreenBrightness(this, SystemUtils.setNumber())
                binding.textView.text = getScreenBrightness(this).toString()
            }
        }
    }

    private fun setScreenBrightness(context: Context, value: Int) {
        var mValue = value
        if (mValue > 255) {
            mValue = 255
        } else if (mValue < 1) {
            mValue = 1
        }
        try {
            Settings.System.putInt(
                context.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS,
                mValue
            )
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    private fun getScreenBrightness(context: Context): Int {
        return try {
            Settings.System.getInt(
                context.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS
            )
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

}
