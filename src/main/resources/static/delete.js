$('#delete').on('show.bs.modal', el => {
    let button = $(el.relatedTarget);
    let id = button.data('id');
    Delete(id);
})

async function Delete(id) {
    let user = await getUser(id);
    let form = document.forms["formDeleteUser"];
    form.id.value = user.id;
    form.username.value = user.username;
    form.name.value = user.name;
    form.lastname.value = user.lastname;
    form.country.value = user.country;
    form.roles.value = user.roles;


    $('#rolesDeleteUser').empty();

    await fetch(baseURL +"roles")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let selectedRole = false;
                for (let i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].role === role.role) {
                        selectedRole = true;
                        break;
                    }
                }
                let el = document.createElement("option");
                el.text = role.role.substring(5);
                el.value = role.id;
                if (selectedRole) el.selected = true;
                $('#rolesDelete')[0].appendChild(el);
            })
        });
    form.addEventListener("submit", d => {
        d.preventDefault();
        fetch(baseURL + form.id.value, {method: 'DELETE', headers: {'Content-Type': 'application/json'}})
            .then(() => {
                $('#deleteClose').click();
                allUsers();
            })
    })
}


async function getUser(id) {
    let url = baseURL + id;
    let response = await fetch(url);
    return await response.json();
}

