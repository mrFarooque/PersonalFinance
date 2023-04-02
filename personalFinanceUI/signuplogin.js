// API calls
// 1. for Token generation with username & password
async function generateToken(userData) {
    let request = {
        username : userData.username,
        password : userData.password
    }
    let response = await fetch("http://localhost:8888/token", {
        method: "post",
        headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
        body: JSON.stringify(request)
    }).then(res => res.json())
    if (response.status == '403') {
        window.alert("Username or Password Incorrect")
        return
    }
    // store token in local storage
    localStorage.setItem("token", JSON.stringify(response))
    console.log(response)
    window.location.href = "index.html"
}

async function createUser(userData) {
    let request = {
        username : userData.username,
        password : userData.password,
        role     : "user"
    }
    let response = await fetch("http://localhost:8888/user/", {
        method: "post",
        headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
        body: JSON.stringify(request)
    }).then(res => res.json());
    console.log(response.status)
    console.log("createUser: " , response)
    if (response.status == '500') {
        window.alert("User Name already Exists")
        return
    }
    window.location.href = "login.html"
}


// all api call ends here
let signupForm = document.getElementById("signup-form")
let loginForm  = document.getElementById("login-form")
let registerBtn = document.getElementById("register")
let loginBtn = document.getElementById("login")
function register() {
    event.preventDefault();
    let username = signupForm.username.value;
    let password = signupForm.password.value;
    let confirmPassword = signupForm.confirmPassword.value;
    if (username === '') {
        window.alert("username cannot be empty.")
    }
    else if (password === '') {
        window.alert("password cannot be empty.")
    }
    else if (password.toUpperCase() === confirmPassword.toUpperCase()) {
        let userData = {
            username : username,
            password : password
        }
        signupForm.reset()
        createUser(userData)
    } else {
        window.alert("Your password and confirmation password do not match.")
    }
}
function login() { 
    event.preventDefault();
    let username = loginForm.username.value;
    let password = loginForm.password.value;

    if (username === '') {
        window.alert("username cannot be empty.")
    }
    else if (password === '') {
        window.alert("password cannot be empty.")
    }
    else {
        let userData = {
            username : username,
            password : password
        }
        loginForm.reset()
        generateToken(userData);
    }
}
if (registerBtn !== null) registerBtn.addEventListener("click",register)
if (loginBtn !== null) loginBtn.addEventListener("click",login)