<template>
    <v-container class="ml-2 mr-2 d-flex justify-space-between">
        <v-card class="pa-2">
            <deck :playerId="player.playerId"
                  :deck="player.deck"/>
        </v-card>
        <v-card class="pa-2 d-flex justify-center flex-grow-1">
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
        </v-card>
    </v-container>
</template>

<script>
    import Deck from "./Deck";

    export default {
        name: "player-hub",
        components: {
            Deck
        },
        props: {
            player: Object
        },

    }
</script>

<style scoped>

</style>
