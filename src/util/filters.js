const filters = {
    /**
     * 功能：格式化货币（amount）
     * @param {Number} value 值
     * @param {Number} decimals 保留小数位数 (默认：2)
     * @param {String} symbol 附加符号 (默认：无)
     * @returns {String} 规范后 字符串+值
     */
    amount: function (value, decimals, symbol) {
        var s = symbol
        var d = decimals
        var pattern = /(?=((?!\b)\d{3})+(\.\d*)?$)/g
        if (d == null || d == "") {
            d = 2
        }
        var num = Number(value).toFixed(d+1)
        var n = num.substring(0, num.lastIndexOf('.')+(d+1))
        if(s == null || s == ""){
            s = ''
        }
        return value = s + n.replace(pattern,',')
    }
}
export default (Vue) => {
    Object.keys(filters).forEach(key => {
        Vue.filter(key, filters[key])
    })
}
