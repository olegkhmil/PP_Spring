$(function () {

    loadAllUsersOnPage();
});

$('#nav-UsersTable-tab').on('click', function (e) {
    e.preventDefault();
    loadAllUsersOnPage()
});
//УДАЛЕНИЕ ЮЗЕРА
$('#tbody').on('click', '.btn-danger', function () {
    let longID = $(this).val();
    console.log(longID);
    console.log(typeof longID);

    $.ajax({
        url: "/rest/delete/" + longID,
        //contentType: 'application/json; charset=utf-8',
        type: "DELETE",
        //data: longID,
        dataType: "json",
        //success: alert("Deleted")
    });
    loadAllUsersOnPage()
    $('#nav-UsersTable-tab').click()
    //$('#nav-UsersTable-tab').trigger('active')
});

function deleteUser(id) {

}

// ДОБАВЛЕНИЕ ЮЗЕРА
$('#add_form').on("submit", function (e) {
    e.preventDefault();
    let uRoles = [];
    $('.form-check-input:checked').each(function () {
        uRoles.push($(this).val())
    })

    let user = {
        id: $('#hidEditID').val(),
        name: $('#newUserName').val(),
        age: parseInt($('#newUserAge').val()),
        email: $('#newUserEmail').val(),
        password: $('#newUserPass').val(),
        roles: uRoles
    }
    user = $.toJSON(user);
    // alert(user);
    $.ajax({
        url: "/rest/add",
        contentType: 'application/json; charset=utf-8',
        type: "POST",
        data: user,
        dataType: "json",
        async: false,
        success: function (data) {
            alert(data)
        },
        error: function (data) {
            alert("error")
        },
        complete: function () {
            loadAllUsersOnPage()
            $('#add_form').each(function () {
                this.reset();  //очищается форма методом .reset()
            });
            toAdminPanel();
        }
    });
    $('#nav-UsersTable-tab').click()
    //$('#nav-UsersTable-tab').click()
});


//в форме edit
$('#editUserForm').on('submit', function (e) {
    e.preventDefault()
    let uRoles = [];
    $('.form-check-input:checked').each(function () {
        uRoles.push($(this).val())
    })

    let user = {
        id: $('#hidEditID').val(),
        name: $('#editName').val(),
        age: parseInt($('#editAge').val()),
        email: $('#editEmail').val(),
        password: $('#editPass').val(),
        state: $('#optionUserState').val(),
        roles: uRoles
    }
    user = $.toJSON(user);
    $.ajax({
        url: "/rest/update",
        contentType: 'application/json; charset=utf-8',
        type: "PUT",
        data: user,
        dataType: "json",
        async: false,
        success: function (data) {
            alert("Успех")
        },
        error: function (data) {
            alert("Провал")
        },
        complete: function () {
            loadAllUsersOnPage();
            $('#editUserModal').fadeOut();
            $('.modal-backdrop').remove();

        }
    });

});


function loadAllUsersOnPage() {
    $.get("/rest/all", function (allUsers) {
        console.log(allUsers);
        $('#tbody').empty();
        $.each(allUsers, function (i, user) {
            addUser(user)
        });
    });

}

function addUser(user) {
    let userRoles = user.roles;
    let r = ' ';
    $.each(userRoles, function (i, role) {
        r = r + role.name + "<br/>";
    });

    $('<tr id="' + user.id + '">').append(
        $('<td class="userID">').text(user.id),
        $('<td class="userNAME">').text(user.name),
        $('<td class="userAGE">').text(user.age),
        $('<td class="userEMAIL">').text(user.email),
        $('<td class="userROLES">').html(r),
        $('<td class="userSTATE">').text(user.state),
        //КНОПКА DEL
        $('<td>').html(
            "<button type='submit' class='btn btn-danger' " +
            "value='" + user.id + "'>" +
            "Del</button></form>"),
        //КНОПКА EDIT
        $('<td>').html(
            "<button " +
            "class='btn btn-success' " +
            "id='editUserButton' " +
            "type='submit' " +
            "data-toggle='modal' " +
            "data-target= '#editUserModal' " +
            "onclick='openEditUserModal(" + user.id + ")' " +
            "value='edit" + user.id + "'>" +
            "Edit </button>")
    ).appendTo('#tbody');
}

function openEditUserModal(id) {

    let uRl = '/rest/userById/' + id;
    $.get(uRl, function (user) {

        console.log(user.roles)
        console.log(user.name)


        $('#exampleModalLabel').text(user.name);
        $('#hidEditID').attr('value', user.id);
        $('#editName').attr('value', user.name);
        $('#editAge').attr('value', user.age);
        $('#editEmail').attr('value', user.email);
        $('#optionUserState').attr('value', user.state).text(user.state);

        for (let i = 0; i < user.roles.length; i++) {
            if (user.roles[i].name === "ADMIN") {
                $('#admEdit').prop('checked', true)
            } else if (user.roles[i].name === "USER") {
                $('#usEdit').prop('checked', true)
            }
        }
    });
}


function toAdminPanel() {
    $('#nav-UsersTable').attr('class', 'tab-pane fade active show');
    $('#nav-newUser').attr('class', 'tab-pane fade');

}

function hideModal() {
    $('#editUserModal').removeClass('show').attr('style', "display: none;");
    $('body').removeAttr('style');
    $('body').removeAttr('class');
}