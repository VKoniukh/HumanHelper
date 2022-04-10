const app = Vue.createApp({
    data() {
        return {
            homes: null
        }
    },
    template: '<home-list :homes="homes"/>',
    created() {
        this.fetchData()
    },
    methods: {
        async fetchData() {
            this.homes = await (await fetch('api/home')).json()
        }
    }
}).component('home-list', {
    props: ['homes'],
    template: '<div><home-row v-for="home in homes" :key="home.id" :home="home" /></div>'
}).component('home-row', {
    props: ['home'],
    template: '<div><i>({{ home.id }})</i> {{ home.location }} {{ home.description }}</div>'
})
app.mount('#app')

