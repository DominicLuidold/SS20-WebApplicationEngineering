let http = require('http');

// Create HTTP server
const server = http.createServer(function (request, response) {
    // Set headers to allow CORS requests
    response.setHeader('Access-Control-Allow-Origin', '*');
    response.setHeader('Access-Control-Request-Method', '*');
    response.setHeader('Access-Control-Allow-Methods', 'OPTIONS, POST');
    response.setHeader('Access-Control-Allow-Headers', '*');

    if (request.method === 'OPTIONS') {
        // Respond with '200 OK' to make CORS preflight requests work
        response.writeHead(200);
        response.end();
    } else if (request.method === 'POST') { // Actual count request handler
        // Respond with '200 OK' and 'application/json'
        response.writeHead(200, {'Content-Type': 'application/json'});

        let body;
        request.on('data', chunk => {
            // Write incoming data into variable
            body = chunk;
        }).on('end', () => {
            // Respond with JSON object containing string length
            console.debug('Counting strings for input: ' + body);
            response.end(JSON.stringify({characterCount: JSON.parse(body).string.length}));
        });
    } else {
        // Respond with '405 Method Not Allowed'
        response.statusCode = 405;
        response.end();
    }
});

// Listen on port 8080
server.listen(8080);
console.debug('Server running at http://127.0.0.1:8080/');
