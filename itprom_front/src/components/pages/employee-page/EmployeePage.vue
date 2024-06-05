<template>
    
    <div class="item_page">

        <h1>Employee with ID {{ $route.params.id }} Page</h1>

        <div v-if="!isEmployeeLoading">
            
            <div v-if="isEmployeeFound" class="item_page_card_block">

                <EmployeeCard :employee="employee" :profession="profession" :department="department" />

                <div class="item_page_card_btns">

                    <CustomButton @click="showModal">Update Info</CustomButton>

                    <CustomButton @click="deleteEmployee">Delete Employee</CustomButton>

                    <CustomModal v-model:show="isModalVisible">

                        <EmployeeUpdateForm @update="updateEmployee" :employee="employee" />

                    </CustomModal>

                </div>

            </div>
            
            <h2 v-else>Employee with this ID is not Found</h2>

        </div>

        <div v-else>Loading...</div>

    </div>

</template>

<script lang="ts">

    import { defineComponent, ref } from 'vue';
    import EmployeeCard from './components/EmployeeCard.vue'
    import EmployeeUpdateForm from './components/EmployeeUpdateForm.vue'
    import { useFetchEmployee } from '@/hooks/employees-fetchers/useFetchEmployee';
    import { useFetchDeleteEmployee } from '@/hooks/employees-fetchers/useFetchDeleteEmployee';

    export default defineComponent({

        name: 'EmployeePage',

        components: { EmployeeCard, EmployeeUpdateForm },

        setup() {

            const { employee, profession, department, isEmployeeLoading, isEmployeeFound, fetchEmployee } = useFetchEmployee();
            const { fetchDeleteEmployee } = useFetchDeleteEmployee();

            const isModalVisible = ref(false);
            const isEmployeeUpdated = ref(true);

            return { employee, profession, department, isEmployeeLoading, isEmployeeFound, fetchEmployee, fetchDeleteEmployee, isModalVisible, isEmployeeUpdated };

        },

        methods: {

            async deleteEmployee() {

                this.isEmployeeLoading = true;
                
                await this.fetchDeleteEmployee();

            },

            showModal() { this.isModalVisible = true; },

            updateEmployee() { 

                this.isEmployeeUpdated = !this.isEmployeeUpdated;
                this.isModalVisible = false;

            }

        },

        watch: {

            isEmployeeUpdated() { this.fetchEmployee() }

        }

    });

</script>

<style scoped>

    @import "@/styles/ItemPageStyles.css";

</style>