<template>
  <div id="app">
    <Table />
  </div>
</template>

<script>
import Table from './components/Table'

export default {
  name: 'app',
  components: {
    Table
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
  max-width: 1200px;
  margin: 0 auto;
}
</style>
