package com.projeto.joquempo


import android.os.Bundle // Importação da classe Bundle
import android.widget.ImageView // Importação da classe ImageView
import android.widget.TextView // Importação da classe TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity // Importação da classe AppCompatActivity
import java.util.Random // Importação da classe Random

class MainActivity : AppCompatActivity() {

    private lateinit var imageApp: ImageView // Declaração do ImageView para a escolha do app
    private lateinit var imageView2: ImageView // Declaração do ImageView para Pedra
    private lateinit var imageView3: ImageView // Declaração do ImageView para Papel
    private lateinit var imageView4: ImageView // Declaração do ImageView para Tesoura
    private lateinit var textResultado: TextView // Declaração do TextView para resultado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        imageApp = findViewById(R.id.image_app) // Encontrar o ImageView para a escolha do app pelo ID
        imageView2 = findViewById(R.id.imageView2) // Encontrar o ImageView para Pedra pelo ID
        imageView3 = findViewById(R.id.imageView3) // Encontrar o ImageView para Papel pelo ID
        imageView4 = findViewById(R.id.imageView4) // Encontrar o ImageView para Tesoura pelo ID
        textResultado = findViewById(R.id.text_resultado) // Encontrar o TextView para resultado pelo ID
    }
    fun selecionarPedra(view: android.view.View) { // Método para selecionar Pedra
        playGame("pedra") // Chamar a função playGame com a escolha "pedra"
    }

    fun selecionarPapel(view: android.view.View) { // Método para selecionar Papel
        playGame("papel") // Chamar a função playGame com a escolha "papel"
    }

    fun selecionarTesoura(view: android.view.View) { // Método para selecionar Tesoura
        playGame("tesoura") // Chamar a função playGame com a escolha "tesoura"
    }

    private fun playGame(userChoice: String) { // Método para jogar o jogo
        val computerChoice = getComputerChoice() // Obter a escolha do computador
        val result = determineWinner(userChoice, computerChoice) // Determinar o vencedor

        updateUI(userChoice, computerChoice, result) // Atualizar a interface do usuário
    }

    private fun getComputerChoice(): String { // Método para obter a escolha do computador
        val options = listOf("pedra", "papel", "tesoura") // Lista de opções
        val random = Random() // Criação de um objeto Random
        return options[random.nextInt(options.size)] // Retornar uma escolha aleatória
    }

    private fun determineWinner(userChoice: String, computerChoice: String): String { // Método para determinar o vencedor
        return when {
            userChoice == computerChoice -> "Empate!" // Empate
            (userChoice == "pedra" && computerChoice == "tesoura") ||
                    (userChoice == "papel" && computerChoice == "pedra") ||
                    (userChoice == "tesoura" && computerChoice == "papel") -> "Você ganhou!" // Você ganhou
            else -> "Você perdeu!" // Você perdeu
        }
    }

    private fun updateUI(userChoice: String, computerChoice: String, result: String) { // Método para atualizar a interface do usuário
        imageApp.setImageResource(getImageResource(computerChoice)) // Definir a imagem do ImageView para a escolha do app
        textResultado.text = result // Definir o texto do TextView para resultado
    }

    private fun getImageResource(choice: String): Int { // Método para obter o recurso de imagem
        return when (choice) { // Determinar o recurso de imagem com base na escolha
            "pedra" -> R.drawable.pedra // Recurso de imagem para Pedra
            "papel" -> R.drawable.papel // Recurso de imagem para Papel
            "tesoura" -> R.drawable.tesoura // Recurso de imagem para Tesoura
            else -> R.drawable.padrao // Recurso de imagem padrão
        }
    }
}