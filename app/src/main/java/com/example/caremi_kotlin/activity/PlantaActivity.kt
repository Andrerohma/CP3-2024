package com.example.caremi_kotlin.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.caremi_kotlin.R
import com.example.caremi_kotlin.model.Atendimento

class PlantaActivity : Activity() {

    private lateinit var plantaRepository: PlantaRepository

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val edtDescricao = findViewById<EditText>(R.id.edtDescricao)
        val edtEspecie = findViewById<EditText>(R.id.edtEspecie)
        val edtTemponoSol = findViewById<EditText>(R.id.edtTemponoSol)
        val edtIntervalodeAgua = findViewById<EditText>(R.id.edtIntervalodeAgua)


        plantaRepository = PlantaRepository()

        btnEnviar.setOnClickListener {
            val descricao = edtDescricao.text.toString()
            val Especie = edtEspecie.text.toString()
            val TemponoSol = edtTemponoSol.text.toString()
            val IntervalodeAgua = edtIntervalodeAgua.text.toString()


            val atendimento = Atendimento(null, descricao, especie, temponosol, intervalodeagua)

            plantaRepository.gravarPlanta(planta) { sucesso, mensagem ->
                runOnUiThread {
                    if (sucesso) {
                        Toast.makeText(this, "Informações da planta gravados com sucesso!", Toast.LENGTH_SHORT).show()
                        edtDescricao.text.clear()
                        edtEspecie.text.clear()
                        edtTemponoSol.text.clear()
                        edtIntervalodeAgua.text.clear()

                    } else {
                        Toast.makeText(this, "Erro ao gravar as Informações: $mensagem", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
