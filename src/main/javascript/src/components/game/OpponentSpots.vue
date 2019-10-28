<template>
    <div class="d-flex justify-space-around">
        <opponent-spot v-for="spot of spots"
              :key="spot.id"
              :spot="spot"/>
    </div>
</template>

<script>
    import OpponentSpot from "./OpponentSpot.vue";

    export default {
        name: "opponent-spots",
        components: { OpponentSpot },
        props: {
            spots: Array,
        }
    }
</script>
