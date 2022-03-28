// Define Variables.
let eventName = null;
let eventDate = null;
let eventAddress = null;
let eventContributors = null;
let eventDetails = null;
let eventImage = null;
let userObject = null;
let communitySelectElement= null;
let communitySelected=null
let projectSelected = null;
let url = null;
let cookieArray =document.cookie.split(":")[2];
let userId = cookieArray ? cookieArray.split(",")[0] : null;

// Checking for userId in cookie
if (userId) {
} else {
    window.alert("You need to login first. Redirecting...");
    window.location.href = "/Login";
}

// Method to close the create event disclaimer model.
const closeModelDisclaimer = (e) =>{
    e.preventDefault();
    document.getElementsByClassName("background--drop")[0].classList.add("model__hide")
    document.getElementsByClassName("create-event__disclaimer")[0].classList.add("model__hide")
    document.body.classList.remove("stop_body--scroll")
}

// Method to display the create event disclaimer model.
const showModelDisclaimer = (e) =>{
    e.preventDefault();
    window.scrollTo({ top: 0, behavior: 'smooth' });
    document.getElementsByClassName("background--drop")[0].classList.remove("model__hide")
    document.getElementsByClassName("create-event__disclaimer")[0].classList.remove("model__hide")
    document.body.classList.add("stop_body--scroll")
}

// Method to capture the project if the user has selected a project associated with an event.
const captureSelectedProject = (e) =>{
    if(e.target.value != null){

        projectSelected = e.target.value;
        url =`http://localhost:8081/api/add-event/project/${projectSelected}`
    }
}

// Method to form a drop down list of communities that the user has joined and associated projects.
const selectCommunity = (e) =>{
    communitySelected = e.target.value;
    url =`http://localhost:8081/api/add-event/community/${communitySelected}`
    const selectedCommunity = userObject.communitySet.find((community)=>community.id == e.target.value)
    if(selectedCommunity){
        // Check if community has any projects or not.
        if(!selectedCommunity.projectSet.length){
            const textNode = document.createTextNode("This Community has no Projects. Either create Events for a Community or create a Project first: ")
            const p = document.createElement("p").appendChild(textNode)
            const a = document.createElement("a")
            a.href ="http://localhost:8081:/CreateProject"
            a.text = "Create New Project"
            a.classList.add("btn")
            a.classList.add("btn-info")
            document.getElementById("community-project__placeholder").removeChild(document.getElementsByClassName("project-select")[0])
            document.getElementById("community-project__placeholder").appendChild(p);
            document.getElementById("community-project__placeholder").appendChild(a);
        }
        // Else create a drop down with the relevant projects of that community.
        else{
            const projectSelectElement = document.getElementsByClassName("project-select")[0];
            projectSelectElement.disabled = false;
            projectSelectElement.removeChild(document.getElementById("projectDummyOption"))
            const noProjectSelectOption = document.createElement("option")
            noProjectSelectOption.value = null;
            noProjectSelectOption.text = "Select a Project Under This community(For Event)"
            projectSelectElement.append(noProjectSelectOption);
            for(let i=0 ; i<selectedCommunity.projectSet.length ; i++){
                const optionElement = document.createElement("option")
                optionElement.value = selectedCommunity.projectSet[i].id;
                optionElement.text = selectedCommunity.projectSet[i].name;
                projectSelectElement.append(optionElement);
            }
            projectSelectElement.addEventListener("change", captureSelectedProject)
        }
    }
}


// Arrow function which fetches user object from backend.
const fetchUser = async () =>{
    try{
        const response = await fetch(`http://localhost:8081/api/get-user/${userId}`)
        if(response.status == "200"){
            userObject = await response.json()
            // Once the user object has been retrieved, create a drop down with the community set present in user object.
            // If user has joined no community, show a message.
            if(!userObject.communitySet.length){
                const textNode = document.createTextNode("No Communities Joined");
                const p = document.createElement("p")
                p.appendChild(textNode)
                document.getElementById("user-community__placeholder").append(p);
            }
            // Else show a drop down.
            else{
                const communitySet = userObject.communitySet;
                // Create a select box for project but show it as loading.
                const projectSelectElement = document.createElement("select")
                projectSelectElement.classList.add("form-select")
                projectSelectElement.classList.add("project-select")
                const projectDummyOption = document.createElement("option")
                projectDummyOption.id ="projectDummyOption"
                projectDummyOption.text="Select Community First"
                projectSelectElement.append(projectDummyOption)
                projectSelectElement.disabled = true;
                document.getElementById("community-project__placeholder").append(projectSelectElement)
                // Create a select box for community.
                const selectElement = document.createElement("select");
                selectElement.classList.add("form-select")
                selectElement.classList.add("community-select")
                const initialOption = document.createElement("option")
                initialOption.innerHTML = "<option>------Select Community to which Event belongs-----</option>"
                selectElement.add(initialOption);
                for(let i=0; i<communitySet.length; i++){
                    const optionElement = document.createElement("option")

                    optionElement.value =communitySet[i].id
                    optionElement.text = communitySet[i].name
                    selectElement.append(optionElement);
                }
                document.getElementById("user-community__placeholder").append(selectElement);
                communitySelectElement = document.getElementsByClassName("community-select")[0];
                communitySelectElement.addEventListener("change", selectCommunity);
            }

        }else{
            alert(("Cannot fetch user details. Please Login again"))
        }
    }catch (e){
        console.log(e)
    }

}

// Method to try and get the user object on load.
window.onload =()=>{
    fetchUser();
}

