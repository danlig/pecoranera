let pwdLength = /^.{8,20}$/,
        pwdUpper = /[A-Z]+/,
        pwdLower = /[a-z]+/,
        pwdNumber = /[0-9]+/,
        pwdSpecial = /[!@#$%^&()'[\]"?+-/*={}.,;:_]+/;

    let checkLength = false,
        checkUpper = false,
        checkLower = false,
        checkNumber = false,
        checkSpecial = false;

        
//Click on eye(s) to show passwords
let showPassword = function(source) {
        
    $(source).on('click', function() {
        let passField = $(this).siblings("input").first();

        $(this).children().first().toggleClass("fa-eye");

        if(passField.prop("type") !== "password"){
            passField.prop("type", "password");
        } else {
            passField.prop("type", "text");
        }
    });
}

//Check passsword validity
let checkPassword = function(elem, isValid) {
    let checkMark = $(elem).children().first();

    if(isValid){
        $(elem).addClass("valid");
        checkMark.removeClass("fa-xmark");
        checkMark.addClass("fa-check");

        return true;
    } else {
        $(elem).removeClass("valid");
        checkMark.addClass("fa-xmark");
        checkMark.removeClass("fa-check");

        return false;
    }
}

let showPasswordSafety = function(elem){
    let pwd = $(elem).val();

    if(pwdLength.test(pwd)){
        checkLength = checkPassword($("#pwd-length"), true);
    } else {
        checkLength = checkPassword($("#pwd-length"), false);
    }

    if(pwdUpper.test(pwd)){
        checkUpper = checkPassword($("#pwd-upper"), true);
    } else {
        checkUpper = checkPassword($("#pwd-upper"), false);
    }

    if(pwdLower.test(pwd)){
        checkLower = checkPassword($("#pwd-lower"), true);
    } else {
        checkLower = checkPassword($("#pwd-lower"), false);
    }

    if(pwdNumber.test(pwd)){
        checkNumber = checkPassword($("#pwd-number"), true);
    } else {
        checkNumber = checkPassword($("#pwd-number"), false);
    }

    if(pwdSpecial.test(pwd)){
        checkSpecial = checkPassword($("#pwd-special"), true);
    } else {
        checkSpecial = checkPassword($("#pwd-special"), false);
    }
}

let checkRegex = function(){
    return checkLength && checkLower && checkUpper && checkNumber && checkSpecial;
}

export {showPassword, showPasswordSafety, checkRegex};