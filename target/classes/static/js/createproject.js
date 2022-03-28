//Define variables
let name;
let description;
let fundsRequired;
let fundsCollected;
let projectCoverImage;
let communityIdSelect
let communityId;
let communityName;
//We assume that the user is a logged in user cookie must be present
let cookieArray =document.cookie.split(":")[2];
let userId = cookieArray ? cookieArray.split(",")[0] : null;
    if (!userId) {
        window.alert("You need to login first. Redirecting...");
        window.location.href = "/Login";
    }
//Define methods
//Method to capture user input
const captureUserInput = (e) =>{
    const eventId = e.target.id;
    const eventValue = e.target.value;
    if(eventId ==="projectName"){
        name = eventValue;
    }else if( eventId === "description"){
        description = eventValue;
    }else if (eventId === "communitySelect"){
        communityIdSelect = eventValue;
        if (eventValue == "add-community"){
            window.open(eventValue);
        }
    }
    else if (eventId === "fundsRequired"){
        fundsRequired = eventValue;
    }
    else if (eventId === "fundsCollected"){
        fundsCollected = eventValue;
    }
}
//Method to get communityId from user to show in the dropdown list
async function fetchCommunities (){
    const url = `http://localhost:8081/api/get-user/${userId}`
    const response = await fetch(url)
    if (response.status == "200") {
        const data = await response.json();
        let communitySet = data.communitySet;
        console.log(communitySet);
        for (let i = 0; i < communitySet.length; i++) {
            communityId = communitySet[i].id
            communityName = communitySet[i].name
            communitySelectInput.options.add(new Option(communityName,communityId));
        }
        communitySelectInput.options.add(new Option("Create a new community","add-community"));
    } else {
        throw new Error("Something went bad. Please refresh or login");
    }
}
fetchCommunities().then();
//Method to save the project to the backend by requesting api
const saveProject = async (e) =>{
    e.preventDefault();
    const url = `http://localhost:8081/api/add-project/userId/${userId}/communityId/${communityIdSelect}`
    const newProjectObject = {
        name,
        description,
        fundsRequired,
        fundsCollected,
        projectCoverImage
    }
    const response = await fetch(url, {
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(newProjectObject)
    })
    if(response.status == "200"){
        console.log("Succeed adding project")
        window.location.href ="/projects"; //open the project list when create successfully
    }else{
        window.alert("Something went bad. Please try again later")
    }
}
// Method to capture the filename of img
const captureImageUploaded = async (e) =>{
    const uploadImageBackEndUri = "http://localhost:8081/api/upload-image";
    const imageFile = e.target.files[0];
    //make a fetch POST call to the api/upload-image
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
        //get filename and alert user
        projectCoverImage = imageFile.name;
        alert("Upload "+projectCoverImage+" successfully")
    }
}

// Capture DOM elements
const communitySelectInput = document.getElementById("communitySelect");
const projectNameInput = document.getElementById("projectName");
const projectDescriptionInput = document.getElementById("description");
const fundsRequiredInput = document.getElementById("fundsRequired");
const fundsCollectedInput = document.getElementById("fundsCollected");
//Add project button
const createProjectButton = document.getElementById("createProject-button");
//To get hold of the upload image elements
const uploadCoverImgInput = document.getElementById("uploadCoverImg");

// Event Listeners
communitySelectInput.addEventListener("change",captureUserInput);
projectNameInput.addEventListener("change", captureUserInput);
projectDescriptionInput.addEventListener("change", captureUserInput);
fundsRequiredInput.addEventListener("change", captureUserInput);
fundsCollectedInput.addEventListener("change", captureUserInput);
createProjectButton.addEventListener("click",saveProject);
uploadCoverImgInput.addEventListener("change",captureImageUploaded);