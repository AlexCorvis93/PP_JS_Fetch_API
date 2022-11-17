async function getUsers() {
    await allUsers();
}

const table = $('#userTable');
let infoUsers = '';

async function allUsers() {
    table.empty()
    fetch(baseURL)
        .then(resp => resp.json())
        .then(data => {data.forEach(user => {
                infoUsers = `$(
                        <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.name}</td>
                        <td>${user.lastname}</td>
                        <td>${user.country}</td>
                        <td>${user.roles}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" id="buttonEdit"
                                data-action="edit" data-id="${user.id}" data-target="#edit">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete"
                                data-action="delete" data-id="${user.id}" data-target="#delete">Delete</button>
                            </td>
                        </tr>)`;
                table.append(infoUsers);
            })
        })
}

getUsers()

















// async function main() {
//     await allUsers();
// }
//
// const table = document.getElementById('UserTable');
// async function allUsers() {
//     fetch("http://localhost:8081/api/")
//         .then(res => res.json())
//         .then(data => {
//             data.forEach(user => {
//                 let tableWithUsers = `$(
//                         <tr>
//                         <td>${user.username}</td>
//                         <td>${user.name}</td>
//                         <td>${user.lastname}</td>
//                         <td>${user.country}</td>
//                         <td>${user.roles}</td>
//                             <td>
//                                 <button type="button" class="btn btn-info" data-toggle="modal" id="buttonEdit"
//                                 data-action="edit" data-id="${user.id}" data-target="#edit">Edit</button>
//                             </td>
//                             <td>
//                                 <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete"
//                                 data-action="delete" data-id="${user.id}" data-target="#delete">Delete</button>
//                             </td>
//                         </tr>)`;
//                 table.append(tableWithUsers);
//             })
//         })
// }
//
//
// main()
//
//
//









