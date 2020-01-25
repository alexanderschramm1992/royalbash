<template>
    <v-card class="ma-2 pa-2 d-flex justify-space-between align-center">
        <div class="d-flex justify-center">
            <handcards :handcards="player.handcards"
                       :hidden="false"/>
        </div>
        <v-divider vertical/>
        <player-deck :playerId="player.playerId"
                     :deck="player.deck"/>
        <div class="d-flex flex-column justify-space-between">
            <v-text-field class=""
                          :value="player.hitpoints"
                          label="Hitpoints"
                          outlined
                          readonly/>
            <v-text-field class=""
                          :value="player.resources"
                          label="Resources"
                          outlined
                          readonly/>
            <v-btn class="caption"
                   @click="endTurn()">
                End Turn
            </v-btn>
        </div>
        <player-graveyard :playerId="player.playerId"
                          :graveyardCards="[]"/>
    </v-card>
</template>

<script>
    import PlayerDeck from "./PlayerDeck";
    import PlayerGraveyard from "./PlayerGraveyard";
    import Handcards from "./Handcards";
    import {COMMIT_EVENT} from "../../MutationTypes";
    import {buildEndTurnEvent} from "../../util/EventBuilder";

    export default {
        name: "player-hub",
        components: {
            PlayerDeck,
            PlayerGraveyard,
            Handcards
        },
        props: {
            player: Object
        },
        methods: {
            endTurn() {
                console.log("commiting event to end turn");
                this.$store.commit(COMMIT_EVENT, buildEndTurnEvent(this.player.id));
            }
        }
    }
</script>

<style scoped>

</style>
