//Define variables
let name;
let description;
let communityImage;
let validatedCommunityName = false;
let validatedCommunityDescription = false;
//We assume that the user is a logged in user cookie must be present
let cookieArray = document.cookie.split(":")[2];
let userId = cookieArray ? cookieArray.split(",")[0] : null;
if (!userId) {
  window.alert("You need to login first. Redirecting...");
  window.location.href = "/Login";
}

//Define methods
//Method to capture user input
const captureUserInput = (e) => {
  const eventId = e.target.id;
  const eventValue = e.target.value;
  if (eventId === "community-name") {
    const nameValidated = validateCommunityNameMethod(eventValue);
    if (nameValidated) {
      name = eventValue;
    } else {
      communityNameElement.focus();
    }
  } else if (eventId === "community-description") {
    const descriptionValidated = validateCommunityDescrMethod(eventValue);
    if (descriptionValidated) {
      description = eventValue;
    } else {
      communityDescriptionElement.focus();
    }
  }
};

//Method to do the validations of community name
const validateCommunityNameMethod = (value) => {
  let lengthValidated = false;
  let contentValidated = false;
  //check length of the value
  if (value.length < 255) {
    lengthValidated = true;
  } else {
    window.alert("Name cannot be more than 255 characters");
  }

  //only alphabets allowed
  const regex = /[\d%@"|#\^\*\[\]]/;
  if (!regex.test(value)) {
    contentValidated = true;
  } else {
    window.alert("Name cannot be blank / Name can have only alphabets");
  }

  if (lengthValidated && contentValidated) {
    validatedCommunityName = true;
  }
  return validatedCommunityName;
};

//Method to validate community description
const validateCommunityDescrMethod = (value) => {
  let lengthValidated = false;
  let contentValidated = false;
  //check length of the value
  if (value.length < 800) {
    lengthValidated = true;
  } else {
    window.alert("Description cannot be more than 800 characters");
  }

  //only alphabets allowed
  const regex = /[\d%@"|#\^\*\[\]]/;
  if (!regex.test(value)) {
    contentValidated = true;
  } else {
    window.alert(
      "Description cannot be blank / Can contain only alphabets, hiphen, underscore, double quotes only"
    );
  }

  if (lengthValidated && contentValidated) {
    validatedCommunityDescription = true;
  }
  return validatedCommunityName;
};

//Method to save the community to the backend
const saveCommunity = async (e) => {
  e.preventDefault();
  if (validatedCommunityName && validatedCommunityDescription) {
    const url = `http://localhost:8081/api/add-community/${userId}`;
    const newCommunityObject = {
      name,
      description,
      communityImage,
    };
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newCommunityObject),
    });
    if (response.status == "200") {
      window.location.href = "/communities";
    } else {
      window.alert("Something went bad. Please try again later");
    }
  } else {
    window.alert(
      "Your form cannot be validated. Please fill properly and try again"
    );
  }
};
const captureImageUploaded = async (e) => {
  const uploadImageBackEndUri = "http://localhost:8081/api/upload-image";
  const imageFile = e.target.files[0];
  //Step 1 update the message of the STATUS as uploading
  const loadingText = document.createTextNode("Uploading....");
  uploadedImageStatusElement.innerHTML = "";
  uploadedImageStatusElement.append(loadingText);
  //make a fetch POST call to the api/upload-image
  const formObject = new FormData();
  formObject.append("image", imageFile);
  const response = await fetch(uploadImageBackEndUri, {
    method: "POST",
    headers: {
      Accept: "application/json",
    },
    body: formObject,
  });
  if (response.status == "200") {
    //update the image upload status method
    const uploadSuccess = document.createTextNode(
      `${imageFile.name} has been uploaded successfully!`
    );
    uploadedImageStatusElement.innerHTML = "";
    uploadedImageStatusElement.append(uploadSuccess);
    communityImage = imageFile.name;
  } else {
    const uploadError = document.createTextNode(
      "Something went wrong. Please try again later"
    );
    uploadedImageStatusElement.innerHTML = "";
    uploadedImageStatusElement.append(uploadError);
  }
};

//Get the elements
const communityNameElement = document.getElementById("community-name");
const communityDescriptionElement = document.getElementById(
  "community-description"
);

//Save the community BUTTON
const addCommunityButton = document.getElementById("add-community_CTA");
//To get hold of the upload image elements
const uploadImageElement = document.getElementById("uploadImage-CTA");
const uploadedImageStatusElement = document.getElementById(
  "upload-image__status"
);

//Attach Event Listeners
communityNameElement.addEventListener("change", captureUserInput);
communityDescriptionElement.addEventListener("change", captureUserInput);

addCommunityButton.addEventListener("click", saveCommunity);
uploadImageElement.addEventListener("change", captureImageUploaded);
