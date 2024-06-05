<template>
    
    <div class="item_page">

        <h1>Department with ID {{ $route.params.id }} Page</h1>

        <div v-if="!isDepartmentLoading">
            
            <div v-if="isDepartmentFound" class="item_page_card_block">

                <DepartmentCard :department="department" />

                <div class="item_page_card_btns">

                    <CustomButton @click="showModal">Update Info</CustomButton>

                    <CustomButton @click="deleteDepartment">Delete Department</CustomButton>

                    <CustomModal v-model:show="isModalVisible">

                        <DepartmentUpdateForm @update="updateDepartment" :department="department" />

                    </CustomModal>

                </div>

            </div>
            
            <h2 v-else>Department with this ID is not Found</h2>

        </div>

        <div v-else>Loading...</div>

    </div>

</template>

<script lang="ts">

    import { defineComponent, ref } from 'vue';
    import DepartmentCard from './components/DepartmentCard.vue'
    import DepartmentUpdateForm from './components/DepartmentUpdateForm.vue'
    import { useFetchDepartment } from '@/hooks/departments-fetchers/useFetchDepartment';
    import { useFetchDeleteDepartment } from '@/hooks/departments-fetchers/useFetchDeleteDepartment';

    export default defineComponent({

        name: 'DepartmentPage',

        components: { DepartmentCard, DepartmentUpdateForm },

        setup() {

            const { department, isDepartmentLoading, isDepartmentFound, fetchDepartment } = useFetchDepartment();
            const { fetchDeleteDepartment } = useFetchDeleteDepartment();

            const isModalVisible = ref(false);
            const isDepartmentUpdated = ref(true);

            return { department, isDepartmentLoading, isDepartmentFound, fetchDepartment, fetchDeleteDepartment, isModalVisible, isDepartmentUpdated };

        },

        methods: {

            async deleteDepartment() {

                this.isDepartmentLoading = true;
                
                await this.fetchDeleteDepartment();

            },

            showModal() { this.isModalVisible = true; },

            updateDepartment() { 

                this.isDepartmentUpdated = !this.isDepartmentUpdated;
                this.isModalVisible = false;

            }

        },

        watch: {

            isDepartmentUpdated() { this.fetchDepartment() }

        }

    });

</script>

<style scoped>

    @import "@/styles/ItemPageStyles.css";

</style>