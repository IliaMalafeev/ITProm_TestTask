<template>

    <div v-if="departments.length > 0" class="item_list_block">

        <h2>Department List</h2>

        <div class="item_list_wrapper">

            <transition-group name="tgroup-list">

                <DepartmentItem v-for="department in departments" :department="department" :key="department.id" />

            </transition-group>

        </div>

    </div>

    <h3 v-else>Department list is empty</h3>

</template>

<script lang="ts">

    import { defineComponent, PropType } from 'vue';
    import DepartmentItem from './DepartmentItem.vue'
    import { DepartmentDTO } from '@/entities/DepartmentDTO';

    export default defineComponent({

        name: 'DepartmentsList',

        components: { DepartmentItem },

        props: {

            departments: { type: Array as PropType<DepartmentDTO[]>, required: true }

        }

    });

</script>

<style scoped>

    @import "@/styles/ItemListStyles.css";

</style>
