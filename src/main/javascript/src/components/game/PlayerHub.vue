<template>
    <v-card class="pa-2 d-flex justify-space-between">
        <v-container class="d-flex justify-center" fluid>
                <handcards :handcards="player.handcards"
                           :hidden="false"/>
        </v-container>
        <div class="d-flex justify-left">
            <player-deck :playerId="player.playerId"
                  :deck="player.deck"/>
            <div>
                <v-text-field class="pa-2"
                              :value="player.hitpoints"
                              label="Hitpoints"
                              outlined
                              readonly/>
                <v-text-field class="pa-2"
                              :value="player.resources"
                              label="Resources"
                              outlined
                              readonly/>
            </div>
            <player-graveyard :playerId="player.playerId"
                  :graveyardCards="[]"/>
        </div>
    </v-card>
</template>

<script>
    import PlayerDeck from "./PlayerDeck";
    import PlayerGraveyard from "./PlayerGraveyard";
    import Handcards from "./Handcards";

    export default {
        name: "player-hub",
        components: {
            PlayerDeck,
            PlayerGraveyard,
            Handcards
        },
        props: {
            player: Object
        }
    }
</script>

<style scoped>

</style>
