let rolesLoaded = false;
console.log("JavaScript is running");
$(document).ready(async function() {
    // Загрузите роли только один раз при загрузке страницы

    if (!rolesLoaded) {
        await populateRoleOptions("roles");
        await populateRoleOptions("rolesEdit");
        rolesLoaded = true;
    }

    // Обновите список ролей при открытии модального окна добавления пользователя
    $('#addModal').on('show.bs.modal', async function () {
        if (!rolesLoaded) {
            await populateRoleOptions("roles");
            await populateRoleOptions("rolesEdit");
            rolesLoaded = true;
        }
    });
});
async function getRoles() {
    const response = await fetch("http://localhost:8080/admin/roles");
    const roles = await response.json();
    return roles;
}

// Функция для заполнения выпадающих списков ролей
async function populateRoleOptions(elementId) {
    const roles = await getRoles();
    const selectElement = document.getElementById(elementId);



    selectElement.innerHTML = '';
    roles.forEach(role => {
        const option = document.createElement("option");
        option.value = role.name;
        option.textContent = role.name;
        selectElement.appendChild(option);
    });
}

// Вызовите функцию populateRoleOptions для заполнения выпадающих списков ролей
$(document).ready(async function() {
    await populateRoleOptions("roles");
    await populateRoleOptions("rolesEdit");
});
// Функция getSelectedRoles остается без изменений
function getSelectedRoles(elementId) {
    const selectElement = document.getElementById(elementId);
    const selectedOptions = Array.from(selectElement.selectedOptions);
    return selectedOptions.map(option => {
        return {
            name: option.value
        };
    });
}

// Обновите обработчик события для формы создания пользователя
document.getElementById("createUserForm").addEventListener("submit", async (event) => {

    console.log("Submit event triggered");

    const formData = new FormData(event.target);

    const username = formData.get("username");
    const password = formData.get("password");
    const roles = getSelectedRoles("roles");

    const newUser = {
        username: username,
        password: password,
        roles: roles,
    };

    try {
        console.log("Sending request to createUser:", JSON.stringify(newUser));
        const response = await fetch("/admin/createUser", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newUser),
        });


        if (response.ok) {
            const createdUser = await response.json();
            console.log("User created:", createdUser);
            await getUsers();

            // Очистите поля формы
            document.getElementById("username").value = "";
            document.getElementById("password").value = "";
            document.getElementById("roles").selectedIndex = -1;
        } else  {
            console.error("Error creating user:", response.status, response.statusText, await response.text());
        }
    } catch (error) {
        console.error("Error creating user:", response.status, response.statusText, await response.text());
    }
});