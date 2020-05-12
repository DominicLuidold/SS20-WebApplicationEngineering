// Define map
const map = L.map('map').setView([0, 0], 2);
let eventMarkers = [];
let eventMarkerCoordinates = [];

// Set map layer
L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1IjoiZGx1OTU3NiIsImEiOiJjazl2N2Z4MmcwOHRvM2xtb3E0MGN5bDdoIn0.JfFobDLNt1nYYtPIqU4_Zg'
}).addTo(map);

// Fetch events from XML using rss2json
$.get('https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.festivalticker.de%2Frss-festivalfeed%2Ffestivalkalender.xml', function (response) {
    // Create HTML elements based on response items
    $.each(response.items, function (index, item) {
        const regExp = /Ort: \d+ ([\wöäüÖÄÜß ]*)<br>Land: ([\wöäüÖÄÜß ]*)/gm;
        const match = regExp.exec(item.description);

        // Create event div with title & description
        const event = $('<div>');
        event.html(`<h2>${item.title}</h2><p>${item.description}</p></div><hr>`);

        // Prepare click event
        if (match) {
            // Click event for event with valid location
            event.attr('event', index);
            event.on('click', function () {
                // If marker is not yet saved, create new one; use existing one otherwise
                if (typeof eventMarkerCoordinates[index] === 'undefined') {
                    // Crate new marker
                    addEventMarker(index, item.title, `${match[1]} ${match[2]}`);
                } else {
                    // Use existing marker
                    eventMarkers[index].openPopup();
                    map.flyTo(eventMarkerCoordinates[index], 12);
                }
            });
        } else {
            // Click event for event with invalid or no location
            event.click(function () {
                // Set background colour temporarily to red
                $(this).css('background-color', '#ff958c')

                // Remove background colour after 250ms
                const div = this;
                setTimeout(function () {
                    $(div).css('background-color', '');
                }, 250);
            });
        }

        // Add event div to list
        $('#events').append(event);
    });
});

// Fetch event location using OpenStreetMap
function addEventMarker(eventId, title, location) {
    $.get(`https://nominatim.openstreetmap.org/search?q=${location}&limit=1&format=json&addressdetails=1`, function (response) {
        // Get coordinates from response
        const latitude = response[0].lat;
        const longitude = response[0].lon;

        const eventMarker = L.marker([latitude, longitude]).addTo(map);

        // Open popup and fly to marker
        eventMarker.bindPopup(`<b>${title}</b>`).openPopup();
        map.flyTo([latitude, longitude], 12);

        // Save marker and marker coordinates for later usage
        eventMarkers[eventId] = eventMarker;
        eventMarkerCoordinates[eventId] = [latitude, longitude];
    });
}
