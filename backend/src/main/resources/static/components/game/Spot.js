import Creature from "./Creature.js";

const template = `
<div class="spot">
    <creature v-if="spot.creature" 
              v-bind:creature="spot.creature"/>
    <v-card v-else>
        <v-card-title>Empty Spot</v-card-title>
        <v-btn>Place Creature</v-btn>
    </v-card>
</div>
`;

export default Vue.component('spot', {
    components: {
        Creature
    },
    props: {
        spot: Object
    },
    template: template
})
