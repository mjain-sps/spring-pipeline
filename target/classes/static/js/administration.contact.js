/* JS for the input effect when texting */

//Fetch user state from cookie
let cookieArray = document.cookie.split(":")[2];
let userId = cookieArray ? cookieArray.split(":")[0] : null;
//Method to check if the user is an admin
if(!userId.includes("admin") && !userId){
    window.alert("You need to be a logged in admin. Redirecting...");
    window.location.href = "/Login";
}
