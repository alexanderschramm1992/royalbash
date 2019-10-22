<template>
    <div class="d-flex justify-space-around">
        <spot v-for="spot of spots"
              :key="spot.id"
              :gameId="gameId"
              :ownerId="ownerId"
              :spot="spot"
              :handcards="handcards"/>
    </div>
</template>

<script>
    import Spot from "./Spot.vue";

    export default {
        name: "spots",
        components: { Spot },
        props: {
            gameId: String,
            ownerId: String,
            spots: Array,
            handcards: Array
        }
    }
</script>
