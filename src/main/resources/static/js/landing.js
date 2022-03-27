// On load fetch the communities and projects.
const fetchAll = async () => {
  //Fetch the community Data
  const responseCommunity = await fetch(
    "http://localhost:8081/api/communities"
  );

  const dataCommunity = await responseCommunity.json();

  //Restrict to four numbers only
  const maxLengthCommunity =
    dataCommunity.length < 4 ? dataCommunity.length : 4;

  //Fetch the Projects data
  const responseProject = await fetch("http://localhost:8081/api/projects");
  const dataProject = await responseProject.json();

  //Restrict Project to 4
  const maxLengthProject = dataProject.length < 4 ? dataProject.length : 4;

  //Fetch events data
  const responseEvent = await fetch("http://localhost:8081/api/events");
  const dataEvent = await responseEvent.json();

  //Restrict events to 4
  const maxLengthEvent = dataEvent.length < 4 ? dataEvent.length : 4;

  if (dataCommunity) {
    //Create a section which
    for (let i = 0; i < maxLengthCommunity; i++) {
      const a = document.createElement("a");
      a.innerText = dataCommunity[i].name;
      a.setAttribute(
        "href",
        `http://localhost:8081/community/${dataCommunity[i].id}`
      );

      // Upload images
      const div = document.createElement("div");
      div.classList.add("community_card");
      const sourceString = `http://localhost:8081/uploads/${dataCommunity[i].communityImage}`;
      div.innerHTML = `<img src=${sourceString} alt="Community image">`;
      div.append(a);
      document.getElementById("community_card--container").append(div);
    }
  }

  if (dataProject) {
    for (let i = 0; i < maxLengthProject; i++) {
      const a = document.createElement("a");
      a.innerText = dataProject[i].name;
      a.setAttribute(
        "href",
        `http://localhost:8081/project/${dataProject[i].id}`
      );
      // Upload images
      const div = document.createElement("div");
      div.classList.add("community_card");
      const sourceString = `http://localhost:8081/uploads/${dataProject[i].projectCoverImage}`;
      div.innerHTML = `<img src=${sourceString} alt="Project image">`;
      div.append(a);
      document.getElementById("project_card--container").append(div);
    }
  }

  if (dataEvent) {
    for (let i = 0; i < maxLengthEvent; i++) {
      const a = document.createElement("a");
      a.innerText = dataEvent[i].name;
      a.setAttribute("href", `http://localhost:8081/event/${dataEvent[i].id}`);
      // Upload images
      const div = document.createElement("div");
      div.classList.add("community_card");
      const sourceString = `http://localhost:8081/uploads/${dataEvent[i].eventImage}`;
      div.innerHTML = `<img  src=${sourceString} alt="Event image">`;
      div.append(a);
      document.getElementById("event_card--container").append(div);
    }
  }
};
//Call Functions To fetch Communities
fetchAll();
