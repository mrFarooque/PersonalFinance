// API CALLS start
// GET: list all expense of current month
async function listExpenseCurrentMonth() {
    let response = await fetch("http://localhost:8888/expense/list/current").then(res => res.json())
    updateTable(response)
}
listExpenseCurrentMonth();

// POST: expense or cash
 async function addMoney(value) {
    let request = getFormData();
    console.log(request)
    console.log(value)
    if(value == 0) {
        let response = await fetch("http://localhost:8888/expense/", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(request)
            }
        )
        listExpenseCurrentMonth()
    } else {
        let response = await fetch("http://localhost:8888/income/", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(request)
            }
        )
        listExpenseCurrentMonth()
    }
 }
 // call all GET Api to fill data
 async function fillData() {
    let categories = ["FOOD", "UTILITIES", "RENT", "DEBT", "ENTERTAINMENT", "GIFTS"]
    categories.forEach(category => {
        async function getData() {
            let response = await fetch("http://localhost:8888/expense/total/category/"+category+"/month/current").then(res => res.json())
            console.log("category:"+ category +" res-> " + response)
            if(category == "FOOD") document.getElementById("food-data").innerText = response;
            else if(category == "UTILITIES") document.getElementById("utilities-data").innerText = response;
            else if(category == "RENT") document.getElementById("rent-data").innerText = response;
            else if(category == "DEBT") document.getElementById("debt-data").innerText = response;
            else if(category == "GIFTS") document.getElementById("gifts-data").innerText = response;
            else if(category == "ENTERTAINMENT") document.getElementById("entertainment-data").innerText = response;
        }
        getData();
    })
    let balance = await fetch("http://localhost:8888/income/balance/month/current").then(res => res.json())
    document.getElementById("balance-data").innerText = balance
 }
 fillData();
// API CALLS end
// DOM to list all expense 
function updateTable(res) {
    fillData();
    let tbody = document.querySelector("tbody")
    tbody.innerHTML = ''
    res.forEach((ele,index) => {
        let tr = document.createElement("tr");
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");
        let td6 = document.createElement("td");
        // let delete = document.createElement("button");
        // let edit = document.createElement("button");
        td1.innerText = index+1;
        td2.innerText = ele.purpose;
        td3.innerText = ele.amount;
        td4.innerText = ele.category;
        td5.innerText = ele.date;
        // td6.append(delete, edit);
        tr.append(td1, td2, td3, td4, td5);
        tbody.append(tr);
    });
}

// form
let form = document.querySelector("form");
document.getElementById("btn-expense").addEventListener("click", () => {
    addMoney(0);
})
document.getElementById("btn-cash").addEventListener("click", () => {
    addMoney(1);
})
function getFormData() {
    event.preventDefault();
    let category = "ENTERTAINMENT";
    checkForCategory();
    let obj = {
        amount: form.amount.value,
        purpose: form.purpose.value,
        date: form.date.value,
        category: category
    }
    function checkForCategory() {
        if (form.food.checked) category = 'FOOD';
        else if (form.utilities.checked) category = 'UTILITIES';
        else if (form.rent.checked) category = 'RENT';
        else if (form.debt.checked) category = 'DEBT'; 
        else if (form.gifts.checked) category = 'GIFTS'; 
        else if (form.entertainment.checked) category = 'ENTERTAINMENT'; 
    }
    form.reset();
    return obj;
}

// Add-Expense/Cash button to hide or unhide form
let toggleBtn = document.getElementById("toggle-btn");
toggleBtn.addEventListener("click", () => {
    let toggleDiv = document.getElementById("toggle")
    toggleDiv.classList.toggle("hide-form")
    let dataChart = document.getElementById("data-chart")
    dataChart.classList.toggle("middle")
})
