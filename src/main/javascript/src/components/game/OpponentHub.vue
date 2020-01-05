<template>
    <v-card class="pa-2 d-flex justify-space-between">
        <div class="d-flex justify-left">
            <opponent-deck/>
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
            <opponent-graveyard :playerId="player.playerId"
                  :graveyardCards="[]"/>
        </div>
        <v-container class="d-flex justify-center" fluid>
            <handcards :handcards="player.handcards"
                       :hidden="true"/>
        </v-container>
    </v-card>
</template>

<script>
    import OpponentDeck from "./OpponentDeck";
    import OpponentGraveyard from "./OpponentGraveyard";
    import Handcards from "./Handcards";

    export default {
        name: "opponent-hub",
        components: {
            OpponentDeck,
            OpponentGraveyard,
            Handcards
        },
        props: {
            player: Object
        },
    }
</script>
