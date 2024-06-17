window.onload = function () {

    // Fetch, HTML para a div com ID expecifico -> NomeDoHTML + Copy

    fetch('../header.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('headerCopy').innerHTML = data;
        });

    fetch('../createRecipeModal.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('createRecipeCopy').innerHTML = data;
        });

    fetch('../footer.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('footerCopy').innerHTML = data;
        });

    fetch('../index.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('indexCopy').innerHTML = data;
        });

}