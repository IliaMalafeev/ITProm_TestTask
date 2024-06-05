<template>

    <input class="input" type="text" :value="modelValue" @input="updateInput">

</template>

<script lang="ts">

    import { defineComponent } from 'vue';

    export default defineComponent({

        name: 'CustomInput',

        props: { modelValue: String },

        methods: {

            updateInput(event: any) {
                this.$emit('update:modelValue', event.target.value)
            }

        }
        
    })

</script>

<style scoped>

    .input {
        width: 100%;
        border: 1px solid teal;
        border-radius: 6px;
        padding: 15px 15px;
        font-size: 1rem;
        transition: all 0.2s;
    }

    .input:focus{
        outline: 2px solid teal;
    }

</style>