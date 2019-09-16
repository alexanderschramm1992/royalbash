import Creature from "./Creature.js";

const template = `
<div class="spot">
    <creature v-if="spot.creature !== undefined" 
              v-bind:creature="spot.creature"/>
    <v-card v-else>
        <v-card-title>Empty Spot</v-card-title>
    </v-card>
</div>
`;

export default Vue.component('spot', {
    props: ['spot'],
    components: {
        Creature
    },
    template: template
})
