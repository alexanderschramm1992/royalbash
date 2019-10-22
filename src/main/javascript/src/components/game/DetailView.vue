<template>
    <v-container>
        <div v-if="focus">
            {{ focus.name }}
        </div>
        <div v-else>
            Nothing to show...
        </div>
    </v-container>
</template>

<script>
    export default {
        name: "DetailView",
        computed: {
            focus () { return this.$store.state.focus }
        }
    }
</script>

<style scoped>

</style>
