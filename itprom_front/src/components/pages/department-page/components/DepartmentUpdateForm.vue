<template>

    <form class="item_update_form" @submit.prevent>

        <h3>Update Department</h3>

        <div class="item_update_form_fields" v-if="!isUpdateDepartmentLoading">

            <div class="item_update_form_field">
                <CustomFieldError :httpError="httpError" fieldName="title" />
                <CustomInput v-model="department.title" type="text" placeholder="Full Name" />
            </div>

            <div class="item_update_form_field">
                <CustomFieldError :httpError="httpError" fieldName="additionalInfo" />
                <CustomInput v-model="department.additionalInfo" type="text" placeholder="Additional Info" />
            </div>

        </div>

        <div v-else>Updating Department...</div>

        <CustomButton class="item_update_form_btn" :disabled="isUpdateDepartmentLoading" @click="updateDepartment">Update Department</CustomButton>    

    </form>

</template>

<script lang="ts">

    import { PropType, defineComponent } from 'vue';
    import { useFetchUpdateDepartment } from '@/hooks/departments-fetchers/useFetchUpdateDepartment';
    import { DepartmentDTO } from '@/entities/DepartmentDTO';

    export default defineComponent({

        name: 'DepartmentUpdateForm',

        props: {

            department: { type: Object as PropType<DepartmentDTO>, required: true },

        },

        setup() {

            const { isUpdateDepartmentLoading, httpError, fetchUpdateDepartment } = useFetchUpdateDepartment();

            return { isUpdateDepartmentLoading, httpError, fetchUpdateDepartment };

        },

        methods: {
            
            async updateDepartment() {

                await this.fetchUpdateDepartment(this.department);

                if (!this.httpError) { this.$emit('update'); }

            },

        }
        
    })

</script>

<style scoped>

    @import "@/styles/ItemUpdateFormStyles.css";

</style>