<template>
    <v-tooltip :disabled="!hasText" bottom>
        <template v-slot:activator="{ on }">
            <v-card outlined>
                <v-responsive :aspect-ratio="3/5" width="225">
                    <v-card-title class="d-flex justify-space-between">
                        <span class="subtitle-1">{{ creature.name }}</span>
                        <span class="subtitle-1">{{ creature.cost }}</span>
                    </v-card-title>
                    <v-img :aspect-ratio="4/5" v-bind:src="'images/' + creature.image"/>
                    <v-card-text>
                <span class="d-flex justify-space-between">
                    <span>{{ creature.attack }}</span>
                    <span>{{ creature.hitpoints }}</span>
                </span>
                    </v-card-text>
                </v-responsive>
            </v-card>
        </template>
        <span>{{ creature.text }}</span>
    </v-tooltip>
</template>

<script>
    export default {
        name: "creature",
        props: {
            creature: Object
        },
        computed: {
            hasText() {
                return this.creature.text !== ""
            }
        }
    };
</script>
