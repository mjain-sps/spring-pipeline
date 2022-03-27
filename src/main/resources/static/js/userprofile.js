//Define Variables
var userAboutYourself = null;
var userQualifications = null;
var userHobbies = null;
var userAchievements = null;
var userCommunity = null;
var userLineOneAddress = null;
var userLineTwoAddress = null;
var userCity = null;
var userPostcode = null;
var userCountry = null;
let cookieArray =document.cookie.split(":")[2];
let userId = cookieArray.split(",")[0];


// Function to capture user Inputs
const captureUserInput = function (e) {
    const userInput = e.target.value;
    const elementName = e.target.name;
    if (elementName === "aboutyourself") {
        userAboutYourself = userInput;

    } else if (elementName === "qualifications") {
        userQualifications = userInput;

    } else if (elementName === "hobbies") {
        userHobbies = userInput;

    } else if (elementName === "achievements") {
        userAchievements = userInput;

    }else if (elementName === "community") {
        userCommunity = userInput;

    }else if (elementName === "lineoneaddress") {
        userLineOneAddress = userInput;

    }else if (elementName === "linetwoaddress") {
        userLineTwoAddress = userInput;

    }else if (elementName === "city") {
        userCity = userInput;

    }else if (elementName === "postcode") {
        userPostcode = userInput;

    }else if (elementName === "country") {
        userCountry = userInput;
};
const fillUserProfile = async function(e){
e.preventDefault();

}
}
// Function to post profile
const postUserProfile = async function (e) {

    e.preventDefault();
    if (userAboutYourself !=null && userQualifications !=null && userHobbies !=null && userAchievements !=null
    && userCommunity !=null && userLineOneAddress != null && userLineTwoAddress !=null && userCity !=null && userPostcode !=null
    && userCountry !=null) {
        const userProfile = {
            aboutyourself:userAboutYourself,
            qualifications:userQualifications,
            hobbies:userHobbies,
            achievements:userAchievements,
            community:userCommunity,
            lineoneaddress:userLineTwoAddress,
            linetwoaddress:userLineTwoAddress,
            city:userCity,
            postcode:userPostcode,
            country:userCountry
        }
        console.log(userProfile);
        const response = await fetch("/api/update-profile/{userId}",{
            method:"PUT",
            headers:{
                "Content-type":"application/json"
            },
            body:JSON.stringify(userProfile)
        })
        if (response.status =="200") {
            console.log("Saved");
        } else {
            window.alert("A problem has occurred. Please try again later.")
        }
    } else {
        window.alert("Please Ensure that you Have Completed all Required Fields.")
    }
}

// Function to post the file image
const editImage  = async function (e){
const uploadImageBackEndUri = "http://localhost:8081/api/upload-image";
    const imageFile = e.target.files[0];
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
        projectCoverImage = imageFile.name;
        alert("Upload "+projectCoverImage+" successfully")
    }
}





// Capture DOM elements
const aboutYourselfInput = document.getElementById("aboutyourself");
const qualificationsInput = document.getElementById("qualifications");
const hobbiesInput = document.getElementById("hobbies");
const achievementsInput = document.getElementById("achievements");
const communityInput = document.getElementById("community");
const lineOneAddressInput = document.getElementById("lineoneaddress");
const lineTwoAddressInput = document.getElementById("linetwoaddress");
const cityInput = document.getElementById("city");
const postcodeInput = document.getElementById("postcode");
const countryInput = document.getElementById("country");
const saveProfileButton = document.getElementById("saveProfile-button");
const editProfileImageButton = document.getElementById("editProfileImage-button");

// Event Listeners
aboutYourselfInput.addEventListener("change", captureUserInput);
qualificationsInput.addEventListener("change", captureUserInput);
hobbiesInput.addEventListener("change", captureUserInput);
achievementsInput.addEventListener("change", captureUserInput);
communityInput.addEventListener("change", captureUserInput);
lineOneAddressInput.addEventListener("change", captureUserInput);
lineTwoAddressInput.addEventListener("change", captureUserInput);
cityInput.addEventListener("change", captureUserInput);
postcodeInput.addEventListener("change", captureUserInput);
countryInput.addEventListener("change", captureUserInput);
saveProfileButton.addEventListener("click", postUserProfile);
editProfileImageButton.addEventListener("click",editImage);
