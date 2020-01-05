<template>
<div id="Board">
    <v-row justify="center">
        <v-col cols="10" class="ma-0 pa-0">
            <opponent-hub :player="opponent"/>
            <opponent-spots :spots="opponentSpots"/>
            <spots :gameId="gameId"
                   :ownerId="own.id"
                   :spots="ownSpots"
                   :handcards="ownHandcards"/>
            <player-hub :player="own"/>
        </v-col>
        <v-col cols="2" class="ma-0 pa-0">
            <logs :logs="logs"/>
            <detail-view/>
        </v-col>
    </v-row>
</div>
</template>

<script>
    import {opponent, own} from "./../../util/GameUtil.js";
    import DetailView from "./DetailView.vue";
    import Logs from "./Logs.vue";
    import PlayerHub from "./PlayerHub.vue";
    import OpponentHub from "./OpponentHub.vue";
    import Handcards from "./Handcards.vue";
    import Spots from "./Spots.vue";
    import OpponentSpots from "./OpponentSpots.vue";

    export default {
    name: "game",
    components: {
        DetailView,
        Logs,
        PlayerHub,
        OpponentHub,
        Handcards,
        Spots,
        OpponentSpots
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
