async function submitEditForm(event) {
    event.preventDefault();

    const id = document.getElementById("idEdit").value;
    const username = document.getElementById("firstNameEdit").value;
    const password = document.getElementById("passwordEdit").value;
    const roles = getSelectedRoles("rolesEdit");

    console.log("ID:", id);
    console.log("Username:", username);
    console.log("Password:", password);
    console.log("Roles:", roles);

    const user = {
        id: id,
        username: username,
        password: password,
        roles: roles
    };

    console.log("User object to be sent:", user);

    const response = await fetch(`http://localhost:8080/admin/users/${id}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    });

    console.log("Response:", response);

    if (response.ok) {
        document.getElementById("editFormBody").reset();
        $('#editModal').modal('hide');
    } else {
        console.log("Response status:", response.status);
        console.log("Response text:", await response.text());
        alert("Ошибка при редактировании пользователя");
    }
}

document.getElementById("editModal").addEventListener("hidden.bs.modal", function () {
    console.log("Close Modal");
    getUsers();
});

getUsers();