const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const fs = require('fs');

const app = express();
const router = express.Router();

app.use(bodyParser.json())
app.use(express.static(path.join(__dirname, 'public')));
app.use(router);

// Get all video from file system
const videoFolder = './video/';
const videoList = [];
fs.readdir(videoFolder, (err, files) => {
    files.forEach(video => {
        videoList.push(video);
        console.debug('Found video: ' + video);
    });
});

router.post('/find-video', function (req, res) {
    console.debug('Looking for video.. ' + req.body.video);
    res.json({videoFound: videoList.includes(req.body.video)});
});

/* Source: https://medium.com/better-programming/video-stream-with-node-js-and-html5-320b3191a6b6 */
router.get('/video/*', function (req, res) {
    const videoPath = '.' + req.url;
    const fileSize = fs.statSync(videoPath).size;
    const range = req.headers.range;

    console.debug('Sending video to client.. ' + req.url);
    if (range) {
        const parts = range.replace(/bytes=/, "").split("-");
        const start = parseInt(parts[0], 10);
        const end = parts[1] ? parseInt(parts[1], 10) : fileSize - 1;
        const chunkSize = (end - start) + 1;
        console.debug({start, end});
        const file = fs.createReadStream(videoPath, {start, end});
        const head = {
            'Content-Range': `bytes ${start}-${end}/${fileSize}`,
            'Accept-Ranges': 'bytes',
            'Content-Length': chunkSize,
            'Content-Type': 'video/mp4',
        };
        res.writeHead(206, head);
        file.pipe(res);
    } else {
        const head = {
            'Content-Length': fileSize,
            'Content-Type': 'video/mp4',
        };
        res.writeHead(200, head);
        fs.createReadStream(videoPath).pipe(res);
    }
});

module.exports = app;
