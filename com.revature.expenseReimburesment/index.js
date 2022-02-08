// JS stands for Javascript
// JS to Java is like hamster is to ham
// The creator's of JS wanted to capitalize on Java's popularity
// JS is dynamically typed. Infers type of var
// JS datatypes: FUNSBONS
//  Stands for Functions, undefined, numbers, boolean, objects, null, symbols, strings
// Truthy, Falsy thingy
// In js all vars have an equivalent boolean value
// Falsy: FUN0NE
// False, undefined, null, +-0, "", NaN
// Created by Brendan Eich, @brendaneich

// AJAX
// Async JS and XML

//used to send HTTP requests
let xhr = new XMLHttpRequest();
let empId;
let url;
let data;
// creating an empty object
let employee = {};
let name;
let manager;
let manId;

function getEmployee(){
    // Accessing the DOM
    // Document Object Model, a tree representation of your webpage
    // Tags are nodes, and tags within other tags are child nodes
    // Body tag is a node with children nodes of your divs in the body
    // with JS you can manipulate the dom
    // dynamically change the way your html doc is structured
    empId = document.getElementById('input').value;
    console.log(empId);

    // assemble the http request
    // http method, endpoint, if you wanna run it async
    // String interpolation in js uses back ticks and ${variableName}
    xhr.open('GET', `http://localhost:7001/Employees/${empId}`, true);
    
    // send the request
    xhr.send();

    // define what happens after request is sent
    // ready state describes state of your request
    // 0 - uninitialized
    // 1 - loading (server connection established) the open method has been invoked
    // 2 - loaded (request received by server) send has been called
    // 3 - interactive (processing request) response body is being received
    // 4 - complete (response received) 
    xhr.onreadystatechange = function () {
        // check if the response is received AND the status code is successful
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // deserialize the JSON
            // deserialize the response body
            employee = JSON.parse(xhr.responseText);
            document.querySelector('#input').value = '';
            name = employee.name;
            manager = employee.manager;
            manId = employee.manId;
            console.log(manager);

            if(manager === true){
                document.getElementById('activeDisplay').innerHTML = document.getElementById('managerOptions').innerHTML;
            }
            else{
                document.getElementById('activeDisplay').innerHTML = document.getElementById('employeeOptions').innerHTML;
                document.getElementById('employeeOptions').innerHTML = '';
            }

            document.getElementById('userInput').innerHTML = "What would you like to do?";
            document.getElementById('formHeader').innerHTML = 'Welcome ' + name;
        }
    }

}

function start(){
    const radioButtons = document.querySelectorAll('input[name="radioSelect"]');
    let selectedAction;
    for (const radioButton of radioButtons) {
        if (radioButton.checked) {
            selectedAction = radioButton.value;
            break;
        }
    }
    console.log(selectedAction);

    if(selectedAction == "submitRequest"){
        document.getElementById('userInput').innerHTML = "Please enter request details for " + name + ":";
        document.getElementById('activeDisplay').innerHTML = document.getElementById('submitRequestInput').innerHTML;
    }
    else if(selectedAction == "viewRequests"){
        document.getElementById('activeDisplay').innerHTML = document.getElementById('viewRequestsInput').innerHTML;
    }
    else if(selectedAction == "updateRequest"){
        updateRequest();
    }
    else if(selectedAction == "viewEmployees"){
        viewEmployees();
    }
    else if(selectedAction == "addEmployee"){
        document.getElementById('activeDisplay').innerHTML = document.getElementById('addEmployeeInput').innerHTML;
    }
    else if(selectedAction == "exit"){
        exit();
    }
}

function submitRequest(){
    let typeOption = document.getElementById("type");
    let type = typeOption.options[typeOption.selectedIndex].text;
    let desc = document.getElementById("description").value;
    let amount = document.getElementById("amount").value;
    url = "http://localhost:7001/AddRequest";
    xhr = new XMLHttpRequest();
    xhr.open("POST", url);

    xhr.setRequestHeader("Content-Type", "text/plain");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            console.log(xhr.status);
            console.log(xhr.responseText);
        }};

    data = '{ "empId": ' + '"' + empId + '"' + 
            ', "type": ' + '"' + type + '"' + 
            ', "description": ' + '"' +  desc + '"' + 
            ', "amount": ' + '"' + amount + '"' + 
            '}';
    xhr.send(data);

    document.getElementById("description").value = '';
    document.getElementById("amount").value = '';
    document.querySelector('#type').value = '';
}

