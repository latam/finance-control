<template>
  <v-app>
    <v-navigation-drawer app>NAV DUPA</v-navigation-drawer>
    <v-toolbar app dark color="primary" dense>
      <v-toolbar-title>Finance Control</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon>
        <v-icon>vpn_key</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-content>
    <v-footer app></v-footer>
  </v-app>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
