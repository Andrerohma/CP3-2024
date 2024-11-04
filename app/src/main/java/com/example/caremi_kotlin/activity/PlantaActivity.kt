package com.example.caremi_kotlin.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.caremi_kotlin.R
import com.example.caremi_kotlin.model.Planta
import com.example.caremi_kotlin.viewmodel.PlantaViewModel

class PlantaActivity : Activity() {

    private val plantaViewModel: PlantaViewModel by viewModels()

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_planta)

        val edtDescricao = findViewById<EditText>(R.id.edtDescricao)
        val edtEspecie = findViewById<EditText>(R.id.edtEspecie)
        val edtTemponoSol = findViewById<EditText>(R.id.edtTemponoSol)
        val edtIntervalodeAgua = findViewById<EditText>(R.id.edtIntervalodeAgua)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val descricao = edtDescricao.text.toString()
            val especie = edtEspecie.text.toString()
            val tempoNoSol = edtTemponoSol.text.toString()
            val intervaloDeAgua = edtIntervalodeAgua.text.toString()

            val planta = Planta(
                descricao = descricao,
                especie = especie,
                tempoNoSol = tempoNoSol,
                intervaloDeAgua = intervaloDeAgua
            )

            plantaViewModel.addPlanta(planta) { sucesso ->
                if (sucesso) {
                    Toast.makeText(this, "Informações da planta gravadas com sucesso!", Toast.LENGTH_SHORT).show()
                    edtDescricao.text.clear()
                    edtEspecie.text.clear()
                    edtTemponoSol.text.clear()
                    edtIntervalodeAgua.text.clear()
                } else {
                    Toast.makeText(this, "Erro ao gravar as informações", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}