// No Special Characters
const regex = /[%@"|#\^\*\[\]+=<>~¬{}`]/;

// Function to validate the userInputs.
const validateEvent = function (elementName, userInput) {
    let validated = false;
    if (!regex.test(userInput)) {
        if (elementName === "date"){
            if (userInput.length > 7) {
                validated = true;
            } else {
                window.alert("\nPlease enter the full Event Date.")
            }
        } else if (elementName === "name") {
            if (1 < userInput.length && 256 > userInput.length) {
                validated = true;
            } else {
                window.alert("\nEvent Name should contain a minimum of 2 and a maximum of 255 characters.")
            }
        } else if (elementName === "address") {
            if (7 < userInput.length && 256 > userInput.length) {
                validated = true;
            } else {
                window.alert("\nEvent Address should contain a minimum of 8 and a maximum of 255 characters.")
            }
        } else if (elementName === "contributors") {
            if (2 < userInput.length && 256 > userInput.length) {
                validated = true;
            } else {
                window.alert("\nEvent Contributors should contain a minimum of 3 and a maximum of 255 characters.")
            }
        } else if (elementName === "aboutSection") {
            if (99 < userInput.length && 801 > userInput.length) {
                validated = true;
            } else {
                window.alert("\nEvent About Section should contain a minimum of 100 and a maximum of 800 characters.")
            }
        }

    } else {
        window.alert("Please don't use special characters.");
    }
    return validated;
}

// Function to capture user inputs in input boxes.
const captureUserInputEvent = function (e) {

    const userInput = e.target.value;
    const elementName = e.target.name;

    if (elementName === "name") {
        const validated = validateEvent(elementName, userInput);
        if (validated) {
            eventName = userInput;
        } else {
            eventNameInput.focus();
        }
    } else if (elementName === "date") {
        const validated = validateEvent(elementName, userInput);
        if (validated) {
            eventDate = userInput;
        } else {
            eventDateInput.focus();
        }
    } else if (elementName === "contributors") {
        const validated = validateEvent(elementName, userInput);
        if (validated) {
            eventContributors = userInput;
        } else {
            eventContributorsInput.focus();
        }
    } else if (elementName === "aboutSection") {
        const validated = validateEvent(elementName, userInput);
        if (validated) {
            eventDetails = userInput;
        } else {
            eventDetailsInput.focus();
        }
    } else if (elementName === "address") {
        const validated = validateEvent(elementName, userInput);
        if (validated) {
            eventAddress = userInput;
        } else {
            eventAddressInput.focus();
        }
    }
}

// Function to post event JSON to backend for storage in Event database.
const postEvent = async function (e) {

    e.preventDefault();
    if (eventName !=null && eventDate !=null && eventAddress !=null && eventContributors !=null && eventDetails !=null) {
        const event = {
            name:eventName,
            date:eventDate,
            aboutSection:eventDetails,
            contributors:eventContributors,
            address:eventAddress,
            eventImage:eventImage,
            creatorUserId:userId

        }

        const response = await fetch(url,{
            method:"POST",
            headers:{
                "Content-type":"application/json"
            },
            body:JSON.stringify(event)
        })
        if (response.status =="200") {
            const data = await response.json()
            alert("You have successfully created an event. Redirecting to Home Page")
            window.location.href = "/"
        } else {
            window.alert("A problem has occurred. Please try again later.")
        }
    } else {
        window.alert("Please ensure that you have completed all required fields correctly.")
    }
}

// Arrow Function to capture the uploaded image and send it to the backend if appropriate.
const captureImageUploaded = async (e) =>{
    const uploadImageBackEndUri = "http://localhost:8081/api/upload-image";
    const imageFile = e.target.files[0];
    // Step 1 update the message of the STATUS as uploading.
    const loadingText = document.createTextNode("Uploading....")
    uploadedImageStatusElement.innerHTML="";
    uploadedImageStatusElement.append(loadingText);
    // Make a fetch POST call to the api/upload-image.
    const formObject = new FormData();
    formObject.append("image", imageFile);
    const response = await fetch(uploadImageBackEndUri, {
        method:"POST",
        headers:{
            'Accept': 'application/json'
        },
        body: formObject
    })
    if(response.status == "200") {
        // Update the image upload status method.
        const uploadSuccess = document.createTextNode(`${imageFile.name} has been uploaded successfully!`)
        uploadedImageStatusElement.innerHTML="";
        uploadedImageStatusElement.append(uploadSuccess);
        eventImage = imageFile.name;
    }else{
        const uploadError = document.createTextNode("Something went wrong. Please try again later")
        uploadedImageStatusElement.innerHTML="";
        uploadedImageStatusElement.append(uploadError);
    }
}

// Capture DOM elements.
const eventNameInput = document.getElementById("name");
const eventDateInput = document.getElementById("date");
const eventAddressInput = document.getElementById("address");
const eventContributorsInput = document.getElementById("contributors");
const eventDetailsInput = document.getElementById("aboutSection");
const createEventButton = document.getElementById("createEvent-button");
const uploadImageElement = document.getElementById("uploadImage");
const uploadedImageStatusElement = document.getElementById("upload-image__status");
const postEventButton = document.getElementById("proceed");
const closeModelButton = document.getElementsByClassName("close_model")[0];

// Event Listeners.
eventNameInput.addEventListener("change", captureUserInputEvent);
eventDateInput.addEventListener("change", captureUserInputEvent);
eventAddressInput.addEventListener("change", captureUserInputEvent);
eventContributorsInput.addEventListener("change", captureUserInputEvent);
eventDetailsInput.addEventListener("change", captureUserInputEvent);
uploadImageElement.addEventListener("change",captureImageUploaded );
createEventButton.addEventListener("click", showModelDisclaimer);
closeModelButton.addEventListener("click", closeModelDisclaimer);
postEventButton.addEventListener("click", postEvent);
postEventButton.addEventListener("click", closeModelDisclaimer);
