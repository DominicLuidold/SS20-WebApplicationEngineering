// Get references to input fields
const inputField = document.getElementById('characters');
const countField = document.getElementById('character_count');

const infoText = 'Anzahl Zeichen: '

// Add 'input' event listener to catch input
inputField.addEventListener('input', function (event) {
    // Fetch counter backend with 'POST' and JSON object containing the string
    fetch('http://localhost:8080', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({string: inputField.value})
    })
        .then(response => response.json())
        // Set countField value according to response
        .then(data => countField.value = infoText + data.characterCount)
        .catch(() => countField.value = 'Es ist ein Fehler aufgetreten!');
});
