const baseURL = 'http://localhost:8081/api/';

const newUsername = document.getElementById('newUserName')
const newName = document.getElementById('newName')
const newLastname = document.getElementById('newLastname')
const newCountry = document.getElementById('newCountry')
const newPassword = document.getElementById('newUserPassword')



$(async function() {
    await newUser();
});
async function newUser() {
    await fetch(baseURL+"roles")
        .then(res => res.json())
        .then(roleList => {
            roleList.forEach(role => {
                let el = document.createElement("option");
                el.text = role.role.substring(5);
                el.value = role.id;
                $('#newUserRoles')[0].appendChild(el);
            })
        })

    const form = document.getElementById("formCreateUser");

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        let newUserRoles = [];
        if (form.roles !== undefined) {
            for (let i = 0; i < form.roles.options.length; i++) {
                if (form.roles.options[i].selected) newUserRoles.push({
                    id: form.roles.options[i].value,
                    role: form.roles.options[i].role
                })
            }
        }

        let newData = {
            username: newUsername.value,
            name: newName.value,
            lastname: newLastname.value,
            country: newCountry.value,
            password: newPassword.value,
            roles: newUserRoles
        }
        fetch(baseURL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newData)
        }).then(() => {
            form.reset();
            allUsers();
            $('#allUsersTable').click();
        })
    })

}

