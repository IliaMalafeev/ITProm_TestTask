<template>

    <div class="nav__wrapper">

    <nav class="navbar">
        <router-link to="/">Employees</router-link> |
        <router-link to="/departments">Departments</router-link> |
        <router-link to="/professions">Professions</router-link> 
    </nav>

    <div class="line"></div>

    </div>

</template>

<script lang="ts">

    import { defineComponent } from 'vue';

    export default defineComponent({

        name: 'Navbar'

    });

</script>

<style scoped>

    .nav__wrapper {
        display: flex;
        flex-direction: column;
        font-size: 1.125rem;
    }

    nav {
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 20px;
        padding: 30px;
    }

    nav a {
        font-weight: bold;
        color: #2c3e50;
    }

    nav a.router-link-exact-active {
        color: #42b983;
    }

    .line {
        width: 90%;
        height: 1px;
        background: teal;
        align-self: center;
    }

</style>