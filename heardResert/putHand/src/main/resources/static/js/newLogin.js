(function() {
    if (!String.prototype.trim) {
        (function() {
            // Make sure we trim BOM and NBSP
            var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
            String.prototype.trim = function() {
                return this.replace(rtrim, '');
            };
        })();
    }

    [].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
        // in case the input is already filled..
        if( inputEl.value.trim() !== '' ) {
            classie.add( inputEl.parentNode, 'input--filled' );
        }

        // events:
        inputEl.addEventListener( 'focus', onInputFocus );
        inputEl.addEventListener( 'blur', onInputBlur );
    } );

    function onInputFocus( ev ) {
        classie.add( ev.target.parentNode, 'input--filled' );
    }

    function onInputBlur( ev ) {
        if( ev.target.value.trim() === '' ) {
            classie.remove( ev.target.parentNode, 'input--filled' );
        }
    }
})();

$(function() {
    $('#login #login-password').focus(function() {
        $('.login-owl').addClass('password');
    }).blur(function() {
        $('.login-owl').removeClass('password');
    });
    $('#login #register-password').focus(function() {
        $('.register-owl').addClass('password');
    }).blur(function() {
        $('.register-owl').removeClass('password');
    });
    $('#login #register-repassword').focus(function() {
        $('.register-owl').addClass('password');
    }).blur(function() {
        $('.register-owl').removeClass('password');
    });
    $('#login #forget-password').focus(function() {
        $('.forget-owl').addClass('password');
    }).blur(function() {
        $('.forget-owl').removeClass('password');
    });
});

function goto_register(){
    $("#register-username").val("");
    $("#register-password").val("");
    $("#register-repassword").val("");
    $("#register-code").val("");
    $("#tab-2").prop("checked",true);
}

function goto_login(){
    $("#login-username").val("");
    $("#login-password").val("");
    $("#tab-1").prop("checked",true);
}

function goto_forget(){
    $("#forget-username").val("");
    $("#forget-password").val("");
    $("#forget-code").val("");
    $("#tab-3").prop("checked",true);
}
//登录
function login(){
    var username = $("#login-username").val(),
        password = $("#login-password").val(),
        validatecode = null,
        flagName = false,
        flagPwd=false,
        allflag=false;
    //判断用户名密码是否为空
    if(username == ""){
        $.pt({
            target: $("#login-username"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"用户名不能为空"
        });
        flagName = false;
    }else{
        flagName=true;
    }
    if(password == ""){
        $.pt({
            target: $("#login-password"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码不能为空"
        });
        flagPwd = false;
    }else{
        flagPwd=true;
    }
    //查看用户名和密码是否存在
    if(flagName&&flagPwd){
        $.ajax({
            url:"/deal/userData",
            type:"Post",
            data:{'userName':username,'password':password},
            dataType:'text',
            async:false,
            success:function (result) {
                if('false'===result){
                    alert('用户名密码错误');
                    allflag=false;
                }else{
                    allflag=true;
                }
            }
        });
    }
    if(allflag){
        return true;
    }else{//登录
        return false;
    }
}

//注册
function register(){
    var username = $("#register-username").val(),
        password = $("#register-password").val(),
        repassword = $("#register-repassword").val(),
        flagName = false,
        flagPwd=false,
        validatecode = null;
    //判断用户名密码是否为空
    if(username == ""){
        $.pt({
            target: $("#register-username"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"用户名不能为空"
        });
        flagName = false;
    }else{
        $.ajax({
            url:"/jumpUserName",
            type:"Post",
            data:{'userName':username},
            dataType:'text',
            async:false,
            success:function (result) {
                if('true'===result){
                    $.pt({
                        target: $("#register-username"),
                        position: 'r',
                        align: 't',
                        width: 'auto',
                        height: 'auto',
                        content:"用户名已被注册"
                    });
                    flagName=false;
                }else{
                    flagName=true;
                }
            }
        });
    }
    if(password == ""){
        $.pt({
            target: $("#register-password"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码不能为空"
        });
        flagPwd = false;
    }else{
        if(password != repassword){
            $.pt({
                target: $("#register-repassword"),
                position: 'r',
                align: 't',
                width: 'auto',
                height: 'auto',
                content:"两次输入的密码不一致"
            });
            flagPwd = false;
        }else{
            flagPwd=true;
        }
    }
    if(flagName&&flagPwd){
        return true;
    }else{
        return false;
    }
}
//重置密码
function forget(){
    var username = $("#forget-username").val(),
        password = $("#forget-password").val(),//2
        code = $("#forget-code").val(),//1
        validatecode = null,
        flagName = false,
        flagSecond=false,
        allflag=false,
        flag = false;
    //判断用户名密码是否为空
    if(username == ""){
        $.pt({
            target: $("#forget-username"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"用户名不能为空"
        });
        flagName = false;
    }else{
        $.ajax({
            url:"/jumpUserName",
            type:"Post",
            data:{'userName':username},
            dataType:'text',
            async:false,
            success:function (result) {
                if('false'===result){
                    $.pt({
                        target: $("#forget-username"),
                        position: 'r',
                        align: 't',
                        width: 'auto',
                        height: 'auto',
                        content:"用户名不存在"
                    });
                    flagName=false;
                }else{
                    flagName=true;
                }
            }
        });
    }

    if(password == ""){
        $.pt({
            target: $("#forget-password"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码不能为空"
        });
        flag=false;
    }else{
        flag=true;
    }
    if(code == ""){
        $.pt({
            target: $("#forget-code"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码不能为空"
        });
        flagSecond=false;
    }else{
        //两次密码是否一致
        if(password===code){
            flagSecond=true;
        }else{
            $.pt({
                target: $("#forget-password"),
                position: 'r',
                align: 't',
                width: 'auto',
                height: 'auto',
                content:"两次密码不一致"
            });
            flagSecond = false;
        }
    }
    if(flag&&flagName&&flagSecond){
        allflag=true;
    }else{
        allflag=false;
    }
    return allflag;
}