// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })




// Changed defaults for avoiding 404 during refresh while served from docker

const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({

    transpileDependencies: true,
    publicPath: ''

});