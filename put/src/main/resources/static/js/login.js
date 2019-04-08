//login
function login() {
    var userName=$('#userName').val();
    var password=$('#password').val();
    var flag=false;
    if(userName==null||userName==undefined||userName==""||
    password==null||password==undefined||password==""){
        alert("请输入姓名和密码");
    }else{
        $.ajax({
            url:"/deal/userData",
            type:"Post",
            data:{'userName':userName,'password':password},
            dataType:'text',
            async:false,
            success:function (result) {
                if('false'===result){
                    alert('用户名和密码错误');
                }else{
                    flag=true;
                }
            }
        });
    }
    return flag;
}