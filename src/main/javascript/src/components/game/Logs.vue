<template>
    <v-container class="log">
        <h3>Game Log</h3>
        <v-list three-line dense>
            <v-listItem v-for="log of logs"
                        :key="log.id">
                <v-list-item-content>
                    <v-list-item-title style="white-space: initial;">{{ log.text }}</v-list-item-title>
                    <v-list-item-subtitle>{{ log.timestamp }}</v-list-item-subtitle>
                </v-list-item-content>
            </v-listItem>
        </v-list>
    </v-container>
</template>
<script>
    export default {
        name: 'logs',
        props: {
            logs: Array
        }
    }
</script>
