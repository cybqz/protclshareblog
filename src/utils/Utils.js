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