<template>

    <form class="item_update_form" @submit.prevent>

        <h3>Update Profession</h3>

        <div class="item_update_form_fields" v-if="!isUpdateProfessionLoading">

            <div class="item_update_form_field">
                <CustomFieldError :httpError="httpError" fieldName="title" />
                <CustomInput v-model="profession.title" type="text" placeholder="Full Name" />
            </div>

            <div class="item_update_form_field">
                <CustomFieldError :httpError="httpError" fieldName="description" />
                <CustomInput v-model="profession.description" type="text" placeholder="Additional Info" />
            </div>

        </div>

        <div v-else>Updating Profession...</div>

        <CustomButton class="item_update_form_btn" :disabled="isUpdateProfessionLoading" @click="updateProfession">Update Profession</CustomButton>    

    </form>

</template>

<script lang="ts">

    import { PropType, defineComponent, ref } from 'vue';
    import { useFetchUpdateProfession } from '@/hooks/professions-fetchers/useFetchUpdateProfession';
    import { ProfessionDTO } from '@/entities/ProfessionDTO';

    export default defineComponent({

        name: 'ProfessionUpdateForm',

        props: {

            profession: { type: Object as PropType<ProfessionDTO>, required: true },

        },

        setup() {

            const { isUpdateProfessionLoading, httpError, fetchUpdateProfession } = useFetchUpdateProfession();

            return { isUpdateProfessionLoading, httpError, fetchUpdateProfession };

        },

        methods: {
            
            async updateProfession() {

                await this.fetchUpdateProfession(this.profession);

                if (!this.httpError) { this.$emit('update'); }

            },

        }
        
    })

</script>

<style scoped>

    @import "@/styles/ItemUpdateFormStyles.css";

</style>