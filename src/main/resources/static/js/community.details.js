//Method to close the modal
const closeModalDisclaimer = (e) =>{
    e.preventDefault();
    document.getElementsByClassName("background--drop")[0].classList.add("model__hide")
    document.getElementsByClassName("join-community__disclaimer")[0].classList.add("model__hide")
    document.body.classList.remove("stop_body--scroll")
}
const closeCommunityMemberModal = (e) =>{
    e.preventDefault();
    document.getElementsByClassName("background--drop")[0].classList.add("model__hide")
    document.getElementsByClassName("community-members__modal")[0].classList.add("model__hide")
    document.body.classList.remove("stop_body--scroll")

}
//method to join the community
const joinCommunity = async (e) =>{
    e.preventDefault();
    //Check if the user is a logged in user or not
    let cookieArray =document.cookie.split(":")[2];
    console.log("cookieArray", cookieArray);
    let userId = cookieArray ?cookieArray.split(",")[0] :null;
    if(!userId){
        window.alert("You need to Login first. Redirecting...")
        window.location.href = "/Login"
    }

    //Lets capture the community id
    const pathVariables = window.location.pathname.split("/");
    const communityId = pathVariables[2];
    //Make an post call to project controller /api/update-community/userid/{userId}/community-id/{communityId}

    const response = await fetch(`http://localhost:8081/api/update-community/userid/${userId}/community-id/${communityId}`,{
        method:"PUT",
    })
    if(response.status == "200"){
        window.alert("You have successfully joined this community")
        closeModalDisclaimer();
        window.location.reload();
    }else {
        window.alert("Something went bad. Please try again later")
    }
}

const communityMembers = (e) =>{
    e.preventDefault();
    window.scrollTo({ top: 0, behavior: 'smooth' });
    document.getElementsByClassName("background--drop")[0].classList.remove("model__hide")
    document.getElementsByClassName("community-members__modal")[0].classList.remove("model__hide")
    document.body.classList.add("stop_body--scroll")
}

//Method to display the join community disclaimer modal
const showModalDisclaimer = (e) =>{
    e.preventDefault();
    window.scrollTo({ top: 0, behavior: 'smooth' });
    document.getElementsByClassName("background--drop")[0].classList.remove("model__hide")
    document.getElementsByClassName("join-community__disclaimer")[0].classList.remove("model__hide")
    document.body.classList.add("stop_body--scroll")
}

//Get all DOM elements
const joinCommunityModelShowElement = document.getElementById("join_community-modal-show")
const closeModalButton = document.getElementsByClassName("close_modal")[0]
const joinCommunityCTAElement = document.getElementsByClassName("join_community--CTA")[0]
const communityMembersModalShow = document.getElementsByClassName("community_members-modal--show")[0]
const closeCommunityMembersModal = document.getElementsByClassName("close_modal--comunnity-members")[0]

//Attach event listeners
joinCommunityModelShowElement.addEventListener("click", showModalDisclaimer)
closeModalButton.addEventListener("click", closeModalDisclaimer)
joinCommunityCTAElement.addEventListener("click", joinCommunity)
communityMembersModalShow.addEventListener("click", communityMembers)
closeCommunityMembersModal.addEventListener("click", closeCommunityMemberModal)