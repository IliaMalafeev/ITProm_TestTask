<template>
    
    <div class="item_page">

        <h1>Profession with ID {{ $route.params.id }} Page</h1>

        <div v-if="!isProfessionLoading">
            
            <div v-if="isProfessionFound" class="item_page_card_block">

                <ProfessionCard :profession="profession" />

                <div class="item_page_card_btns">

                    <CustomButton @click="showModal">Update Info</CustomButton>

                    <CustomButton @click="deleteProfession">Delete Profession</CustomButton>

                    <CustomModal v-model:show="isModalVisible">

                        <ProfessionUpdateForm @update="updateProfession" :profession="profession" />

                    </CustomModal>

                </div>

            </div>
            
            <h2 v-else>Profession with this ID is not Found</h2>

        </div>

        <div v-else>Loading...</div>

    </div>

</template>

<script lang="ts">

    import { defineComponent, ref } from 'vue';
    import ProfessionCard from './components/ProfessionCard.vue'
    import ProfessionUpdateForm from './components/ProfessionUpdateForm.vue'
    import { useFetchProfession } from '@/hooks/professions-fetchers/useFetchProfession';
    import { useFetchDeleteProfession } from '@/hooks/professions-fetchers/useFetchDeleteProfession';

    export default defineComponent({

        name: 'ProfessionPage',

        components: { ProfessionCard, ProfessionUpdateForm },

        setup() {

            const { profession, isProfessionLoading, isProfessionFound, fetchProfession } = useFetchProfession();
            const { fetchDeleteProfession } = useFetchDeleteProfession();

            const isModalVisible = ref(false);
            const isProfessionUpdated = ref(true);

            return { profession, isProfessionLoading, isProfessionFound, fetchProfession, fetchDeleteProfession, isModalVisible, isProfessionUpdated };

        },

        methods: {

            async deleteProfession() {

                this.isProfessionLoading = true;
                
                await this.fetchDeleteProfession();

            },

            showModal() { this.isModalVisible = true; },

            updateProfession() { 

                this.isProfessionUpdated = !this.isProfessionUpdated;
                this.isModalVisible = false;

            }

        },

        watch: {

            isProfessionUpdated() { this.fetchProfession() }

        }

    });

</script>

<style scoped>

    @import "@/styles/ItemPageStyles.css";

</style>