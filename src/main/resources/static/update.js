$('#edit').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');
    Edit(id);
})

async function Edit(id) {
    $('#rolesEditUser').empty();
    let user = await getUser(id);
    let form = document.forms["formEditUser"];
    form.id.value = user.id;
    form.username.value = user.username;
    form.name.value = user.name;
    form.lastname.value = user.lastname;
    form.country.value = user.country;
    form.password.value = user.password;


    await fetch(baseURL+"roles")
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
                $('#rolesEditUser')[0].appendChild(el);
            })
        })

    form.addEventListener("submit", ed => {
        ed.preventDefault()
        let userRoles = []
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) userRoles.push({
                id: form.roles.options[i].value,
                role: "ROLE_" + form.roles.options[i].text,
            })
        }
        console.log(userRoles)

        fetch(baseURL + form.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: form.id.value,
                username: form.username.value,
                name: form.name.value,
                lastname: form.lastname.value,
                country: form.country.value,
                password: form.password.value,
                roles: userRoles
            })
        }).then(() => {
            $('#editCloseButton').click();
            allUsers();
        })

    })
}


