import Vue from 'vue';
let validFocus = Vue.directive("focus",{

    inserted:function(el){
        let inputTarget=el.getElementsByTagName("input");
        for(var i=0;i<inputTarget.length;i++){
            // inputTarget[i].focus()
            // console.log(inputTarget[i])
            // inputTarget[i].onclick=function(){

            //     console.log("focus");
            // }
        }
    }
});

let highlightPlaceholer = Vue.directive('placeholder-highlight',{
    bind:function(el){
        let target=el.getElementsByTagName("input")[0];
        console.log(target);
        // target.className="highlightPlaceholder";
    }
})

export default {validFocus}