<template>

    <form class="item_create_form" @submit.prevent>

        <h3>Create Profession</h3>

        <div class="item_create_form_fields" v-if="!isCreateProfessionLoading">

            <div class="item_create_form_field">
                <CustomFieldError :httpError="httpError" fieldName="title" />
                <CustomInput v-model="profession.title" type="text" placeholder="Profession Title" />
            </div>

            <div class="item_create_form_field">
                <CustomFieldError :httpError="httpError" fieldName="description" />
                <CustomInput v-model="profession.description" type="text" placeholder="Profession Description" />
            </div>

        </div>

        <div v-else>Creating Profession...</div>

        <CustomButton class="item_create_form_btn" :disabled="isCreateProfessionLoading" @click="createProfession">Create Profession</CustomButton>    

    </form>

</template>

<script lang="ts">

    import { defineComponent } from 'vue';
    import { useFetchCreateProfession } from '@/hooks/professions-fetchers/useFetchCreateProfession';

    export default defineComponent({

        name: 'ProfessionCreateForm',

        setup() {

            const { profession, isCreateProfessionLoading, httpError, fetchCreateProfession } = useFetchCreateProfession();

            return { profession, isCreateProfessionLoading, httpError, fetchCreateProfession };

        },

        methods: {
            
            async createProfession() {

                await this.fetchCreateProfession(this.profession);

                if (!this.httpError) { this.$emit('create'); }

            },

        }
        
    })

</script>

<style scoped>

    @import "@/styles/ItemCreateFormStyles.css";

</style>