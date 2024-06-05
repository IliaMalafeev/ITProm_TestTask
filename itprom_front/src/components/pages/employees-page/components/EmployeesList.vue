<template>

    <div v-if="employees.length > 0" class="item_list_block">

        <h2>Employee List</h2>

        <div class="item_list_wrapper">

            <transition-group name="tgroup-list">

                <EmployeeItem v-for="employee in employees" :employee="employee" :key="employee.id" />

            </transition-group>

        </div>

    </div>

    <h3 v-else>Employee list is empty</h3>

</template>

<script lang="ts">

    import { defineComponent, PropType } from 'vue';
    import EmployeeItem from './EmployeeItem.vue'
    import { Employee } from '@/entities/Employee';

    export default defineComponent({

        name: 'EmployeesList',

        components: { EmployeeItem },

        props: {

            employees: { type: Array as PropType<Employee[]>, required: true }

        }

    });

</script>

<style scoped>

    @import "@/styles/ItemListStyles.css";

</style>