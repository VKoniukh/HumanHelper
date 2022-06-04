function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

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
    data() {
        return {
            home: null
        }
    },
    template: '<div style="position: relative; width: 600px;">' +
        '<home-form  :homes="homes" :homeAttr="home" />' +
        '<home-row v-for="home in homes" :key="home.id" :home="home" :editHome="edit" :homes="homes"/>' +
        '</div>',
    methods: {
        edit(home) {
            this.home = home;
        }
    }
}).component('home-row', {
    props: ['home', 'editHome', "homes"],
    template: '<div><i>({{ home.id }})</i> City: {{ home.location }} Description: {{ home.description }} sleep place: {{home.sleepPlace}}' +
        '<span style="position: absolute; right: 0;">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit() {
            this.editHome(this.home);
        },
        del() {
            var requestDeleteOptions = {
                method: "DELETE",
                headers: {"Content-Type": "application/json"},
            };
            fetch("api/home/" + this.home.id, requestDeleteOptions)
                .then(response => {
                        if (response.ok) {
                            this.homes.splice(this.homes.indexOf(this.home), 1);
                        }
                    }
                );
        }
    }
}).component('home-form', {
    props: {
        homes: null,
        homeAttr: null
    },
    data() {
        return {
            location: '',
            sleepPlace: '',
            description: '',
            id: null
        }
    },
    watch: {
        homeAttr(newVal, oldVal) {
            this.location = newVal.location;
            this.sleepPlace = newVal.sleepPlace;
            this.description = newVal.description;
            this.id = newVal.id;
        }
    },
    template: '<div> ' +
        '<input type="text" placeholder="City" v-model="location" />' +
        '<select v-model="sleepPlace">' +
        '<option disabled value="">Please select one</option>' +
        '<option>1</option>' +
        '<option>2</option>' +
        '<option>3</option>' +
        '<option>4</option>' +
        '<option>5</option>' +
        '<option>6</option>' +
        '<option>7</option>' +
        '<option>8</option>' +
        '<option>9</option>' +
        '<option>More</option>' +
        '</select>' +
        '<input type="text" placeholder="Description" v-model="description" />' +
        '<input type="button" value="Save" @click="save" />'
        + '</div>',
    methods: {
        save() {
            var message = {location: this.location, description: this.description, sleepPlace: this.sleepPlace};
            if (this.id) {
                var requestPutOptions = {
                    method: "PUT",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify(message)
                };
                fetch("api/home/" + this.id, requestPutOptions)
                    .then(response => response.json()).then(saved => {
                    var index = getIndex(saved, id);
                    this.homes.splice(index, 1, saved);
                });
                this.id = null;
            } else {
                var requestPostOptions = {
                    method: "POST",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify(message)
                };
                fetch("api/home", requestPostOptions)
                    .then(response => response.json()).then(saved => this.homes.push(saved));
            }
            this.location = ''
            this.sleepPlace = ''
            this.description = ''
        }
    }
})
app.mount('#app')

