package com.zzp.dtrip.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textfield.TextInputLayout
import com.zzp.dtrip.R
import com.zzp.dtrip.body.PhoneBody
import com.zzp.dtrip.body.RegisterBody
import com.zzp.dtrip.data.NormalResult
import com.zzp.dtrip.data.PhoneResult
import com.zzp.dtrip.util.AppService
import com.zzp.dtrip.util.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var phoneNumber: TextInputLayout
    private lateinit var code: TextInputLayout
    private lateinit var passwordAgainLayout: TextInputLayout
    private lateinit var registerButton: MaterialButton
    private lateinit var manButton: MaterialRadioButton
    private lateinit var womanButton: MaterialRadioButton
    private lateinit var radioGroup: RadioGroup

    private var username = ""
    private var password = ""
    private var passwordAgain = ""
    private var sex = "男"
    private var phone = ""
    private var messageCode = ""
    private val TAG = "RegisterActivity"

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById()
        setFocus()
        code.setEndIconOnClickListener {
            phone = phoneNumber.editText?.text.toString()
//            Log.d("phone",phone)
            if (phone.isEmpty()) {
                phoneNumber.error = "手机号为空"
            } else {
                val num = Regex("[1][35789]\\d{9}")
                if (phone.matches(num)) {
                    getCode()
                } else {
                    phoneNumber.error = "手机号格式不正确"
                }
            }
        }
        //给后端上传用户信息
        registerButton.setOnClickListener {
            username = usernameLayout.editText?.text.toString()
            password = passwordLayout.editText?.text.toString()
            passwordAgain = passwordAgainLayout.editText?.text.toString()
            phone = phoneNumber.editText?.text.toString()
            messageCode = code.editText?.text.toString()
            if (isNotEmpty()) {
                if (password != passwordAgain) {
                    passwordAgainLayout.error = "密码不一致"
                } else {
                    postRegister()
                }
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.register_man -> {
                    manButton.setTextColor(resources.getColor(R.color.white))
                    womanButton.setTextColor(resources.getColor(R.color.gray))
                    sex = "男"
                }
                R.id.register_woman -> {
                    womanButton.setTextColor(resources.getColor(R.color.white))
                    manButton.setTextColor(resources.getColor(R.color.gray))
                    sex = "女"
                }
            }
        }
    }

    private fun findViewById() {
        usernameLayout = findViewById(R.id.username_layout)
        passwordLayout = findViewById(R.id.password_layout)
        passwordAgainLayout = findViewById(R.id.password2_layout)
        registerButton = findViewById(R.id.register_button)
        manButton = findViewById(R.id.register_man)
        womanButton = findViewById(R.id.register_woman)
        radioGroup = findViewById(R.id.radio_group)
        phoneNumber = findViewById(R.id.phoneNumber)
        code = findViewById(R.id.code)
    }

    private fun postRegister() {
        val appService = RetrofitManager.create<AppService>()
        val sexNum = if (sex == "男") 0 else 1
        val task = appService.postRegister(
            RegisterBody(
                username,
                password,
                sexNum.toString(),
                messageCode,
                phone
            )
        )
        task.enqueue(object : Callback<NormalResult> {
            override fun onResponse(
                call: Call<NormalResult>,
                response: Response<NormalResult>
            ) {
                Log.d(TAG, "onResponse: ")
                response.body()?.apply {
                    if (errorCode == 0) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "注册成功", Toast.LENGTH_SHORT
                        ).show()
                        onBackPressed()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            errorMsg, Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<NormalResult>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })
    }

    private fun getCode() {
        val appService = RetrofitManager.create<AppService>()
//        val task = appService.getCode(PhoneBody("18173916150"))
        val task = appService.getCode(RequestBody.create(MediaType.parse("text"), "18173916150"))
        Log.d("phone", phone)
        task.enqueue(object : Callback<NormalResult> {
            override fun onResponse(
                call: Call<NormalResult>,
                response: Response<NormalResult>
            ) {
                Log.d(TAG, "onResponse: ")
                response.body()?.apply {
                    Log.d("Phone", errorCode.toString())
                    if (errorCode == 0) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "注册成功", Toast.LENGTH_SHORT
                        ).show()
//                        onBackPressed()
                        refreshCodeLayout()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            errorMsg, Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<NormalResult>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })
    }

    private fun setFocus() {
        usernameLayout.editText?.setOnFocusChangeListener { v, hasFocus ->
            usernameLayout.error = ""
        }
        passwordLayout.editText?.setOnFocusChangeListener { v, hasFocus ->
            passwordLayout.error = ""
        }
        passwordAgainLayout.editText?.setOnFocusChangeListener { v, hasFocus ->
            passwordAgainLayout.error = ""
        }
    }

    private fun refreshCodeLayout() {
        code.isEndIconVisible = false
        code.requestFocus()
        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(p0: Long) {
                code.suffixText = (p0 / 1000).toString()
            }

            override fun onFinish() {
                code.suffixText = ""
                code.isEndIconVisible = true
                cancel()
            }
        }
        timer?.start()
    }

    private fun isNotEmpty(): Boolean {
        var flag = true
        if (username.trim().isEmpty()) {
            usernameLayout.error = "用户名为空"
            flag = false
        } else {
            usernameLayout.error = ""
        }
        if (password.trim().isEmpty()) {
            passwordLayout.error = "密码为空"
            flag = false
        } else {
            passwordLayout.error = ""
        }
        if (passwordAgain.trim().isEmpty()) {
            passwordAgainLayout.error = "密码为空"
            flag = false
        } else {
            passwordAgainLayout.error = ""
        }
        if (phone.trim().isEmpty()) {
            phoneNumber.error = "电话号码为空"
            flag = false
        } else {
            passwordAgainLayout.error = ""
        }
        if (messageCode.trim().isEmpty()) {
            code.error = "验证码为空"
            flag = false
        } else {
            passwordAgainLayout.error = ""
        }
        return flag
    }
}

