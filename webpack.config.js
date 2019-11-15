//nodejs里面的基本包，用来处理路径
const path = require("path");
const webpack = require('webpack');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const HTMLPlugin = require('html-webpack-plugin');
const isDev = process.env.NODE_ENV === 'development';

//__dirname表示文件相对于工程的路径
module.exports ={
  entry: path.join(__dirname, 'src/index.js'),
  output: {
    filename: 'bundle.js',
    path: path.join(__dirname, 'dist')
  },
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      'vue$': 'vue/dist/vue.esm.js',
      '@': resolve('src'),
    }
  },
  devServer: {
    historyApiFallback: true,
    noInfo: true
  },
  devtool: '#eval-source-map',
  plugins: [
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: isDev ? '"development"' : '"production"'
      }
    }),
    // make sure to include the plugin for the magic
    new VueLoaderPlugin(),
    new HTMLPlugin()
  ],
  module: {
    rules: [
       {//通过vue-loader来识别以vue结尾的文件
         test: /.vue$/, 
         loader: 'vue-loader',
       },
       {//通过vue-loader来识别以vue结尾的文件
        test: /.css$/, 
        //css的处理方式不同，有嵌入在页面style标签里的，有从外部文件引入的，我们这里用use来声明
        use: [
          'style-loader',//接受潜在页面内部的style标签的文件。
          'css-loader'
        ],
      },
      {
        test: /\.styl(us)?$/, 
        use: [
          'style-loader',
          'css-loader',
          'stylus-loader'
        ],
      },
      {//处理图片文件
        test: /\.(gif|jpg|jpeg|png|svg)$/ ,
        use: [
          {
            loader: 'url-loader',
            options: {
              limit: 1024,
              name: '[name]-aaa.[ext]'
            }
          },
        ]
      },
      {
        test: /\.(eot|svg|ttf|woff|woff2)(\?\S*)?$/,
        use: [
          {
            loader: 'file-loader'
          }
        ]
      }
    ]
  }
}

function resolve (dir) {
  return path.join(__dirname, dir)
}

if(isDev){
  module.exports.devtool = '#cheap-module-eval-source-map'
  module.exports.devServer = {
    port: 8000,
    host: '127.0.0.1',
    open: true,
    hot: true,
    overlay: {
      erros: true,
    },
    historyApiFallback: true,
    noInfo: true
  };
  module.exports.plugins.push(
    new webpack.HotModuleReplacementPlugin(), //热加载
    new webpack.NoEmitOnErrorsPlugin() //不显示不必要的信息
  );
}