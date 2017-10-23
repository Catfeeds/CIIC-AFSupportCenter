import axios from "axios";
import moment from "moment";
import qs from "qs";
import config from "./config";

let utils = {
  // 默认分页大小 5
  DEFAULT_PAGE_SIZE: 5,
  DEBUG: config.env === 'development'
};

/**
 * 空函数
 */
utils.noop = function () {
}


/**
 * copy 属性
 * @param source
 * @param target
 */
utils.copy = function (source, target) {
  // copy attr
  for (var attr in source) {
    if (target[attr] != undefined) {
      target[attr] = source[attr];
    }
  }
}


/**
 * 格式化日期
 * @param date 日期
 * @param fmt 日期格式
 * @returns string
 */
utils.formatDate = function (date, fmt) {
  return moment(date).format(fmt);
};

/**
 * 解析日期
 * @param dateString 日期字符串
 * @param fmt 日期格式
 * @returns Date
 */
utils.parseDate = function (dateString, fmt) {
  // utc 解决时区问题
  return moment.utc(dateString, fmt);
};

/**
 * 创建 ajax 对象
 * @param config
 */
utils.createAjax = (config) => {
  let ajax = axios.create(config);

  // 添加一个请求拦截器
  ajax.interceptors.request.use((config) => {
      // Do something before request is sent
      var contentType = config.headers['Content-Type'];

      if (!Boolean(contentType)) {
        // 不区分大小写查找
        for (var header in config.headers) {
          if ('application/x-www-form-urlencoded' === header.toLowerCase()) {
            contentType = header;
            break;
          }
        }
      }

      if (contentType === 'application/x-www-form-urlencoded') {
        config.data = qs.stringify(config.data);
      }


      // outer log
      if (utils.DEBUG) {
        let logInfo = {
          url: config.url,
          method: config.method,
          time: utils.formatDate(new Date(), 'YYYY-MM-DD HH:mm:ss'),
          data: null,
        };
        if (contentType === 'application/x-www-form-urlencoded') {
          logInfo.data = decodeURIComponent(config.data);
        } else {
          logInfo.data = JSON.stringify(config.data);
        }

        console.log(JSON.stringify(logInfo));
      }

      return config;
    },
    error => {
      // Do something with request error
      return Promise.reject(error);
    })
  return ajax;
}

function _createAjax(name) {
  return utils.createAjax({
    baseURL: config.basePaths[name],
    timeout: utils.DEBUG ? 0 : 5000,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}

utils.ajaxAlertJob = _createAjax('AlertJob');


export default utils;
