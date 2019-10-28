<template>
  <v-card class="mx-auto" outlined>
    <v-btn v-on:click="drawCard">Draw Card</v-btn>
  </v-card>
</template>

<script>
    import {buildCardDrawnEvent} from "../../util/EventBuilder.js";
    import {COMMIT_EVENT} from "../../MutationTypes.js";

    export default {
    name: "deck",
    props: {
        playerId: String,
        deck: Array
    },
    methods: {
        drawCard() {
            this.$store.commit(COMMIT_EVENT, buildCardDrawnEvent(this.playerId));
        }
    }
}
</script>
