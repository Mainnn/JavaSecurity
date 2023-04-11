async function updateUserList() {
    const list = document.querySelector(".AllUsers");
    list.innerHTML = ""; // Очистите текущий список пользователей
    await getUsers(); // Загрузите и отобразите обновленный список пользователей
}