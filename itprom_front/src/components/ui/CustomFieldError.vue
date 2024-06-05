<template>

    <div class="error" v-for="error in fieldErrors">

        {{ error.replace(RegExp(`${fieldName}: `), "") }}
    
    </div>

</template>

<script lang="ts">

    import { defineComponent, ref } from 'vue';

    export default defineComponent({

        name: 'CustomInput',

        props: { httpError: String, fieldName: String },

        setup(props) {

            const regex = `(?:${props.fieldName}:\\s([\\w.?\\s-]*))`;

            const fieldErrorsIterator = props.httpError?.matchAll(RegExp(regex, "gi"));

            const fieldErrors = ref([] as String[]);

            if (fieldErrorsIterator) {

                for (const validationError of fieldErrorsIterator) fieldErrors.value.push(validationError[0]);

            }

            return { fieldErrors };

        }
        
    })

</script>

<style scoped>

    .error {
        width: 100%;
        border: 1px solid rgb(116, 0, 0);
        border-radius: 6px;
        background: rgb(255, 184, 184);
        padding: 4px;
        font-size: 1rem;
    }

</style>