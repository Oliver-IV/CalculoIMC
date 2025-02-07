package edu.mx.itson.practica4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etKilos = findViewById<EditText>(R.id.etKilos)
        val etEstatura = findViewById<EditText>(R.id.etEstatura)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvIMC = findViewById<TextView>(R.id.tvIMC)
        val tvRange = findViewById<TextView>(R.id.tvRange)

        btnCalcular.setOnClickListener {
            var peso = 0.0
            var estatura = 0.0

            try {
                peso = etKilos.text.toString().toDouble()
                estatura = etEstatura.text.toString().toDouble()
            } catch (e: Exception) {
                tvIMC.text = "Debe ingresar valores reales"
                e.printStackTrace()
            }

            val resultado = calcularIMC(peso, estatura)
            val formattedNumber = "%.2f".format(resultado)
            tvIMC.setText(formattedNumber)
            var salud:String
            var color:Int


            when {
                resultado < 18.5 -> {
                    salud = "Bajo Peso"
                    color = R.color.colorRed
                }
                resultado >= 18.5 && resultado <= 24.9 -> {
                    salud = "Saludable"
                    color = R.color.colorGreenish
                }
                resultado >= 25 && resultado <= 29.9 -> {
                    salud = "Sobrepeso"
                    color = R.color.colorYellow
                }
                resultado >= 30 && resultado <= 34.9 -> {
                    salud = "Obesidad Grado 1"
                    color = R.color.colorOrange
                }
                resultado >= 35 && resultado <= 39.9 -> {
                    salud = "Obesidad Grado 2"
                    color = R.color.colorBrown
                }
                resultado >= 40 -> {
                    salud = "Obesidad Grado 3"
                    color = R.color.colorRed
                }
                else -> {
                    salud = "Error"
                    color = 0
                }
            }

            tvRange.setBackgroundResource(color)
            tvRange.setText(salud)

        }
    }

    private fun calcularIMC(kilos: Double, altura: Double): Double {
        return kilos / (altura * altura)
    }
}