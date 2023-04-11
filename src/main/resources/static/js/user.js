async function getUser() {
    let response = await fetch("http://localhost:8080/api/user");
    let content = await response.json();
    let list = document.querySelector("#userInfo");

    console.log(content);

    let roles = content.roles.map(role => role.name).join(', ');
    list.innerHTML += `
                        <td>${content.id}</td>
                        <td>${content.username}</td>
                        <td>${roles}</td>`;
}
getUser();