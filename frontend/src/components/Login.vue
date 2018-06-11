<template>
  <v-form v-model="valid">
    <v-text-field
      v-model="email"
      :rules="emailRules"
      label="E-mail"
      required
    ></v-text-field>
    <v-text-field
      v-model="password"
      :rules="passwordRules"
      label="Password"
      :append-icon="passwordHidden ? 'visibility_off' : 'visibility'"
      :append-icon-cb="() => (passwordHidden = !passwordHidden)"
      :type="passwordHidden ? 'password' : 'text'"
      required
    ></v-text-field>
    <v-btn block color="secondary" dark v-on:click="login">Login</v-btn>
  </v-form>
</template>
<script>
export default {
  data: () => ({
    passwordHidden: true,
    valid: false,
    password: '',
    passwordRules: [
      v => !!v || 'Password is required',
      v => v.length >= 6 || 'Password must be more than 6 characters',
      v => v.length <= 12 || 'Password must be less than 12 characters'
    ],
    email: '',
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
    ]
  }),
  methods: {
    login () {
      if (this.valid) {
        console.log(this.email)
        console.log(this.password)
        this.$http.post('/api/auth/login', { username: this.email, password: this.password })
      }
    },
    loginSuccessful (request) {
      if (!request.data.token) {
        this.loginFailed()
        return
      }

      localStorage.token = request.data.token
      this.error = false
      console.log(request.data.token)
      // this.$router.replace(this.$route.query.redirect || '/authors')
    },
    loginFailed () {
      this.error = 'Login failed!'
      delete localStorage.token
    }
  }
}
</script>
