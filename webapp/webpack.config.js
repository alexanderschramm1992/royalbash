const HtmlWebPackPlugin = require("html-webpack-plugin");
module.exports = {
    output: {
        path: __dirname + "/public",
    },
    devServer: {
        port: 3000,
        proxy: {
            '/game': 'http://localhost:8080'
        }
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader"
                }
            },
            {
                test: /\.html$/,
                use: [
                    {
                        loader: "html-loader"
                    }
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebPackPlugin({
            template: "./src/index.html",
            filename: "./index.html"
        })
    ]
};