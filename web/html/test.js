
function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i =0 ;i < vars.length; i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}
var errorCode = getQueryVariable("errorCode");
if (errorCode == 3) {
    window.alert("邮箱验证码不一样");
} else if (errorCode == 2) {
    window.alert("密码不一致");
} else if (errorCode == 1) {
    window.alert("用户名重复");
}

var input = document.getElementsByTagName("input");
for (let i = 0; i < input.length; i++) {
    input[i].onmouseover = function () {
        input[i].style.background = "linear-gradient(white, rgb(182, 237, 232))";
    }
    input[i].onmouseout = function () {
        input[i].style.background = "transparent";
    }
}