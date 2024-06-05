<template>

    <div class="main_page">

        <h1>Employees Page</h1>

        <div v-if="!isEmployeesLoading" class="main_page_list">
            
            <EmployeesList v-if="!isEmployeesLoading" :employees="employees" />
            
            <CustomButton @click="showModal">Create Employee</CustomButton>

            <CustomModal v-model:show="isModalVisible">

                <EmployeeCreateForm @create="createEmployee" />

            </CustomModal>
        
        </div>

        <div v-else>Loading...</div>

    </div>

</template>

<script lang="ts">

    import { defineComponent, ref } from 'vue';
    import EmployeesList from './components/EmployeesList.vue'
    import EmployeeCreateForm from './components/EmployeeCreateForm.vue'
    import { useFetchEmployees } from '@/hooks/employees-fetchers/useFetchEmployees';

    export default defineComponent({

        name: 'EmployeesPage',

        components: { EmployeesList, EmployeeCreateForm },

        setup() {

            const { employees, isEmployeesLoading, fetchEmployees } = useFetchEmployees();
            
            const isModalVisible = ref(false);
            const isEmployeeCreated = ref(true);

            return { employees, isEmployeesLoading, isEmployeeCreated, fetchEmployees, isModalVisible }

        },

        methods: {

            showModal() { this.isModalVisible = true; },

            createEmployee() { 

                this.isEmployeeCreated = !this.isEmployeeCreated;
                this.isModalVisible = false;
            
            }

        },

        watch: {

            isEmployeeCreated() { this.fetchEmployees() }

        }

    });

</script>

<style scoped>

    @import "@/styles/MainPageStyles.css";

</style>