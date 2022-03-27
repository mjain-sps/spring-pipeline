/* JS for the input effect when texting */

const inputs = document.querySelectorAll(".input");

function focusFunc(){
    let parent = this.parentNode;
    parent.classList.add("focus");
}

function blurFunc(){
    let parent = this.parentNode;
    if (this.value == ""){
    parent.classList.remove("focus");
    }
}


inputs.forEach(input =>{
    input.addEventListener("focus", focusFunc);
    input.addEventListener("blur", blurFunc);
})

//Define variables for fetching user's enquiry details
let contactName;
let contactEmail;
let contactPhone;
let contactMessage;
let contactUpload;

/*

            DEFINE FUNCTION

 */

//Function to capture user input - NAME
const captureContactNameInput = (event) =>{
    if (event.target.value.length > 20 || event.target.value.length < 1){
        window.alert("Name cannot be more than 20 characters or less than 1.")
        contactNameInput.focus();
    }
    contactName = event.target.value;
}

//Function to capture user input - EMAIL
const captureContactEmailInput = (event) =>{
    if (!event.target.value.includes("@gmail", "@yahoo")){
        window.alert("Invalid email")
        contactEmailInput.focus();
    }
    contactEmail = event.target.value;
}

//Function to capture user input - phone
const captureContactPhoneInput = (event) =>{
    if (event.target.value.length > 11 || event.target.value.length < 11){
        window.alert("Invalid number, the input should be a total of 11 digits")
        contactEmailInput.focus();
    }
    contactPhone = event.target.value;
}

//Function to capture user input - Message
const captureContactMessageInput = (event) =>{
    if (event.target.value.length > 250 || event.target.value.length <= 0){
        window.alert("The message box cannot be left empty or you have reached the maximum character amount.")
        contactMessageInput.focus();
    }
    contactMessage = event.target.value;
}

//Function to save the transaction to the backend
const saveContact = async (event) => {
    event.preventDefault();
    if (contactName !=null && contactEmail !=null && contactPhone !=null && contactMessage !=null ) {
    const contactObject = {
        name: contactName,
        email: contactEmail,
        phone: contactPhone,
        message: contactMessage,
    }
    if(contactUpload){
        contactObject["uploadInput"] = contactUpload
    }

    const response = await fetch("http://localhost:8081/api/new-contact", {
        method: "POST",
        headers: {
            "Content-type": "Application/json"
        },
        body: JSON.stringify(contactObject)
    })
    if (response.status == "200") {
        const data = await response.json()
        window.alert("Your enquiry has been sent to the administration team.")
        window.location.reload(); //open the project list when create successfully
    } else {
        throw new Error("There is an issue in the form. Please try again later");
    }
} else{
    window.alert("Something is wrong. Try again later.")
    }
}


// Method to capture the filename of img
const captureContactUploadInput = async (event) =>{
    const uploadPdfBackEndUri = "http://localhost:8081/api/upload-image";
    const pdfFile = event.target.files[0];
    //Updating the message of the status as uploading
    const loadingText = document.createTextNode("Uploading....")
    contactUploadDisplay.innerHTML="";
    contactUploadDisplay.append(loadingText);
    //make a fetch POST call to the api/upload-image
    const formObject = new FormData();
    formObject.append("image", pdfFile);
    const response = await fetch(uploadPdfBackEndUri, {
        method:"POST",
        body: formObject
    })
    if(response.status == "200") {
        //get filename and alert user
        const uploadSuccess = document.createTextNode(`${pdfFile.name} has been uploaded successfully!`)
        contactUploadDisplay.innerHTML="";
        contactUploadDisplay.append(uploadSuccess);
        contactUpload = pdfFile.name;
    }else{
        const uploadError = document.createTextNode("Something went wrong. Please try again later")
        contactUploadDisplay.innerHTML="";
        contactUploadDisplay.append(uploadError);
    }
}


//Lets get all the elements from the HTML using their ID
const contactNameInput = document.getElementById("name");
const contactEmailInput = document.getElementById("email");
const contactPhoneInput = document.getElementById("phone");
const contactMessageInput = document.getElementById("message");
const contactUploadInput = document.getElementById("uploadInput");
const contactUploadDisplay = document.getElementById("print-file-upload");
const saveContactButton = document.getElementById("contactBtn");


//Attach event listeners
contactNameInput.addEventListener("change", captureContactNameInput);
contactEmailInput.addEventListener("change", captureContactEmailInput);
contactPhoneInput.addEventListener("change", captureContactPhoneInput);
contactMessageInput.addEventListener("change", captureContactMessageInput);
contactUploadInput.addEventListener("change", captureContactUploadInput);
saveContactButton.addEventListener("click", saveContact);