function viewRequestsByStatus(){
    document.getElementById('userInput').innerHTML = "Request Data";
    let output = '';
    let statusOption = document.getElementById("findByStatusSelect");
    let status = statusOption.options[statusOption.selectedIndex].value;
    let req;
    let statusHTML;

    xhr = new XMLHttpRequest();
    xhr.open('GET', `http://localhost:7001/RequestsByStatus/${status}`, true);
    xhr.send();
    xhr.onreadystatechange = function () {
        // check if the response is received AND the status code is successful
        if (xhr.readyState == 4 && xhr.status == 201)
        {
            let requests = JSON.parse(xhr.responseText);

            Object.entries(requests).forEach((request) => {
                req = request[1];
                statusHTML = '<select name="statusSelect" id="'+ req.id +'" class="form-select" onChange="updateRequest(' + req.id +')">';
                if(req.status == 'PENDING'){
                    statusHTML += '<option value="PENDING" selected="selected">Pending</option><option value="APPROVED">Approved</option><option value="REJECTED">Rejected</option>';
                }
                else if(req.status ='APPROVED'){
                    statusHTML += '<option value="PENDING">Pending</option><option value="APPROVED" selected="selected">Approved</option><option value="REJECTED">Rejected</option>';
                }
                else if(req.status ='REJECTED'){
                    statusHTML += '<option value="PENDING">Pending</option><option value="APPROVED">Approved</option><option value="REJECTED" selected="selected">Rejected</option>';
                }
                statusHTML += '</select>';

                output += "<tr>";
                output += '<th scope="row">' + req.id + '</th>';
                output += '<td>' + req.type + '</td>';
                output += '<td>' + req.description + '</td>';
                output += '<td>' + req.amount + '</td>';
                output += '<td>' + statusHTML + '</td>';
                output += '<td>' + req.empId + '</td>';
                output += "</tr>";
            });

            document.getElementById("requestOutputBody").innerHTML = output;
            document.getElementById("activeDisplay").innerHTML = document.getElementById("requestOuput").innerHTML;
        }
    }
    
}

function viewRequestsByEmpId(){
    document.getElementById('userInput').innerHTML = "Request Data";
    let output = '';
    let employeeId = document.getElementById('inputEmpId').value;
    let req;
    let statusHTML;

    xhr = new XMLHttpRequest();
    xhr.open('GET', `http://localhost:7001/RequestsByEmployee/${employeeId}`, true);
    xhr.send();
    xhr.onreadystatechange = function () {
        // check if the response is received AND the status code is successful
        if (xhr.readyState == 4 && xhr.status == 201)
        {
            console.log(xhr.responseText);
            let requests = JSON.parse(xhr.responseText);

            Object.entries(requests).forEach((request) => {
                req = request[1];
                statusHTML = '<select name="statusSelect" id="'+ req.id +'" class="form-select" onChange="updateRequest(' + req.id +')">';
                if(req.status == 'PENDING'){
                    statusHTML += '<option value="PENDING" selected="selected">Pending</option><option value="APPROVED">Approved</option><option value="REJECTED">Rejected</option>';
                }
                else if(req.status ='APPROVED'){
                    statusHTML += '<option value="PENDING">Pending</option><option value="APPROVED" selected="selected">Approved</option><option value="REJECTED">Rejected</option>';
                }
                else if(req.status ='REJECTED'){
                    statusHTML += '<option value="PENDING">Pending</option><option value="APPROVED">Approved</option><option value="REJECTED" selected="selected">Rejected</option>';
                }
                statusHTML += '</select>';

                output += "<tr>";
                output += '<th scope="row">' + req.id + '</th>';
                output += '<td>' + req.type + '</td>';
                output += '<td>' + req.description + '</td>';
                output += '<td>' + req.amount + '</td>';
                output += '<td>' + statusHTML + '</td>';
                output += '<td>' + req.empId + '</td>';
                output += "</tr>";
            });

            document.getElementById("requestOutputBody").innerHTML = output;
            document.getElementById("activeDisplay").innerHTML = document.getElementById("requestOuput").innerHTML;
        }
    }
}

