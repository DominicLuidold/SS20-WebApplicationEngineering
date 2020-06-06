const express = require('express');
const path = require('path');
const os = require( 'os' );

const app = express();
const router = express.Router();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, 'public')));
app.use(router);

router.get('/', function(req, res) {
    res.render('index');
});

router.get('/ip-date-info', function (req, res) {
    // Get all IP addresses from Node.js server
    const ipAddresses = [];
    Object.keys(os.networkInterfaces()).forEach(networkInterface => {
        os.networkInterfaces()[networkInterface].forEach(actualInterface => {
            ipAddresses.push(actualInterface.address)
        });
    });

    // Send response
    res.json({
        hostIp: ipAddresses,
        date: new Date(),
        time: new Date().getTime()
    });
});

module.exports = app;
