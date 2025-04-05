package com.hadirahimi.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.hadirahimi.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var firstNumber = ""
    private var currentNumber = ""
    private var currentOp = ""
    private var result = ""

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding.apply {
            binding.layoutMain.children.filterIsInstance<Button>().forEach { button ->
                button.setOnClickListener{
                    val buttonText = button.text.toString()
                    when{
                        buttonText.matches(Regex("[0-9]"))->{
                            if(currentOp.isEmpty()){
                                firstNumber+=buttonText
                                tvResult.text=firstNumber
                            }
                            else{
                                currentNumber+=buttonText
                                tvResult.text=currentNumber
                            }
                        }

                        buttonText.matches(Regex("[+\\-*/]"))->{
                            currentNumber=""
                            if(tvResult.text.toString().isNotEmpty()){
                                currentOp=buttonText
                                tvResult.text="0"
                            }
                        }

                        buttonText == "="->{
                            if(currentNumber.isNotEmpty() && currentOp.isNotEmpty()){
                                tvFormula.text = "$firstNumber$currentOp$currentNumber"
                                result = calculate(firstNumber, currentNumber, currentOp)
                                firstNumber=result
                                tvResult.text=result
                            }
                        }

                        buttonText == "."->{
                            if(currentOp.isEmpty())
                            {
                                if (! firstNumber.contains("."))
                                {
                                    if(firstNumber.isEmpty())firstNumber+="0$buttonText"
                                    else firstNumber +=buttonText
                                    tvResult.text = firstNumber
                                }
                            }else
                            {
                                if (! currentNumber.contains("."))
                                {
                                    if(currentNumber.isEmpty()) currentNumber+="0$buttonText"
                                    else currentNumber +=buttonText
                                    tvResult.text = currentNumber
                                }
                            }
                        }

                        buttonText == "C"->{
                            currentNumber = ""
                            firstNumber = ""
                            currentOp = ""
                            tvResult.text = "0"
                            tvFormula.text = ""
                        }
                    }
                }
            }
        }
    }

    private  fun calculate(firstNo: String, secondNo: String, op: String):String{
        val n1 = firstNo.toDouble()
        val n2 = secondNo.toDouble()
        return when(op){
            "+"->(n1+n2).toString()
            "-"->(n1-n2).toString()
            "*"->(n1*n2).toString()
            "/"->(n1/n2).toString()
            else -> ""
        }
    }
}













































