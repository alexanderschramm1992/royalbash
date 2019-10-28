<template>
    <div class="d-flex justify-space-around">
        <card v-for="handcard of handcards"
              :key="handcard.instanceId"
              :card="handcard"
              :hidden="hidden"/>
    </div>
</template>

<script>
    import Card from "./Card.vue";

    export default {
        name: "handcards",
        components: { Card },
        props: {
            handcards: Array,
            hidden: Boolean
        }
    }
</script>
