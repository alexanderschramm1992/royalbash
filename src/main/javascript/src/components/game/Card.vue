<template>
    <v-tooltip :disabled="!hasText" bottom>
        <template v-slot:activator="{ on }">
            <v-card outlined v-on="on">
                <v-responsive :aspect-ratio="3/5" width="225">
                    <v-card-title class="d-flex justify-space-between">
                        <span class="subtitle-1">{{ card.name }}</span>
                        <span class="subtitle-1">{{ card.cost }}</span>
                    </v-card-title>
                    <v-img :aspect-ratio="4/5" v-bind:src="'images/' + card.image"/>
                </v-responsive>
            </v-card>
        </template>
        <span>{{ card.text }}</span>
    </v-tooltip>
</template>

<script>

    export default {
        name: "card",
        props: {
            card: Object
        },
        computed: {
            hasText () { return this.card.text !== "" }
        }
    }
</script>
