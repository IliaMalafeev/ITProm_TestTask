<template>

    <form class="item_create_form" @submit.prevent>

        <h3>Create Department</h3>

        <div class="item_create_form_fields" v-if="!isCreateDepartmentLoading">

            <div class="item_create_form_field">
                <CustomFieldError :httpError="httpError" fieldName="title" />
                <CustomInput v-model="department.title" type="text" placeholder="Department Title" />
            </div>

            <div class="item_create_form_field">
                <CustomFieldError :httpError="httpError" fieldName="additionalInfo" />
                <CustomInput v-model="department.additionalInfo" type="text" placeholder="Department Description" />
            </div>

            <div class="item_create_form_field">
                <CustomSelect v-if="!isDepartmentsLoading" v-model="parentDepartmentId" :options="departments" fieldName="Department" />
                <div v-else>Loading departments...</div>
            </div>

        </div>

        <div v-else>Creating Department...</div>

        <CustomButton class="item_create_form_btn" :disabled="isCreateDepartmentLoading" @click="createDepartment">Create Department</CustomButton>

    </form>

</template>

<script lang="ts">

    import { defineComponent, ref } from 'vue';
    import { useFetchCreateDepartment } from '@/hooks/departments-fetchers/useFetchCreateDepartment';
    import { useFetchDepartments } from '@/hooks/departments-fetchers/useFetchDepartments';

    export default defineComponent({

        name: 'DepartmentCreateForm',

        setup() {

            const { departments, isDepartmentsLoading } = useFetchDepartments();
            const { department, isCreateDepartmentLoading, httpError, fetchCreateDepartment } = useFetchCreateDepartment();

            const parentDepartmentId = ref("");

            return { departments, isDepartmentsLoading, department, isCreateDepartmentLoading, httpError, fetchCreateDepartment, parentDepartmentId };

        },

        methods: {
            
            async createDepartment() {

                const parentDepartmentToAssign = this.departments.find((el) => { return el.id === Number(this.parentDepartmentId); });
                if (parentDepartmentToAssign) { this.department.parentDepartment = parentDepartmentToAssign; }

                await this.fetchCreateDepartment(this.department);

                if (!this.httpError) { this.$emit('create'); }

            },

        }
        
    })

</script>

<style scoped>

    @import "@/styles/ItemCreateFormStyles.css";

</style>