function updateRequest(id){
    let statusOption = document.getElementById(id);
    let status = statusOption.options[statusOption.selectedIndex].value;

    const dataObject = {
        id: id,
        status: status
    };
    
    fetch(`http://localhost:7001/UpdateRequest/${id}/${status}`,{
        method:'PUT',
        headers:{
        'Content-Type':'application/json'
        },
        body:JSON.stringify(dataObject)
    });
}

function viewEmployees(){
    output = '';
    let emp;
    xhr = new XMLHttpRequest();
    xhr.open('GET', `http://localhost:7001/Employees`, true);
    xhr.send();
    xhr.onreadystatechange = function () {
        // check if the response is received AND the status code is successful
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            console.log(xhr.responseText);
            let employees = JSON.parse(xhr.responseText);

            Object.entries(employees).forEach((employee) => {
                emp = employee[1];
                output += "<tr>";
                output += '<th scope="row">' + emp.empId + '</th>';
                output += '<td>' + emp.name + '</td>';
                output += '<td>' + emp.manager + '</td>';
                output += '<td>' + emp.manId + '</td>';
                output += "</tr>";
            });

            document.getElementById("employeeOutputBody").innerHTML = output;
            document.getElementById("activeDisplay").innerHTML = document.getElementById("employeeOuput").innerHTML;
        }
    }
}

function addEmployee(){
    let name = document.getElementById("employeeName").value;
    let managerId = document.getElementById("managerId").value;
    let isManagerOption = document.getElementById('isManager');
    let isManager = isManagerOption.options[isManagerOption.selectedIndex].value;

    url = "http://localhost:7001/AddEmployee";
    xhr = new XMLHttpRequest();
    xhr.open("POST", url);

    xhr.setRequestHeader("Content-Type", "text/plain");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            console.log(xhr.status);
            console.log(xhr.responseText);
        }};

    data = '{ "name": ' + '"' + name + '"' + 
            ', "manId": ' + '"' + managerId + '"' + 
            ', "manager": ' + '"' +  isManager + '"' +
            '}';
    xhr.send(data);

    document.getElementById("employeeName").value = '';
    document.getElementById("managerIdInput").value = '';
    document.getElementById("isManager").value = '';
}

function displayManIdInput(){
    let isManagerOption = document.getElementById('isManager');
    let isManager = isManagerOption.options[isManagerOption.selectedIndex].value;
    console.log(isManager);
    if(isManager === 'false'){
        document.getElementById("managerId").style.visibility = 'visibile';
    }
}

function exit(){
    alert('Goodbye. Have a Great Day!')
    location.reload();
}

function back(){
    if(manager === true){
        document.getElementById('activeDisplay').innerHTML = document.getElementById('managerOptions').innerHTML;
    }
    else{
        document.getElementById('activeDisplay').innerHTML = document.getElementById('employeeOptions').innerHTML;
        document.getElementById('employeeOptions').innerHTML = '';
    }

    document.getElementById('userInput').innerHTML = "What would you like to do?";
    document.getElementById('formHeader').innerHTML = 'Welcome ' + name;
}

// Make sure this code gets executed after the DOM is loaded.
document.querySelector("#input").addEventListener("keyup", event => {
    if(event.key !== "Enter") return; // Use `.key` instead.
    document.querySelector("#submit").click(); // Things you want to do.
    event.preventDefault(); // No need to `return false;`.
});


function changeFunc(){
    let findByOption = document.getElementById("findBy");
    let findBy = findByOption.options[findByOption.selectedIndex].value;
    console.log(findBy);

    if(findBy == "status"){
        document.getElementById("submitFindBy").setAttribute('onclick', "viewRequestsByStatus()");
        document.getElementById("findByStatus").style.visibility = "visible";
        document.getElementById("findByEmpId").style.visibility = "hidden";
    }
    else if(findBy == "empId"){
        document.getElementById("submitFindBy").setAttribute('onclick', "viewRequestsByEmpId()");
        document.getElementById("findByEmpId").style.visibility = "visible";
        document.getElementById("findByStatus").style.visibility = "hidden";
    }
    else{
        document.getElementById("submitFindBy").onclick = '';
    }
}