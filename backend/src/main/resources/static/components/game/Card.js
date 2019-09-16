const template = `
<v-card class="mx-auto">
    <v-container>
        <v-row>
            <v-col cols="9">
                <v-card-title>{{ card.name }}</v-card-title>
            </v-col>
            <v-col cols="3">
                <v-card-title>{{ card.cost }}</v-card-title>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col cols="11">
                <v-img aspect-ratio="0.8" v-bind:src="'images/' + card.image"/>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="12" class="text-center">
                <v-card-text>{{ card.text }}</v-card-text>
            </v-col>
        </v-row>
    </v-container>
</v-card>
`;


export default Vue.component('card', {
    props: ['card'],
    template: template
})
