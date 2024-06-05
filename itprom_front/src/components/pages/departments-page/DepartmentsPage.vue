<template>

    <div class="main_page">

        <h1>Departments Page</h1>

        <div v-if="!isDepartmentsLoading" class="main_page_list">
            
            <DepartmentsList v-if="!isDepartmentsLoading" :departments="departments" />
            
            <CustomButton @click="showModal">Create Department</CustomButton>

            <CustomModal v-model:show="isModalVisible">

                <DepartmentCreateForm @create="createDepartment" />

            </CustomModal>
        
        </div>

        <div v-else>Loading...</div>

    </div>

</template>

<script lang="ts">

    import { defineComponent, ref } from 'vue';
    import DepartmentsList from './components/DepartmentsList.vue'
    import DepartmentCreateForm from './components/DepartmentCreateForm.vue'
    import { useFetchDepartments } from '@/hooks/departments-fetchers/useFetchDepartments';

    export default defineComponent({

        name: 'DepartmentsPage',

        components: { DepartmentsList, DepartmentCreateForm },

        setup() {

            const { departments, isDepartmentsLoading, fetchDepartments } = useFetchDepartments();
            
            const isModalVisible = ref(false);
            const isDepartmentCreated = ref(true);

            return { departments, isDepartmentsLoading, isDepartmentCreated, fetchDepartments, isModalVisible }

        },

        methods: {

            showModal() { this.isModalVisible = true; },

            createDepartment() { 

                this.isDepartmentCreated = !this.isDepartmentCreated;
                this.isModalVisible = false;
            
            }

        },

        watch: {

            isDepartmentCreated() { this.fetchDepartments() }

        }

    });

</script>

<style scoped>

    @import "@/styles/MainPageStyles.css";

</style>