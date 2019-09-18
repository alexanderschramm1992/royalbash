import Spot from "./Spot.js";

const template = `
<v-row justify="center">
    <v-col cols="2"
           v-for="spot of spots"
           v-bind:key="spot.id">
        <spot v-bind:spot="spot"/>
    </v-col>
</v-row>
`;

export default Vue.component('spots', {
    components: {
        Spot
    },
    props: {
        spots: Array
    },
    template: template
})
