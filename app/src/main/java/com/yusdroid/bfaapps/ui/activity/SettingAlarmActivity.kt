package com.yusdroid.bfaapps.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yusdroid.bfaapps.R
import com.yusdroid.bfaapps.utils.AlarmReceiver
import kotlinx.android.synthetic.main.activity_setting_alarm.*

class SettingAlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_alarm)

        val alarmReceiver = AlarmReceiver()

        sw_alarm.setOnClickListener {
            if (sw_alarm.isChecked){
                alarmReceiver.setRepeatingAlarm(this)
                Toast.makeText(this, "Set Alarm ON", Toast.LENGTH_SHORT).show()
            }else{
                alarmReceiver.cancelAlarm(this)
                Toast.makeText(this, "Alarm OFF", Toast.LENGTH_SHORT).show()
            }
        }
    }
}