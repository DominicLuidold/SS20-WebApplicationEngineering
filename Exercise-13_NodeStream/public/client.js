const backend = 'http://localhost:3000';
const input = document.getElementById('videoName');
const submitButton = document.getElementById('submit');
const videoPlayer = document.getElementById('player');

submitButton.addEventListener('click', () => {
    fetch(`${backend}/find-video`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({video: input.value})
    })
        .then(response => response.json())
        .then(data => {
            if (data.videoFound === true) {
                showVideo();
            } else {
                alert('Video konnte nicht gefunden werden!');
            }
        })
        .catch(() => alert('Es ist ein Fehler aufgetreten!'));
});

function showVideo() {
    videoPlayer.src = `${backend}/video/${input.value}`;
    videoPlayer.hidden = false;
    videoPlayer.autoplay = true;
}
