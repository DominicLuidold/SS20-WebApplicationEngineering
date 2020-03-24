function validateForm() {
    const textInputList = [
        "firstName",
        "lastName",
        "address",
        "country",
        "userID",
        "email",
        "password",
        "confirmPassword",
    ];
    const selectInputList = [
        "sex",
        "role",
    ];

    /* Check for existing content in every text input field */
    if (!textInputList.every(validateTextInputFields)) {
        return false;
    }

    /* CHeck if selectable inputs have been selected */
    if (!selectInputList.every(validateSelectableInputFields)) {
        return false;
    }

    /* Validate userID */
    if (!validateUserID($("#userID"))) {
        return false;
    }

    /* Validate email */
    if (!validateEmail($("#email"))) {
        return false;
    }

    /* Validate password */
    if (!validatePassword($("#password"), $("#confirmPassword"))) {
        return false;
    }

    return true;
}

function validateTextInputFields(input) {
    const inputField = $(`#${input}`);
    if (inputField.val().length === 0) {
        inputField.select();
        alert(`${inputField.siblings("label").text()} muss angegeben werden!`);
        return false;
    }
    return true;
}

function validateSelectableInputFields(input) {
    const inputField = $(`input[name=${input}]`);
    if (!inputField.is(":checked")) {
        inputField.select();
        alert(`${inputField.parent().siblings("label").text()} muss angegeben werden!`);
        return false;
    }
    return true;
}

function validatePassword(password, confirmPassword) {
    const passwordRegex = /^[a-zA-Z]\w{5,10}$/;
    if (passwordRegex.exec(password.val()) === null) {
        password.select();
        alert("Passwort entspricht nicht den Kriterien");
        return false;
    } else if (password.val() !== confirmPassword.val()) {
        confirmPassword.select();
        alert("Passwörter stimmen nicht überein");
        return false;
    }
    return true;
}

function validateUserID(userID) {
    const userIDRegex = /^[a-zA-Z_]{5,12}$/;
    if (userIDRegex.exec(userID.val()) === null) {
        userID.select();
        alert("User-ID entspricht nicht den Kriterien");
        return false;
    }
    return true;
}

function validateEmail(email) {
    /* Regex source: self-made
    *
    * [a-zA-Z](\.?[\w-]+)* -- Namensteil
    * (([a-zA-Z](-*[\w]+)*)\.)* -- etwaige Subdomains
    * [a-zA-Z](-*[a-zA-Z0-9]){1,62} -- Domain Name
    * [a-zA-Z]{2,6} -- TLD
    * */
    const emailRegex = /^[a-zA-Z](\.?[\w-]+)*@(([a-zA-Z](-*[\w]+)*)\.)*[a-zA-Z](-*[a-zA-Z0-9]){1,62}\.[a-zA-Z]{2,6}$/;
    if (emailRegex.exec(email.val()) === null) {
        email.select();
        alert("E-Mail syntaktisch nicht korrekt");
        return false;
    }
    return true;
}

/* TODO - Method NOT used
*
* Instead, the form itself has an action that opens the mail client and puts the form content in the body
* */
function sendEmail(textInputList, selectInputList) {
    let subject = "Beantragen einer User-ID und eines Passworts";

    /* Create body */
    let body = "";
    textInputList.forEach(
        function (input) {
            body += input + ":%20" + $(`#${input}`).val() + "%0A"
        }
    );
    /* TODO - Multiple checkboxes don't work */
    selectInputList.forEach(
        function (input) {
            body += input + ":%20" + $(`input[name=${input}]:checked`).val() + "%0A"
        }
    );

    /* Open mail client */
    window.open(`mailto:donot@sendan.email?subject=${subject}&body=${body}`);
}
