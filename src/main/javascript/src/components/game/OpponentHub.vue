<template>
    <v-container>
        <v-card class="pa-2 d-flex justify-space-between">
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

    export default {
        name: "opponent-hub",
        props: {
            player: Object
        },
    }
</script>
