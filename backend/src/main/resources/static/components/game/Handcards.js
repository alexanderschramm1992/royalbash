import Card from "./Card.js";

const template = `
<v-row justify="center">
    <v-col cols="2"
           v-for="handcard of handcards"
           v-bind:key="handcard.id">
        <card v-bind:card="handcard"/>
    </v-col>
</v-row>
`;

export default Vue.component('handcards', {
    components: {
        Card
    },
    props: {
        handcards: Array
    },
    template: template
})
