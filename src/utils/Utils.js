/**
 * 判断传入对象是否为空
 * @param {*} value 
 */
export function isEmptyStr(value){

    if(value == undefined || value == null || value == ''){
        return true
    }else{
        return false
    }
}

/**
 * 判断传入的map及其值是否为空
 * @param {*} map 
 * @param {*} list 
 */
export function isEmptyMap(map, list){

    if(map == undefined || map == null){
        return true
    }else{
        if(list == undefined || list == null || list.length == 0){
            for(let i in map){
                if(isEmptyStr(map[i])){
                    return true
                }
            }
        }else{
            for(let i in map){
                for(let j in list){
                    if(list[j] === i){
                        if(isEmptyStr(map[i])){
                            return true
                        }
                    }
                }
            }
        }
        return false
    }
}

/**
 * 格式化时间
 * @param {*} time 
 */
export function dateFormat(time){
    var date=new Date(time);
    var year=date.getFullYear();
    /* 在日期格式中，月份是从0开始的，因此要加0
        * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
        * */
    var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
    var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
    var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
    var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
    var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
    // 拼接
    return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
}