function imagePopup(imagePath) {
    /* Create new image */
    let img = new Image();
    img.src = imagePath;

    img.onload = function () {
        /* Open popup once image is loaded */
        let popup = window.open(imagePath, imagePath, `width=${img.width}, height=${img.height}`);

        /* Image closes popup when clicked */
        popup.onload = function () {
            popup.document.getElementsByTagName("img")[0].onclick = function () {
                popup.close();
            };
        };
    };
}
