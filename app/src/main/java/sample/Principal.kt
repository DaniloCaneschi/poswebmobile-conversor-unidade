package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editQuantidade = findViewById<EditText>(R.id.edtQuantidade)
        val cbUnidadeOrigem = findViewById<Spinner>(R.id.cbUnidadeOrigem)
        val cbUnidadeDestino = findViewById<Spinner>(R.id.cbUnidadeDestino)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val textResultado = findViewById<TextView>(R.id.textResultado)

        val adapter = ArrayAdapter.createFromResource(this, 0, android.R.layout.simple_spinner_dropdown_item)

        cbUnidadeOrigem.adapter = adapter
        cbUnidadeDestino.adapter = adapter

        btnCalcular.setOnClickListener {
            val quantidade = editQuantidade.text.toString()
            if (quantidade.isEmpty()) {
                Toast.makeText(this, "Digita a Quantidade a ser convertida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var resultado: Double = quantidade.toDouble()
            val unidadeOrigem = cbUnidadeDestino.selectedItem.toString()
            val unidadeDestino = cbUnidadeOrigem.selectedItem.toString()

            val toMetro: Double = when (unidadeOrigem) {
                "mm" -> 0.001
                "cm" -> 0.01
                "dm" -> 0.1
                "m" -> 1.0
                "dam" -> 10.0
                "hm" -> 100.0
                "km" -> 1000.0
                else -> 0.0
            }

            val toDestino: Double = when (unidadeDestino) {
                "mm" -> 1000.0
                "cm" -> 100.0
                "dm" -> 10.0
                "m" -> 1.0
                "dam" -> 0.1
                "hm" -> 0.01
                "km" -> 0.001
                else -> 0.0
            }

            resultado *= toMetro * toDestino

            textResultado.text = resultado.toString()
        }
    }
}