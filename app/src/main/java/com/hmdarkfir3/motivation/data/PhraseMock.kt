package com.hmdarkfir3.motivation.data

import com.hmdarkfir3.motivation.models.PhraseModel
import com.hmdarkfir3.motivation.utils.Constants
import kotlin.random.Random

class PhraseMock {
    private val listPhrases = listOf(
        PhraseModel("Não sabendo que era impossível, foi lá e fez.", HAPPY),
        PhraseModel("Você não é derrotado quando perde, você é derrotado quando desiste!", HAPPY),
        PhraseModel("Quando está mais escuro, vemos mais estrelas!", HAPPY),
        PhraseModel("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", HAPPY),
        PhraseModel("Não pare quando estiver cansado, pare quando tiver terminado.", HAPPY),
        PhraseModel("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", HAPPY),
        PhraseModel("A melhor maneira de prever o futuro é inventá-lo.", SUNNY),
        PhraseModel("Você perde todas as chances que você não aproveita.", SUNNY),
        PhraseModel("Fracasso é o condimento que dá sabor ao sucesso.", SUNNY),
        PhraseModel(" Enquanto não estivermos comprometidos, haverá hesitação!", SUNNY),
        PhraseModel("Se você não sabe onde quer ir, qualquer caminho serve.", SUNNY),
        PhraseModel("Se você acredita, faz toda a diferença.", SUNNY),
        PhraseModel("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", SUNNY)
    )

    fun getPhrase(categoryId: Int): String {
        val filteredPhrases = listPhrases.filter { it.categoryId == categoryId || categoryId == ALL }
        val rand = Random.nextInt(filteredPhrases.size)

        return filteredPhrases[rand].description
    }

    companion object {
        private const val ALL = Constants.Category.ALL
        private const val HAPPY = Constants.Category.HAPPY
        private const val SUNNY = Constants.Category.SUNNY
    }
}