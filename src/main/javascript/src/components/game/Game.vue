<template>
<div id="Board">
    <v-row justify="center">
        <v-col cols="10">
            <v-container class="opponent-deck" fluid>
                <deck v-bind:gameId="gameId"
                      v-bind:playerId="opponent.id"
                      v-bind:deck="opponentDeckcards"/>
            </v-container>
            <v-container class="opponent-handcards" fluid>
                <handcards v-bind:handcards="opponentHandcards"/>
            </v-container>
            <v-container class="opponent-spots" fluid>
                <spots v-bind:gameId="gameId"
                       v-bind:ownerId="opponent.id"
                       v-bind:spots="opponentSpots"
                       v-bind:handcards="opponentHandcards"/>
            </v-container>
            <v-container class="own-spots" fluid>
                <spots v-bind:gameId="gameId"
                       v-bind:ownerId="own.id"
                       v-bind:spots="ownSpots"
                       v-bind:handcards="ownHandcards"/>
            </v-container>
            <v-container class="own-handcards" fluid>
                <handcards v-bind:handcards="ownHandcards"/>
            </v-container>
            <v-container class="own-deck" fluid>
                <deck v-bind:gameId="gameId"
                      v-bind:playerId="own.id"
                      v-bind:deck="ownDeckcards"/>
            </v-container>
        </v-col>
        <v-col cols="2">
            <Logs :logs="logs"/>
            <DetailView/>
        </v-col>
    </v-row>
</div>
</template>

<script>
    import {opponent, own} from "./../../util/GameUtil.js";
    import DetailView from "./DetailView.vue";
    import Handcards from "./Handcards.vue";
    import Spots from "./Spots.vue";
    import Deck from "./Deck.vue";
    import Logs from "./Logs";

    export default {
    name: "game",
    components: {
        DetailView,
        Logs,
        Handcards,
        Spots,
        Deck
    },
    computed: {
        game() { return this.$store.state.game },
        gameId() { return this.game ? this.game.id : undefined },
        logs() { return this.game ? this.game.logs : [] },
        own() { return this.game ? own(this.$store.state.playerId, this.game) : undefined },
        ownHandcards() { return this.own ? this.own.handcards : [] },
        ownDeckcards() { return this.own ? this.own.deckcards : [] },
        ownSpots() { return this.own ? this.own.spots : [] },
        opponent() { return this.game ? opponent(this.$store.state.playerId, this.game) : undefined },
        opponentHandcards() { return this.opponent ? this.opponent.handcards : [] },
        opponentDeckcards() { return this.opponent ? this.opponent.deckcards : [] },
        opponentSpots() { return this.opponent ? this.opponent.spots : [] }
    }
}
</script>
