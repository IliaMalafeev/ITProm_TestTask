<template>

    <div v-if="professions.length > 0" class="item_list_block">

        <h2>Profession List</h2>

        <div class="item_list_wrapper">

            <transition-group name="tgroup-list">

                <ProfessionItem v-for="profession in professions" :profession="profession" :key="profession.id" />

            </transition-group>

        </div>

    </div>

    <h3 v-else>Profession list is empty</h3>

</template>

<script lang="ts">

    import { defineComponent, PropType } from 'vue';
    import ProfessionItem from './ProfessionItem.vue'
    import { ProfessionDTO } from '@/entities/ProfessionDTO';

    export default defineComponent({

        name: 'ProfessionsList',

        components: { ProfessionItem },

        props: {

            professions: { type: Array as PropType<ProfessionDTO[]>, required: true }

        }

    });

</script>

<style scoped>

    @import "@/styles/ItemListStyles.css";

</style>