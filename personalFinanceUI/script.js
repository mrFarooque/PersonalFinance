// API CALLS starts here
// GET: list all expense of current month
async function listExpenseCurrentMonth() {
    let response = await fetch("http://localhost:8888/finance/list/current").then(res => res.json())
    updateTable(response, "current")
}
listExpenseCurrentMonth();

// alternate of listExpenseCurrentMonth() is showAllData()
async function showAllData(type, month) {
    let response = await fetch("http://localhost:8888/finance/list/"+type+"/"+month).then(res => res.json())
    updateTable(response, month)
}

// POST: expense or cash
 async function addMoney(value) {
    let request = getFormData();
    if(value == 0) {
        request.type = "EXPENSE"
        let response = await fetch("http://localhost:8888/finance/", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(request)
        }
        )
    } else {
        request.type = "INCOME"
        request.category = "SALARY"
        let response = await fetch("http://localhost:8888/finance/", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(request)
            }
        )
    }
    listExpenseCurrentMonth()
 }
 // call all GET Api to fill data
 async function fillData(month) {
    let categories = ["FOOD", "UTILITIES", "RENT", "DEBT", "ENTERTAINMENT", "GIFTS"]
    categories.forEach(category => {
        async function getData() {
            let response = await fetch("http://localhost:8888/finance/total/EXPENSE/"+category+"/"+month).then(res => res.json())
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
    let balance = await fetch("http://localhost:8888/finance/balance/"+month).then(res => res.json())
    document.getElementById("balance-data").innerText = balance
    let balanceTag = document.getElementById("balance-ptag")
    if(balance < 0){
        balanceTag.classList.remove("positive-balance-bgcolor")
        balanceTag.classList.add("negative-balance-bgcolor")
    }
    else {
        balanceTag.classList.remove("negative-balance-bgcolor")
        balanceTag.classList.add("positive-balance-bgcolor")
    }
 }
 async function deletExpenseById(id) {  
    fetch("http://localhost:8888/finance/"+id, {method: 'DELETE'})
 }
// all API CALLS ends here
// DOM to list all expense 
function updateTable(res, month) {
    fillData(month);
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
        let del = document.createElement("button");
        let edit = document.createElement("button");
        td1.innerText = index+1;
        td2.innerText = ele.purpose;
        td3.innerText = ele.amount;
        td4.innerText = ele.category;
        td5.innerText = ele.date;
        del.innerHTML = '<i class="fa-solid fa-trash"></i>'
        edit.innerHTML = '<i class="fa-solid fa-pen-nib"></i>'
        td6.append(del, edit);
        tr.append(td1, td2, td3, td4, td5, td6);
        tbody.append(tr);
        
        // if income/expense bgcolor changes
        if(ele.type == "EXPENSE") {
            tr.classList.add("expense-color")
            del.classList.add("expense-color")
            edit.classList.add("expense-color")
        }
        else {
            tr.classList.add("income-color")
            del.classList.add("income-color")
            edit.classList.add("income-color")
        }
        // delete and edit functionalities
        del.addEventListener("click", () => {
            deletExpenseById(ele.id)
            location.reload()
        })
        edit.addEventListener("click", () => {
            // open form
            let toggleDiv = document.getElementById("toggle")
            // if form is close open and if already open then do not close
            if(toggleDiv.classList.contains("hide-form")) toggleForm()
            // clear form data
            form.reset();
            // enter form data
            form.id.value = ele.id
            form.amount.value = ele.amount
            form.purpose.value = ele.purpose
            form.date.value = ele.date
            // check which category it belogns and tick that checkbox
            if(ele.category == "FOOD") form.food.checked = true
            if(ele.category == "UTILITIES") form.utilities.checked = true
            if(ele.category == "RENT") form.rent.checked = true
            if(ele.category == "DEBT") form.debt.checked = true
            if(ele.category == "GIFTS") form.debt.checked = true
            if(ele.category == "ENTERTAINMENT") form.entertainment.checked = true
        })
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
        id: form.id.value,
        amount: form.amount.value,
        purpose: form.purpose.value,
        date: form.date.value,
        category: category
    }
    console.log(obj)
    function checkForCategory() {
        if (form.food.checked) category = 'FOOD';
        else if (form.utilities.checked) category = 'UTILITIES';
        else if (form.rent.checked) category = 'RENT';
        else if (form.debt.checked) category = 'DEBT'; 
        else if (form.gifts.checked) category = 'GIFTS'; 
        else if (form.entertainment.checked) category = 'ENTERTAINMENT'; 
    }
    form.id.value = 0
    form.reset();
    return obj;
}

// Add button to hide or unhide form
let toggleBtn = document.getElementById("toggle-btn");
toggleBtn.innerHTML = 'Add <i class="fa-solid fa-bars"></i>'
let bar = document.getElementsByName("button i");
let count = 0;
toggleBtn.addEventListener("click", toggleForm)
function toggleForm() {
    count++;
    let toggleDiv = document.getElementById("toggle")
    toggleDiv.classList.toggle("hide-form")
    let dataChart = document.getElementById("data-chart")
    dataChart.classList.toggle("middle")
    if(count % 2 == 1) {
        toggleBtn.innerHTML = 'Add <i class="fa-solid fa-xmark"></i>'
    } else {
        toggleBtn.innerHTML = 'Add <i class="fa-solid fa-bars"></i>'
    }
}
// filter data 
filterBtn = document.getElementById("filter-btn")
filterBtn.addEventListener("click", () => {
    let type = document.getElementById("which-table").value
    let month = document.getElementById("month").value
    showAllData(type, month)
})