let local = {
  edits: false, //新建or编辑
  typeName: [],
  save(key, value) {
    localStorage.setItem(key, value);
    // 
  },
  fetch(key) {
    return localStorage.getItem(key) || {};
  },
  back(vm, step) {
    window.history.go(-1);
    //  window.history.back();
  },
  fuzzyQuery(query, source, type) {
    if (query !== '') {
        // this.loading1 = true;
    //   setTimeout(() => {
        // this.loading1 = false;
      const list = source.map(item => {
        return {
          value: item[type],
          label: item[type]
        };
      });
      return  list.filter(item => item.label.indexOf(query) > -1);
    //   }, 300);
    } else {
      return  [];
    }
  },
  titleCase(str) {
    //将字符串分解为数组并将其小写化 
    var convertToArray = str.toLowerCase().split(" ");
    for (var i = 0; i < convertToArray.length; i++) {
      var char = convertToArray[i].charAt(0);
      //使用 replace()方法将数组中的每个首字母大写化 
      convertToArray[i] = convertToArray[i].replace(char, function replace(char) {
        return char.toUpperCase();
      });
    }
    return convertToArray.join(" ");
  },
  addClasses(obj, cls){
      if (!this.hasClass(obj, cls)) obj.className += " " + cls;  
  }
}
export default {
  install: function (vm) {
    vm.prototype.$local = local;
  }
}
// let types = {
//     isString:(obj) => {
//         return (typeof obj=='string')&&obj.constructor==String; 
//     },
//     isNumber:(obj) => {
//         return (typeof obj=='number')&&obj.constructor==Number; 
//     },
//     isDate:(obj) => {
//         return (typeof obj=='object')&&obj.constructor==Date;
//     },
//     isFunction:(obj) => {
//         return (typeof obj=='function')&&obj.constructor==Function; 
//     },
//     isObject:(obj) => {
//         return (typeof obj=='object')&&obj.constructor==Object;  
//     },
//     isArray:(obj) => {
//         return (typeof obj=='object')&&obj.constructor==Array; 
//     }
// }
