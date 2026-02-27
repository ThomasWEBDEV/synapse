<template>
  <div id="app">
    <nav>
      <router-link to="/">Dashboard</router-link>
    </nav>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body {
  background: #f4f6f9;
  color: #1a1a2e;
}
nav {
  background: #1a1a2e;
  padding: 14px 30px;
}
nav a {
  color: white;
  text-decoration: none;
  font-size: 14px;
  font-weight: 600;
}
nav a:hover {
  text-decoration: underline;
}
</style>
