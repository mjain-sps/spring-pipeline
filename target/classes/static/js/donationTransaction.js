//Fetch user state from cookie
let cookieArray = document.cookie.split(":")[2];
let userId = cookieArray ? cookieArray.split(",")[0] : null;
//Method to show transaction form
function togglePopup() {
    if (!userId) {
        window.alert("You need to login first. Redirecting...");
        window.location.href = "/Login";
    }else {
        document.getElementById('popup-1').classList.toggle('active');
    }
}
//Define variables for Fetch Payload
let donorName;
let donorEmail;
let amount;
let dateVariable;
let creditCard;
let cardCvc;
let cardYear;
let cardMonth;
//get project id
let url = window.location.href;
let index = url.lastIndexOf("\/");
projectId = url.substring(index + 1,url.length);
//Define functions
//Function to capture user input - NAME
const captureDonorNameInput = (event) =>{
    if(event.target.value.length >20 || event.target.value.length <1){
        window.alert("Name cannot be more than 20 or less than 1")
        donorNameInput.focus();
    }
    donorName = event.target.value
}

//Function to capture user input - EMAIL
const captureUserEmailInput = (event) =>{
    if(!event.target.value.includes("@gmail", "@yahoo")){
        window.alert("Invalid email")
        donorEmailInput.focus();
    }
    donorEmail = event.target.value;
}

//Function to capture user input - PROJECT ID
const captureProjectIdInput = (event) =>{
    if (event.target.value.length <=0){
        window.alert("Invalid Project Id")
        projectIdInput.focus();
    }
    projectId = event.target.value
}

//Function to capture user input - AMOUNT
const captureAmountInput = (event) =>{
    if (event.target.value.length <= 0){
        window.alert("You have entered an incorrect value, please re-enter a correct value.")
        amountInput.focus()
    }
    amount = event.target.value
}

//Function to capture user input - DATE
const captureDateInput = (event) =>{
    if (event.target.value.length = 0){
        window.alert("Select a valid date")
        dateInput.focus()
    }
    dateVariable = event.target.value
}
//Function to capture user input - CARD NUMBER
const captureCardNumberInput = (event) =>{
    if (event.target.value.length < 16 && event.target.value.length > 16){
        window.alert("Your card number should be a 16 digit number.")
        cardNumberInput.focus()
    }
    creditCard = event.target.value;
}

//Function to capture user input - CARD MONTH
const captureCardMonthInput =  (event) =>{
    if (event.target.value.length == null || event.target.value ==="Month") {
        window.alert("Select the correct options provided for you.")
        cardMonthInput.focus()
    }
    cardMonth = event.target.value;
}

//Function to capture user input - CARD CVC
const captureCardCvcInput = (event) =>{
    if (event.target.value.length <3 || event.target.value.length >3){
        window.alert("Your card cvc number should be a 3 digit number.")
        cardCvcInput.focus()
    }
    cardCvc = event.target.value;
}

//Function to capture user input - CARD YEAR
const captureCardYearInput = (event) =>{
    if (event.target.value.length == null || event.target.value ==="Year") {
        window.alert("Select the correct options provided for you.")
        cardYearInput.focus()
    }
    cardYear = event.target.value;
}

//Function to save the transaction to the backend
const saveTransaction = async (event) =>{
       event.preventDefault();
       const transactionObject = {
            donorName: donorName,
            donorEmail: donorEmail,
            amount:amount,
            date:dateVariable,
            projectId:projectId,
            creditCard:creditCard,
            cardCvc:cardCvc,
            cardYear:cardYear,
            cardMonth:cardMonth,
           userId: userId
       }
       const response = await fetch("http://localhost:8081/api/new-transaction", {
           method:"POST",
           headers:{
               "Content-type":"Application/json"
           },
           body: JSON.stringify(transactionObject)
       })
        console.log("response--->", response)
        if(response.status ==200){
            const data = await response.json();
            // let addAmount = data.amount;
            const addFundsUrl = `http://localhost:8081/api/update-funds/projectId/${projectId}/amount/${amount}`
            const responseAddFunds = await fetch(addFundsUrl, {
                method:"PUT",
                headers:{
                    "Content-type":"Application/json"
                }
            })
            if (responseAddFunds.status == "200") {
                window.alert("Transaction completed. Redirecting...")
                window.location.reload();
            } else {
                throw new Error("Failed to donate,please try again");
            }
        }else{
            window.alert("Something wrong. Try again later")
        }
}
//Lets get all the elements from HTML based on ID
const donorNameInput = document.getElementById("donerName");
const donorEmailInput = document.getElementById("donerEmail");
const projectIdInput = document.getElementById("projectId");
const amountInput = document.getElementById("amount");
const dateInput = document.getElementById("date");
const cardNumberInput = document.getElementById("creditCard");
const cardCvcInput = document.getElementById("cardCvc");
const cardMonthInput = document.getElementById("cardMonth");
const cardYearInput = document.getElementById("cardYear");
const saveTransactionButton = document.getElementById("createDonateButton");
projectIdInput.value = projectId;

//Attach event listeners
donorNameInput.addEventListener("change", captureDonorNameInput);
donorEmailInput.addEventListener("change", captureUserEmailInput);
projectIdInput.addEventListener("change", captureProjectIdInput);
amountInput.addEventListener("change", captureAmountInput);
dateInput.addEventListener("change", captureDateInput);
cardNumberInput.addEventListener("change", captureCardNumberInput);
cardCvcInput.addEventListener("change", captureCardCvcInput);
cardMonthInput.addEventListener("change", captureCardMonthInput);
cardYearInput.addEventListener("change", captureCardYearInput);
saveTransactionButton.addEventListener("click", saveTransaction)

