async function startPage() {
    await thisUser();
}
async function thisUser() {
    fetch("http://127.0.0.1:8081/api/user")
        .then(res => res.json())
        .then(data => {
                // Добавляем информацию в шапку
            $('#headerUsername').append(data.username);
            let roles = data.roles.map(role => " " + role.role.substring(5));
            $('#headerRoles').append(roles);

            let user = `$(
               <tr>
                   <td>${data.id}</td>
                   <td>${data.username}</td>
                   <td>${data.name}</td>
                   <td>${data.lastname}</td>
                   <td>${data.country}</td>
                   <td>${roles}</td>)
                 </tr>`;
            $('#infoTableUser').append(user);
        })
}

startPage()