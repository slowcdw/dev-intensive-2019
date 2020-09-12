package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var benderImage : ImageView
    lateinit var textTxt : TextView
    lateinit var messageEt : EditText
    lateinit var sendBtn : ImageView
    lateinit var benderObj : Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        benderImage = findViewById(R.id.iv_bender) as ImageView
        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send
        messageEt.setOnEditorActionListener { v, actionId, event ->
//            Toast.makeText(getApplicationContext(), "Some key pressed!", Toast.LENGTH_LONG).show();
//            Log.d("M_MainActivity", "!!!!!!!!!!!!!!!!!!!!!!!!!!!")
//            Log.d("M_MainActivity", actionId.toString())
            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                Log.d("M_MainActivity", "onEditorAction")
                sendBtn.callOnClick()
                this.hideKeyboard()
                true;
            } else {
                false
            }
        }


        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        Log.d("M_MainActivity","onCreate $status $question")

        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY )

        textTxt.text = (benderObj.askQuestion())
        sendBtn.setOnClickListener(this)

    }

    @Override
    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity","onRestart")
    }

    @Override
    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity","onStart")
    }

    @Override
    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity","onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        Log.d("M_MainActivity","onSaveInstanceState ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.iv_send){
//            val (phrace, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            if (messageEt.text.toString().isNullOrBlank()) return
            val (phrace, color) = benderObj.listenAnswer(messageEt.text.toString())
            messageEt.setText("")
            val (r,g,b) = color
            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY )
            textTxt.text = phrace
        }
    }
}
