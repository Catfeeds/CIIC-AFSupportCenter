let Env = require('./env');

// 默认测试
let basePaths = {
  'AlertJob': 'http://localhost:8081',
}

// 生产
if (Env === 'production') {
  // let basePath = 'http://172.16.9.24:8080';
  let basePath = 'http://localhost:8080';
  basePaths = {
    'AlertJob': basePath + '/AlertJob-Host',
  }
}


module.exports = basePaths;
