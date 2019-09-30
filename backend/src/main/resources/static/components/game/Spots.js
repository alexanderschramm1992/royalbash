import Spot from "./Spot.js";

const template = `
<v-row justify="center">
    <v-col cols="2"
           v-for="spot of spots"
           v-bind:key="spot.id">
        <spot v-bind:gameId="gameId"
              v-bind:ownerId="ownerId"
              v-bind:spot="spot"
              v-bind:handcards="handcards"/>
    </v-col>
</v-row>
`;

export default Vue.component('spots', {
    components: {
        Spot
    },
    props: {
        gameId: String,
        ownerId: String,
        spots: Array,
        handcards: Array
    },
    template: template
})
