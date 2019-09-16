const template = `
<v-card class="mx-auto">
    <v-container>
        <v-row>
            <v-col cols="12">
                <v-card-title>{{ creature.name }}</v-card-title>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col cols="11">
                <v-img aspect-ratio="0.8" v-bind:src="'images/' + creature.image"/>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="12">{{ creature.text }}</v-col>
        </v-row>
        <v-row>
            <v-col cols="3">{{ creature.attack }}</v-col>
            <v-col cols="6"/>
            <v-col cols="3">{{ creature.hitpoints }}</v-col>
        </v-row>
    </v-container>
</v-card>
`;

export default Vue.component('creature', {
    props: ['creature'],
    template: template
})
