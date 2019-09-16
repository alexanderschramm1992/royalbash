package de.schramm.royalbash.application

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.creature.Boar
import de.schramm.royalbash.domain.card.creature.GoblinGuard
import de.schramm.royalbash.domain.card.spell.ByondInsight
import de.schramm.royalbash.domain.card.spell.Fireball
import de.schramm.royalbash.domain.card.spell.LightningBolt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class TestDataLoader @Autowired constructor(private val repository: Games) {

    @PostConstruct
    fun loadTestData() {

        val boar = Boar(
                id = "boar",
                cost = 2,
                attack = 2,
                hitpoints = 1)
        val goblinGuard = GoblinGuard(
                id = "goblinGuard",
                cost = 3,
                attack = 1,
                hitpoints = 3)
        val fireball = Fireball(id = "fireball", cost = 2)
        val byondInsight = ByondInsight(id = "byondInsight", cost = 2)
        val lightningBolt = LightningBolt(id = "lightningBolt", cost = 2)
        val player1 = Player(
                id = "player1",
                name = "Player 1",
                hitpoints = 25,
                handcards = listOf(boar, fireball, goblinGuard, byondInsight, lightningBolt),
                spots = listOf(Spot(id = "spot1", creature = boar),
                               Spot(id = "spot2", creature = goblinGuard)),
                deckcards = listOf(byondInsight),
                depositcards = listOf(lightningBolt))
        val player2 = Player(
                id = "player2",
                handcards = listOf(byondInsight, boar),
                spots = listOf(Spot(id = "spot1", creature = goblinGuard)),
                name = "Player 2")
        val game = Game(
                id = "1",
                player1 = player1,
                player2 = player2,
                playerOnTurn = player1,
                state = State.OPEN,
                log = Log())

        repository.save(game)
    }
}
