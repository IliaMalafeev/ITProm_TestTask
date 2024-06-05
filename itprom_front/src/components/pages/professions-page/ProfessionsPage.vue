<template>

    <div class="main_page">

        <h1>Professions Page</h1>

        <div v-if="!isProfessionsLoading" class="main_page_list">
            
            <ProfessionsList v-if="!isProfessionsLoading" :professions="professions" />
            
            <CustomButton @click="showModal">Create Profession</CustomButton>

            <CustomModal v-model:show="isModalVisible">

                <ProfessionCreateForm @create="createProfession" />

            </CustomModal>
        
        </div>

        <div v-else>Loading...</div>

    </div>

</template>

<script lang="ts">

    import { defineComponent, ref } from 'vue';
    import ProfessionsList from './components/ProfessionsList.vue'
    import ProfessionCreateForm from './components/ProfessionCreateForm.vue'
    import { useFetchProfessions } from '@/hooks/professions-fetchers/useFetchProfessions';

    export default defineComponent({

        name: 'ProfessionsPage',

        components: { ProfessionsList, ProfessionCreateForm },

        setup() {

            const { professions, isProfessionsLoading, fetchProfessions } = useFetchProfessions();
            
            const isModalVisible = ref(false);
            const isProfessionCreated = ref(true);

            return { professions, isProfessionsLoading, isProfessionCreated, fetchProfessions, isModalVisible }

        },

        methods: {

            showModal() { this.isModalVisible = true; },

            createProfession() { 

                this.isProfessionCreated = !this.isProfessionCreated;
                this.isModalVisible = false;
            
            }

        },

        watch: {

            isProfessionCreated() { this.fetchProfessions() }

        }

    });

</script>

<style scoped>

    @import "@/styles/MainPageStyles.css";

</style>